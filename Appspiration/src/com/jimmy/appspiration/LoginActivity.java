package com.jimmy.appspiration;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class LoginActivity extends Activity {
	ProgressDialog prgDialog;
	TextView errorMsg;
	EditText userET;
	EditText pwdET;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		errorMsg = (TextView)findViewById(R.id.login_error);
		userET = (EditText)findViewById(R.id.loginUsername);
		pwdET = (EditText)findViewById(R.id.loginPassword);
		
		prgDialog = new ProgressDialog(this);
		prgDialog.setMessage("Please wait...");
		prgDialog.setCancelable(false);
	}

	public void loginUser(View view){
		String user = userET.getText().toString();
		String password = pwdET.getText().toString();
		RequestParams params = new RequestParams();
		if(Utility.isNotNull(user) && Utility.isNotNull(password)){
			if(Utility.validate(user)){
				params.put("username", user);
				params.put("password", password);
				invokeWS(params);
			} 
			else{
				Toast.makeText(getApplicationContext(), "Please enter valid username", Toast.LENGTH_LONG).show();
			}
		} else{
			Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
		}

	}

	public void invokeWS(RequestParams params){
		prgDialog.show();
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://192.168.1.76:8080/appspiration_webservice/login/dologin",params ,new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				prgDialog.hide();
				try {
					JSONObject obj = new JSONObject(response);
					if(obj.getBoolean("status")){
						Toast.makeText(getApplicationContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();
						navigatetoHomeActivity();
					} 
					else{
						errorMsg.setText(obj.getString("error_msg"));
						Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
					e.printStackTrace();

				}
			}
			
			@Override
			public void onFailure(int statusCode, Throwable error,
					String content) {
				prgDialog.hide();
				// When Http response code is '404'
				if(statusCode == 404){
					Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
				} 
				// When Http response code is '500'
				else if(statusCode == 500){
					Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
				} 
				// When Http response code other than 404, 500
				else{
					Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	public void navigatetoHomeActivity(){
		Intent homeIntent = new Intent(getApplicationContext(),FragmentPagerSupport.class);
		homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(homeIntent);
	}

	public void navigatetoRegisterActivity(View view){
		Intent loginIntent = new Intent(getApplicationContext(),RegisterActivity.class);
		loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(loginIntent);
	}

}
