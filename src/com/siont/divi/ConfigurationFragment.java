package com.siont.divi;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.siont.divi.R;

public class ConfigurationFragment extends Fragment {
	
	private View rootView;
	private Context context;

    public ConfigurationFragment(){}
    
    @SuppressLint("InflateParams")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	context = getActivity();
    	
    	inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    rootView = inflater.inflate(R.layout.configuration, null);
    	
    	TextView txt = (TextView) rootView.findViewById(R.id.custom_font);
	    Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Chantelli_Antiqua.ttf");
	    txt.setTypeface(font);
    	
    	PackageManager pm = context.getPackageManager();
    	Intent intent = pm.getLaunchIntentForPackage("com.android.settings");
    	if (intent != null)
    		startActivity(intent);
    	else
    		Log.e("LaunchSettings", "La aplicación no esta disponible en este dispositivo");
    	
        //rootView = inflater.inflate(R.layout.configuration, container, false);
          
        return rootView;
    }
	
}
