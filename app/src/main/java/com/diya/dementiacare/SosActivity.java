package com.diya.dementiacare;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class SosActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int REQUEST_PERMISSION = 0;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private static final int PERMISSION_REQUEST_CODE = 2;
    SqliteHelper mydb;
    ImageView mImage;
    ImageView mImage2;
    private Uri mImageUri;
    ImageButton addp1;
    ImageButton addp2;
    ImageButton resetp1;
    ImageButton resetp2;
    EditText name3;
    EditText name;
    String cna1;
    String cna2;
    String cno1;
    String cno2;
    String pname;


    private Menu globalMenuItem;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_circle, menu);
        globalMenuItem = menu;
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        if (id == R.id.go_home) {
            gohome();
            return true;
        }
        if (id == R.id.action_edit) {
            item.setVisible(false);
            editsos();
            globalMenuItem.findItem(R.id.action_save).setVisible(true);
            return true;
        }
        if (id == R.id.action_save) {
            item.setVisible(false);
            savesos();
            globalMenuItem.findItem(R.id.action_edit).setVisible(true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void editsos() {
        addp1.setVisibility(View.VISIBLE);
        addp2.setVisibility(View.VISIBLE);
        resetp1.setVisibility(View.VISIBLE);
        resetp2.setVisibility(View.VISIBLE);
    }
    private void savesos() {
        addp1.setVisibility(View.INVISIBLE);
        addp2.setVisibility(View.INVISIBLE);
        resetp1.setVisibility(View.INVISIBLE);
        resetp2.setVisibility(View.INVISIBLE);
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
    private void gohome() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, HomeActivity.class);
        startActivity(setIntent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        mImage = (ImageView) findViewById(R.id.imageView);
        mImage2 = (ImageView) findViewById(R.id.imageView3);
        name=findViewById(R.id.name);
        name3=findViewById(R.id.name3);
        addp1= (ImageButton) findViewById(R.id.lbl_btn_photo);
        addp1.setOnClickListener(this);
        addp2= (ImageButton) findViewById(R.id.lbl_btn_photo3);
        addp2.setOnClickListener(this);
        resetp1= (ImageButton) findViewById(R.id.lbl_btn_reset);
        resetp1.setOnClickListener(this);
        resetp2= (ImageButton) findViewById(R.id.lbl_btn_reset3);
        resetp2.setOnClickListener(this);
        ImageButton call1= (ImageButton) findViewById(R.id.imageButton);
        ImageButton msg1= (ImageButton) findViewById(R.id.imageButton2);
        ImageButton call2= (ImageButton) findViewById(R.id.imageButton4);
        ImageButton msg2= (ImageButton) findViewById(R.id.imageButton5);

        ArrayList arr;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mImageUri = preferences.getString("sosimage", null);
        String mImageUri2 = preferences.getString("sosimage2", null);
        String useremail = preferences.getString("useremail", null);
        mydb = new SqliteHelper(this);
        if (useremail != null) {
        arr = mydb.fetch(useremail);
        cna1= (String) arr.get(0);
        cna2= (String) arr.get(1);
        cno1= (String) arr.get(2);
        cno2= (String) arr.get(3);
        pname= (String) arr.get(4);
            if (cna1 != null) {
                name.setText(cna1);
            }
            if (cna2 != null) {
                name3.setText(cna2);
            }}
        if (mImageUri != null) {
            mImage.setImageURI(Uri.parse(mImageUri));
        } else {
            mImage.setImageResource(R.drawable.ic_launcher);
        }
        if (mImageUri2 != null) {
            mImage2.setImageURI(Uri.parse(mImageUri2));
        } else {
            mImage2.setImageResource(R.drawable.ic_launcher);
        }
        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+cno1));
                if (ContextCompat.checkSelfPermission(SosActivity.this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(SosActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);

                    // MY_PERMISSIONS_REQUEST_CALL_PHONE is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                } else {
                    //You already have permission
                    try {
                        startActivity(callIntent);
                    } catch(SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+cno2));
                if (ContextCompat.checkSelfPermission(SosActivity.this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(SosActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);

                    // MY_PERMISSIONS_REQUEST_CALL_PHONE is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                } else {
                    //You already have permission
                    try {
                        startActivity(callIntent);
                    } catch(SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        msg1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_DENIED) {

                    Log.d("permission", "permission denied to SEND_SMS - requesting it");
                    String[] permissions = {Manifest.permission.SEND_SMS};

                    ActivityCompat.requestPermissions(SosActivity.this,permissions, PERMISSION_REQUEST_CODE);

                }
                Intent intent=new Intent(getApplicationContext(),SosActivity.class);
                PendingIntent pi= PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
                SmsManager sms= SmsManager.getDefault();
                sms.sendTextMessage(cno1, null, "Distress sms sent by "+pname+" via DementiaCare. It could be an emergency, please contact the center.", pi,null);
            }
        });
        msg2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_DENIED) {

                    Log.d("permission", "permission denied to SEND_SMS - requesting it");
                    String[] permissions = {Manifest.permission.SEND_SMS};

                    ActivityCompat.requestPermissions(SosActivity.this,permissions, PERMISSION_REQUEST_CODE);

                }
                Intent intent=new Intent(getApplicationContext(),SosActivity.class);
                PendingIntent pi= PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
                SmsManager sms= SmsManager.getDefault();
                sms.sendTextMessage(cno2, null, "Distress sms sent by "+pname+" via DementiaCare. It could be an emergency, please contact the center.", pi,null);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION: {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {


                        System.out.println("Permissions --> " + "Permission Granted: " + permissions[i]);
                    } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        System.out.println("Permissions --> " + "Permission Denied: " + permissions[i]);
                    }
                }
            }
            break;
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the phone call

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case PERMISSION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the phone call

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request

            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    public void imageSelect2(int idno) {
        permissionsCheck2();
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*").addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);


        startActivityForResult(Intent.createChooser(intent, "Select Picture"), idno);
    }
    public void permissionsCheck2() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 0) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a image.
                // The Intent's data Uri identifies which item was selected.
                if (data != null) {int idno=requestCode;
                    // This is the key line item, URI specifies the name of the data
                    mImageUri = data.getData();
                    getContentResolver().takePersistableUriPermission(mImageUri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    Log.d("circle", String.valueOf(idno));
                    // Saves image URI as string to Default Shared Preferences
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = preferences.edit();
                    switch(idno){
                        case 1:editor.putString("sosimage", String.valueOf(mImageUri));
                            Log.d("circle", "in 1");
                            mImage.setImageURI(mImageUri);
                            mImage.invalidate();break;
                        case 2:editor.putString("sosimage2", String.valueOf(mImageUri));
                            mImage2.setImageURI(mImageUri);
                            mImage2.invalidate();break;
                        default:break;
                    }
                    editor.commit();

                    // Sets the ImageView with the Image URI
                }
            }
        }
    }
    public void clearData2(int idno) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        switch(idno){
            case 1:editor.putString("sosimage", "NULL");
                mImage.setImageResource(R.drawable.ic_launcher);break;
            case 2:editor.putString("sosimage2", "NULL");
                mImage2.setImageResource(R.drawable.ic_launcher);break;
            default:break;
        }
        editor.commit();
    }
    @Override
    public void onClick(View view) {
        int idno=0;
        switch (view.getId()) {
            case R.id.lbl_btn_photo:
                idno=1;
                Log.d("circle", String.valueOf(idno));
                imageSelect2(idno);
                break;
            case R.id.lbl_btn_reset:
                idno=1;
                clearData2(idno);
                break;
            case R.id.lbl_btn_photo3:
                idno=2;
                imageSelect2(idno);
                break;
            case R.id.lbl_btn_reset3:
                idno=2;
                clearData2(idno);
                break;

            default:
                break;
        }
    }
}