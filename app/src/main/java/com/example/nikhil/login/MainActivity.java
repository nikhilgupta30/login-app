package com.example.nikhil.login;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        LoaderManager.LoaderCallbacks<String>{

    private Button button;

    private static final String authUrl = "http://devopedia.herokuapp.com/api/student/authenticate";

    private static final int LOADER_ID = 1;

    private String result;

    private EditText email,password;

    String toastMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.login);
        button.setOnClickListener(this);

        password = (EditText)findViewById(R.id.password);
        email = (EditText)findViewById(R.id.email);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.login:
                toastMessage = "Password: " + password.getText().toString();
                Log.v(MainActivity.class.getSimpleName(),toastMessage);
                authenticate();

        }
    }

    public void authenticate(){
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {

            // Get a reference to the LoaderManager, in order to interact with loaders.
            //LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            //getLoaderManager().restartLoader(LOADER_ID, null, this);
            getLoaderManager().restartLoader(LOADER_ID, null, dataApi_1);
        }
    }

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        String[] input = new String[2];
        input[0] = new String();
        input[0] = email.getText().toString();
        input[1] = new String();
        input[1] = password.getText().toString();

        return new CustomLoader(MainActivity.this,authUrl,input);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {

        if(data == null){
            Toast.makeText(MainActivity.this,"log in failed",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this,"Successfully logged in",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        result = "";
    }


    private LoaderManager.LoaderCallbacks<String> dataApi_1
            = new LoaderManager.LoaderCallbacks<String>(){

        @Override
        public Loader<String> onCreateLoader(int i, Bundle bundle) {
            String[] input = new String[2];
            input[0] = new String();
            input[0] = email.getText().toString();
            input[1] = new String();
            input[1] = password.getText().toString();

            return new CustomLoader(MainActivity.this,authUrl,input);
        }

        @Override
        public void onLoadFinished(Loader<String> loader, String data) {

            if(data == null){
                Toast.makeText(MainActivity.this,"log in failed",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this,"Successfully logged in",Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onLoaderReset(Loader<String> loader) {
            result = "";
        }
    };

}
