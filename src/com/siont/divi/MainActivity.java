package com.siont.divi;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.ProfilePictureView;
import com.siont.divi.R;

public class MainActivity extends ActionBarActivity {
	
	private UiLifecycleHelper uiHelper;
	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state, Exception exception) {
			onSessionStateChanged(session, state, exception);
		}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);
		
		LoginButton lb = (LoginButton) findViewById(R.id.login_button);
		lb.setPublishPermissions(Arrays.asList("email", "public_profile", "user_friends"));
		
		ActionBar actionBAR = getActionBar();
		actionBAR.show();
		actionBAR.setTitle("");
		
		try {
	        PackageInfo info = getPackageManager().getPackageInfo(
	                "com.siont.divi", 
	                PackageManager.GET_SIGNATURES);
	        for (Signature signature : info.signatures) {
	            MessageDigest md = MessageDigest.getInstance("SHA");
	            md.update(signature.toByteArray());
	            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
	            }
	    } catch (NameNotFoundException e) {

	    } catch (NoSuchAlgorithmException e) {

	    }
		
	}
	
	private ShareActionProvider mShareActionProvider;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
		getMenuInflater().inflate(R.menu.menu_actions, menu);
		
		// Set up ShareActionProvider's default share intent
	    MenuItem shareItem = menu.findItem(R.id.action_share);
	    mShareActionProvider = (ShareActionProvider)
	    		MenuItemCompat.getActionProvider(shareItem);
	    mShareActionProvider.setShareIntent(sIntent());
		
		return super.onCreateOptionsMenu(menu);
	}
	
	private Intent sIntent() {
	    Intent intent = new Intent(Intent.ACTION_SEND);
	    intent.setType("text/plain");
	    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
	    intent.putExtra(Intent.EXTRA_SUBJECT, "MiFormula");
        intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=app.miformula");
	    return intent;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_settings:
	            openSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	private void openSettings() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		Session session = Session.getActiveSession();
		if(session != null && (session.isClosed() || session.isOpened())){
			onSessionStateChanged(session, session.getState(), null);
		}
		
		uiHelper.onResume();
	}
	
	
	@Override
	protected void onPause() {
		super.onPause();
		uiHelper.onPause();
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}
	
	
	@Override
	protected void onSaveInstanceState(Bundle bundle) {
		super.onSaveInstanceState(bundle);
		uiHelper.onSaveInstanceState(bundle);
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}
	
	
	
	// METHODS FACEBOOK
	public void onSessionStateChanged(final Session session, SessionState state, Exception exception){
		if(session != null && session.isOpened()){
			
			Log.i("Script", "Usuário conectado");
			
			Request.newMeRequest(session, new Request.GraphUserCallback() {
				
				@Override
				public void onCompleted(GraphUser user, Response response) {
					if(user != null){
						Intent intent = new Intent(getApplicationContext(), Index.class);
						/*TextView tv = (TextView) findViewById(R.id.name);
						tv.setText(user.getFirstName()+" "+user.getLastName());
						
						tv = (TextView) findViewById(R.id.email);
						tv.setText(user.getProperty("email").toString());
						
						tv = (TextView) findViewById(R.id.id);
						tv.setText(user.getId());*/
						
						ProfilePictureView ppv = (ProfilePictureView) findViewById(R.id.fbImg);
						ppv.setProfileId(user.getId());
						
						getFriends(session);
						startActivity(intent);
						
					}
				}
			}).executeAsync();
			
		}
		else{
			Log.i("Script", "Usuário no conectado");
		}
	}
	
	
	public void getFriends(Session session){
		Request.newMyFriendsRequest(session, new Request.GraphUserListCallback() {
			@Override
			public void onCompleted(List<GraphUser> users, Response response) {
				if(users != null){
					Log.i("Script", "Friends: "+users.size());
				}
				Log.i("Script", "response: "+response);
			}
		}).executeAsync();
	}
}
