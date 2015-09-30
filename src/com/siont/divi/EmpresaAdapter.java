package com.siont.divi;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EmpresaAdapter extends ArrayAdapter<Empresa> {
	  
	  @SuppressWarnings("unused")
	private Activity activity;
	  private List<Empresa> itemList;
	  private Context context;
	  ImageLoader imageLoader;
	              
	  public EmpresaAdapter(List<Empresa> itemList, Context ctx) {
		  super(ctx, android.R.layout.simple_list_item_1, itemList);
	    this.itemList = itemList;
	    this.context = ctx;
	    imageLoader = new ImageLoader(ctx);
	  }
	  
	  @Override
	  public int getCount() {
		  if(itemList != null)
	    return itemList.size();
		  return 0;
	  }
	  
	  @Override
	  public Empresa getItem(int position) {
		  if (itemList != null)
				return itemList.get(position);
			return null;
	  }
	  
	  @Override
	  public long getItemId(int position) {
	    if (itemList != null)
		return itemList.get(position).hashCode();
	    	return 0;
	  }
	  
	@SuppressLint({ "InflateParams", "ViewHolder" })
	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    //final View vi=convertView;
	          
	    //if(convertView == null) {
	      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	      final View vi = inflater.inflate(R.layout.list_item_layout, null);
	    //}
	          
	    final Empresa empresa = itemList.get(position);
	    
	    /*TextView txtnom_Empresa = (TextView) vi.findViewById(R.id.txtnom_Empresa);
	    txtnom_Empresa.setText(empresa.getNom_empresa());*/
	          
	    ImageView image = (ImageView) vi.findViewById(R.id.imgMySQL);
	    imageLoader.DisplayImage(empresa.getImg_empresa(), image);
	    
	    Button makeCall = (Button) vi.findViewById(R.id.btnLlamar);
	    //makeCall.setText(empresa.getTel_empresa());
	    makeCall.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	             String uri = empresa.getTel_empresa();
	             Intent intent = new Intent(Intent.ACTION_DIAL);
	             intent.setData(Uri.parse("tel:" +uri));
	             vi.getContext().startActivity(intent);
	        }
	    });
	    
	    Button sendEmail = (Button) vi.findViewById(R.id.btnContactar);
	    //sendEmail.setText(empresa.getEmail_empresa());
	    sendEmail.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	        	String uri = empresa.getEmail_empresa();
	        	Intent emailIntent = new Intent(Intent.ACTION_SEND);
	        	emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{uri});
	        	emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email Subject");
	        	emailIntent.putExtra(Intent.EXTRA_TEXT, "");
	        	emailIntent.setType("text/plain");
	        	emailIntent.setType("message/rfc822");

	        	vi.getContext().startActivity(Intent.createChooser(emailIntent, "Enviar E-mail"));
	        }
	    });
	    
	    Button webEmpresa = (Button) vi.findViewById(R.id.btnWebsite);
	    //webEmpresa.setText(empresa.getWeb_empresa());
	    webEmpresa.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	        	String url = empresa.getWeb_empresa();
	        	if (!url.startsWith("https://") && !url.startsWith("http://")){
	        	    url = "http://" + url;
	        	}
	        	//String url = empresa.getWeb_empresa();
	        	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url)); 
	        	vi.getContext().startActivity(intent);
	        }
	    });
	          
	    TextView txtDescripcion = (TextView) vi.findViewById(R.id.txtDescripcion);
	    txtDescripcion.setText(empresa.getDesc_empresa());
	    
	    return vi;
	  }
	
	public List<Empresa> getItemList() {
		return itemList;
	}

	public void setItemList(List<Empresa> itemList) {
		this.itemList = itemList;
	}
	
	}
