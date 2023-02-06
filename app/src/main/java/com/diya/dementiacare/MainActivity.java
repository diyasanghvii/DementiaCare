 package com.diya.dementiacare;

 import android.content.Intent;
 import android.content.SharedPreferences;
 import android.os.Bundle;
 import android.preference.PreferenceManager;
 import android.util.Log;

 import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String logs = sharedPreferences.getString("Login", "out");
        Log.d("valuemain",logs);
        if(logs.equals("in"))

        {   Log.d("flow","homeact");
            Intent intent = new Intent(this,HomeActivity.class);
            finish();
            startActivity(intent);
        }else
        {   Log.d("flow","loginact");
            Intent intent = new Intent(this,LoginActivity.class);
            finish();
            startActivity(intent);
        }
    }
}

