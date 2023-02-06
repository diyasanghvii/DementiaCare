package com.diya.dementiacare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



public class HomeActivity extends AppCompatActivity {

   //
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.menu_main, menu);
       return super.onCreateOptionsMenu(menu);
   }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        if (id == R.id.my_profile) {
            myprofile();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Login", "out");
        editor.apply();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    private void myprofile() {
           /* SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Login", "out");
            editor.apply();*/
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button button4 = findViewById(R.id.button4);
        Button button3 = findViewById(R.id.button3);
        Button button2 = findViewById(R.id.button2);
        Button button = findViewById(R.id.button);

        button4.setOnClickListener(view -> {
            Intent intent=new Intent(this,Reminder.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
                });
        button2.setOnClickListener(view -> {
            Intent intent=new Intent(this,CircleActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        button3.setOnClickListener(view -> {
            Intent intent=new Intent(this,QuizActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
        button.setOnClickListener(view -> {
            Intent intent=new Intent(this,SosActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
        /*buttonLogout.setOnClickListener(view -> {
            editor.putString("Login", "out");
            editor.apply();

                    //User Logged in Successfully Launch You home screen activity
                    Intent intent=new Intent(this,LoginActivity.class);
                    startActivity(intent);
                    finish();
        }
    );*/


    /*buttonLogout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent in = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(in);
        }
});*/

        /*Button Logout = (Button) findViewById(R.id.buttonLogout);

        Intent in = getIntent();
        String string = in.getStringExtra("message");
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle("Confirmation PopUp!").
                        setMessage("You sure, that you want to logout?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(getApplicationContext(),
                                        LoginActivity.class);
                                startActivity(i);
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });*/
    }
}