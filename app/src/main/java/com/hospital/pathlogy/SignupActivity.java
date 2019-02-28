package com.hospital.pathlogy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    TextView signinhere,signUpBtn;
    private EditText ETUsername, ETPassword, ETEmail, ETMobile;
    Typeface fonts1;
    ProgressDialog loading ;//= ProgressDialog.show(getActivity(), "Loading Data", "Please wait...", false, false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //TODO: Implement siugnup Activity
        ETUsername =findViewById(R.id.regUsername);
        ETPassword =findViewById(R.id.regPassword);
        ETEmail =findViewById(R.id.regEmail);
        ETMobile =findViewById(R.id.regMobile);

        signinhere = (TextView)findViewById(R.id.signinhere);

        signinhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });



        fonts1 =  Typeface.createFromAsset(SignupActivity.this.getAssets(),
                "fonts/Lato-Regular.ttf");

        TextView t1 =(TextView)findViewById(R.id.signinhere);
        t1.setTypeface(fonts1);

        signUpBtn=findViewById(R.id.regSignup);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attempSignup();
            }
        });
    }

    public void attempSignup()
    {
        ETEmail.setError(null);
        ETUsername.setError(null);
        ETPassword.setError(null);
        ETMobile.setError(null);

        String username=ETUsername.getText().toString();
        String password=ETPassword.getText().toString();
        String email=ETEmail.getText().toString();
        String mobileNo=ETMobile.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            ETPassword.setError(getString(R.string.error_invalid_password));
            focusView = ETPassword;
            cancel = true;
        }

        // Check for a valid user address.
        if (TextUtils.isEmpty(username)) {
            ETUsername.setError(getString(R.string.error_field_required));
            focusView = ETUsername;
            cancel = true;
        }
        if (TextUtils.isEmpty(mobileNo)) {
            ETMobile.setError("Mobile No is required.");
            focusView = ETMobile;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            ETEmail.setError("Email is required.");
            focusView = ETEmail;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            loading = ProgressDialog.show(SignupActivity.this, "Signing up.", "Please wait...", false, false);
            try {
                register(username, password,email,mobileNo);
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(),"Error while attempting login.",Toast.LENGTH_SHORT);
                Log.d("LoginActivity",e.toString());
            }
            // mAuthTask = new UserLoginTask(email, password);
            //  mAuthTask.execute((Void) null);
        }

    }
    private boolean isPasswordValid(String password) {

        return password.length() > 4;
    }

    public void register(final String username, final String password, final String email, final String MobileNo){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.SERVER_URL+"/register"+"?id=1",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        JSONObject responseObj = null;
                        try {

                            JSONArray jsonArray=new JSONArray(response);
                            responseObj = (JSONObject)jsonArray.get(0);


                            //If we are getting success from server //type 1 for user
                            if (responseObj.getBoolean("success")) {
                                //Creating a shared preference
                                SharedPreferences sharedPreferences = SignupActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                //Creating editor to store values to shared preferences
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                //Adding values to editor
                                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                                editor.putString(Config.USERNAME_PREF, username);
                                editor.putString(Config.USER_OBJ, responseObj.getJSONObject("user").toString());
                                editor.putString(Config.USER_TYPE, "user");

                                //Saving values to editor
                                editor.commit();
                                Toast.makeText(getApplicationContext(), "Signup Success!!", Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), "Login Success!!", Toast.LENGTH_SHORT).show();

                                //Starting profile activity
                                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                startActivity(intent);

                            }
                            else {
                                //If the server response is not success
                                //Displaying an error message on toast
                                Toast.makeText(SignupActivity.this, "Error While creating account.Please try again", Toast.LENGTH_LONG).show();
                                ETPassword.requestFocus();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                            loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Connection Failed.", Toast.LENGTH_SHORT).show();
                        Log.d("SIGNUPACTIVITY", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.KEY_USERNAME, username);
                params.put(Config.KEY_PASSWORD, password);
                params.put("email", email);
                params.put("mobileNo", MobileNo);
                params.put("type","1");//user

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(SignupActivity.this);
        requestQueue.add(stringRequest);
    }
}
