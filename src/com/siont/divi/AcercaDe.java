package com.siont.divi;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AcercaDe extends Fragment {

	private Context context;
	private View rootView;
	/**
	 * @param args
	 */
	public AcercaDe(){}
    
    //Context contxt;
    @SuppressLint("InflateParams")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
   	context = getActivity();
//    	
//    	PackageManager pm = contxt.getPackageManager();
//    	Intent intent = pm.getLaunchIntentForPackage("com.android.settings");
//    	if (intent != null)
//    		startActivity(intent);
//    	else
//    		Log.e("LaunchSettings", "La aplicación no esta disponible en este dispositivo");
    	inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    rootView = inflater.inflate(R.layout.acercade, null);
	    
	    TextView txt = (TextView) rootView.findViewById(R.id.custom_font);
	    Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Chantelli_Antiqua.ttf");
	    txt.setTypeface(font);
    	
	    Button sendEmail = (Button) rootView.findViewById(R.id.btnWebsite_siont);
	    //sendEmail.setText(empresa.getEmail_empresa());
	    sendEmail.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	        	String url = "http://www.siont.com.co";
	        	if (!url.startsWith("https://") && !url.startsWith("http://")){
	        	    url = "http://" + url;
	        	}
	        	//String url = empresa.getWeb_empresa();
	        	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url)); 
	        	rootView.getContext().startActivity(intent);
	        }
	    });
    	
        //View rootView = inflater.inflate(R.layout.acercade, container, false);
          
        return rootView;
    }

}
