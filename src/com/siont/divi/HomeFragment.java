package com.siont.divi;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HomeFragment extends Fragment {
	
	private ListView listView;
	private EmpresaAdapter empAdapter;
	private Empresa empresa;
	public HomeFragment(){}
    
	@SuppressLint("InflateParams")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.home, null);
        empAdapter = new EmpresaAdapter(new ArrayList<Empresa>(), getActivity());
        listView = (ListView) rootView.findViewById(R.id.lv_empresas);
        listView.setAdapter(empAdapter);
        (new AsyncListViewLoader()).execute("http://www.siont.com.co/divi/empresas.php");
        
        return rootView;
        
    } 	
    
    /*public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
		getActivity().setContentView(R.layout.home);
		listView = (ListView) getView().findViewById(R.id.lv_empresas);
		
		accessWebService();
    }*/
    
    private class AsyncListViewLoader extends AsyncTask<String, Void, List<Empresa>> {
    	private final ProgressDialog dialog = new ProgressDialog(getActivity());
    	
    	@Override
		protected void onPostExecute(List<Empresa> result) {
    		super.onPostExecute(result);
			dialog.dismiss();
			empAdapter.setItemList(result);
			empAdapter.notifyDataSetChanged();
		}
    	
    	@Override
		protected void onPreExecute() {		
			super.onPreExecute();
			dialog.setMessage("Cargando Empresas...");
			dialog.show();			
		}
       
    	@Override
		protected List<Empresa> doInBackground(String... params) {
    		List<Empresa> result = new ArrayList<Empresa>();
    		
    		try {
				URL u = new URL(params[0]);
				
				HttpURLConnection conn = (HttpURLConnection) u.openConnection();
				conn.setRequestMethod("GET");
				
				conn.connect();
				InputStream is = conn.getInputStream();
				
				// Read the stream
				byte[] b = new byte[1024];
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				
				while ( is.read(b) != -1)
					baos.write(b);
				
				String JSONResp = new String(baos.toByteArray());
				
				JSONObject jsonResponse = new JSONObject(JSONResp);
				JSONArray arr = jsonResponse.optJSONArray("empresas");
				for (int i=0; i < arr.length(); i++) {
					JSONObject emp = arr.getJSONObject(i);
					
					empresa = new Empresa(emp.getInt("id_empresa"), emp.getString("nom_empresa"),
							emp.getString("email_empresa"), emp.getString("tel_empresa"),
							emp.getString("desc_empresa"), emp.getString("web_empresa"), 
							emp.getString("img_empresa"));
					//empresa.setData(emp.getString("img_empresa"));
					
					result.add(empresa);
   
					//result.add(convertEmpresa(arr.getJSONObject(i)));
				}
				
				return result;
			}
			catch(Throwable t) {
				t.printStackTrace();
			}
			return null;
		}
		
		/*private Empresa convertEmpresa(JSONObject obj) throws JSONException {			
			
			int id = obj.getInt("id_empresa");
			String name = obj.getString("nom_empresa");			
			//convertEmpresa.setData(obj.getString("img_empresa"));
			
			return new Empresa(id, name);
			
		}*/
    	
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_actions, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       // handle item selection
       switch (item.getItemId()) {
          case R.id.action_share:
             // do s.th.
             return true;
          default:
             return super.onOptionsItemSelected(item);
       }
    }
	
}
