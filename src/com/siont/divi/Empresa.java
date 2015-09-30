package com.siont.divi;

import java.io.Serializable;
 
@SuppressWarnings("serial")
public class Empresa implements Serializable {
	
	private int id_empresa;
	private String nom_empresa;
    //private String data;
    private String img_empresa;
    private String email_empresa;
    private String tel_empresa;
    private String desc_empresa;
    private String web_empresa;
    
    public Empresa(int id_empresa, String nom_empresa, String email_empresa,
    		String tel_emrpesa, String desc_empresa, String web_empresa, String img_empresa){
    	super();
    	this.id_empresa = id_empresa;
    	this.nom_empresa = nom_empresa;
    	this.email_empresa = email_empresa;
    	this.tel_empresa = tel_emrpesa;
    	this.desc_empresa = desc_empresa;
    	this.web_empresa = web_empresa;
    	this.img_empresa = img_empresa;
    }

/*public String getData() {
    return data;
  }*/
 
  /*public void setData(String data) {
    this.data = data;
    try {   
      byte[] byteData = Base64.decode(data.getBytes(), Base64.DEFAULT);
      this.img_empresa = BitmapFactory.decodeByteArray( byteData, 0, byteData.length);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }*/

  public String getImg_empresa() {
		return img_empresa;
	}

public void setImg_empresa(String img_empresa) {
	  this.img_empresa = img_empresa;
  }
     
  /*public Bitmap getImg_empresa() {
    return img_empresa;
  }*/
  
  public int getId_empresa() {
	return id_empresa;
}

public void setId_empresa(int id_empresa) {
	this.id_empresa = id_empresa;
}

public String getNom_empresa() {
	return nom_empresa;
}

public void setNom_empresa(String nom_empresa) {
	this.nom_empresa = nom_empresa;
}

public String getEmail_empresa() {
	return email_empresa;
}

public void setEmail_empresa(String email_empresa) {
	this.email_empresa = email_empresa;
}

public String getTel_empresa() {
	return tel_empresa;
}

public void setTel_empresa(String tel_empresa) {
	this.tel_empresa = tel_empresa;
}

public String getDesc_empresa() {
	return desc_empresa;
}

public void setDesc_empresa(String desc_empresa) {
	this.desc_empresa = desc_empresa;
}

public String getWeb_empresa() {
	return web_empresa;
}

public void setWeb_empresa(String web_empresa) {
	this.web_empresa = web_empresa;
}

@Override
  public String toString(){
		return this.id_empresa+this.nom_empresa+this.img_empresa+this.tel_empresa+this.email_empresa+
				this.desc_empresa+this.web_empresa;
	}
  
}
