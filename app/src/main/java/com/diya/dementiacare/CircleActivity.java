package com.diya.dementiacare;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class CircleActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int REQUEST_PERMISSION = 0;
    private static final int PICK_IMAGE_REQUEST = 0;
    ImageView mImage;
    ImageView mImage2;
    ImageView mImage3;
    private Uri mImageUri;
    ConstraintLayout expandableView;
    Button arrowBtn;
    Button arrowBtn2;
    Button arrowBtn3;
    CardView cardView;
    ConstraintLayout expandableView2;
    EditText name;
    EditText desc;
    EditText info;
    EditText name2;
    EditText desc2;
    EditText info2;
    EditText name3;
    EditText desc3;
    EditText info3;
    CardView cardView2;
    ConstraintLayout expandableView3;
    
    CardView cardView3;
    ImageButton addp1;
    ImageButton addp2;
    ImageButton addp3;
    ImageButton resetp1;
    ImageButton resetp2;
    ImageButton resetp3;

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
            editcircle();
            globalMenuItem.findItem(R.id.action_save).setVisible(true);
            return true;
        }
        if (id == R.id.action_save) {
            item.setVisible(false);
            savecircle();
            globalMenuItem.findItem(R.id.action_edit).setVisible(true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void editcircle() {
         addp1.setVisibility(View.VISIBLE);
         addp2.setVisibility(View.VISIBLE);
         addp3.setVisibility(View.VISIBLE);
         resetp1.setVisibility(View.VISIBLE);
         resetp2.setVisibility(View.VISIBLE);
         resetp3.setVisibility(View.VISIBLE);
        arrowBtn.setVisibility(View.INVISIBLE);
        arrowBtn2.setVisibility(View.INVISIBLE);
        arrowBtn3.setVisibility(View.INVISIBLE);
        expandableView.setVisibility(View.VISIBLE);
        expandableView2.setVisibility(View.VISIBLE);
        expandableView3.setVisibility(View.VISIBLE);
        enableEditText(name);
        enableEditText(name2);
        enableEditText(name3);
        enableEditText(desc);
        enableEditText(desc2);
        enableEditText(desc3);
        enableEditText(info);
        enableEditText(info2);
        enableEditText(info3);

    }
    private void savecircle() {
        addp1.setVisibility(View.INVISIBLE);
        addp2.setVisibility(View.INVISIBLE);
        addp3.setVisibility(View.INVISIBLE);
        resetp1.setVisibility(View.INVISIBLE);
        resetp2.setVisibility(View.INVISIBLE);
        resetp3.setVisibility(View.INVISIBLE);
        arrowBtn.setVisibility(View.VISIBLE);
        arrowBtn2.setVisibility(View.VISIBLE);
        arrowBtn3.setVisibility(View.VISIBLE);
        expandableView.setVisibility(View.INVISIBLE);
        expandableView2.setVisibility(View.INVISIBLE);
        expandableView3.setVisibility(View.INVISIBLE);
        disableEditText(name);
        disableEditText(name2);
        disableEditText(name3);
        disableEditText(desc);
        disableEditText(desc2);
        disableEditText(desc3);
        disableEditText(info);
        disableEditText(info2);
        disableEditText(info3);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CircleActivity.this);
        SharedPreferences.Editor editor = preferences.edit();
        String temp= name.getText().toString();
        editor.putString("name1", temp);
        temp= name2.getText().toString();
        editor.putString("name2", temp);
        temp= name3.getText().toString();
        editor.putString("name3", temp);
        temp= desc.getText().toString();
        editor.putString("desc1", temp);
        temp= desc2.getText().toString();
        editor.putString("desc2", temp);
        temp= desc3.getText().toString();
        editor.putString("desc3", temp);
        temp= info.getText().toString();
        editor.putString("bond1", temp);
        temp= info2.getText().toString();
        editor.putString("bond2", temp);
        temp= info3.getText().toString();
        editor.putString("bond3", temp);
        editor.commit();

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
        setContentView(R.layout.activity_circle);
        mImage = (ImageView) findViewById(R.id.imageView);
        mImage2 = (ImageView) findViewById(R.id.imageView2);
        mImage3 = (ImageView) findViewById(R.id.imageView3);
        addp1= (ImageButton) findViewById(R.id.lbl_btn_photo);
        addp1.setOnClickListener(this);
        addp2= (ImageButton) findViewById(R.id.lbl_btn_photo2);
        addp2.setOnClickListener(this);
        addp3= (ImageButton) findViewById(R.id.lbl_btn_photo3);
        addp3.setOnClickListener(this);
        resetp1= (ImageButton) findViewById(R.id.lbl_btn_reset);
        resetp1.setOnClickListener(this);
        resetp2= (ImageButton) findViewById(R.id.lbl_btn_reset2);
        resetp2.setOnClickListener(this);
        resetp3= (ImageButton) findViewById(R.id.lbl_btn_reset3);
        resetp3.setOnClickListener(this);
        expandableView = findViewById(R.id.expandableView);
        arrowBtn = findViewById(R.id.arrowBtn);
        arrowBtn2 = findViewById(R.id.arrowBtn2);
        arrowBtn3 = findViewById(R.id.arrowBtn3);
        cardView = findViewById(R.id.cardView);
         name=findViewById(R.id.name);
         desc=findViewById(R.id.desc);
         info=findViewById(R.id.info);
         name2=findViewById(R.id.name2);
         desc2=findViewById(R.id.desc2);
         info2=findViewById(R.id.info2);
         name3=findViewById(R.id.name3);
         desc3=findViewById(R.id.desc3);
         info3=findViewById(R.id.info3);
        disableEditText(name);
        disableEditText(name2);
        disableEditText(name3);
        disableEditText(desc);
        disableEditText(desc2);
        disableEditText(desc3);
        disableEditText(info);
        disableEditText(info2);
        disableEditText(info3);
        expandableView2 = findViewById(R.id.expandableView2);
        expandableView3 = findViewById(R.id.expandableView3);
        arrowBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (expandableView.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });
        expandableView2 = findViewById(R.id.expandableView2);
        arrowBtn2 = findViewById(R.id.arrowBtn2);
        cardView2 = findViewById(R.id.cardView2);

        arrowBtn2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (expandableView2.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    expandableView2.setVisibility(View.VISIBLE);
                    arrowBtn2.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    expandableView2.setVisibility(View.GONE);
                    arrowBtn2.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });
        expandableView3 = findViewById(R.id.expandableView3);
        arrowBtn3 = findViewById(R.id.arrowBtn3);
        cardView3 = findViewById(R.id.cardView3);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mImageUri = preferences.getString("image", null);
        String mImageUri2 = preferences.getString("image2", null);
        String mImageUri3 = preferences.getString("image3", null);
        String name1 = preferences.getString("name1", null);
        String name22 = preferences.getString("name2", null);
        String name33 = preferences.getString("name3", null);
        String desc1 = preferences.getString("desc1", null);
        String desc22 = preferences.getString("desc2", null);
        String desc33 = preferences.getString("desc3", null);
        String bond1 = preferences.getString("bond1", null);
        String bond2 = preferences.getString("bond2", null);
        String bond3 = preferences.getString("bond3", null);
        arrowBtn3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (expandableView3.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView3, new AutoTransition());
                    expandableView3.setVisibility(View.VISIBLE);
                    arrowBtn3.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView3, new AutoTransition());
                    expandableView3.setVisibility(View.GONE);
                    arrowBtn3.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int hasWritePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int hasReadPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

            List<String> permissions = new ArrayList<String>();
            if (hasWritePermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            } else {
//              preferencesUtility.setString("storage", "true");
            }

            if (hasReadPermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);

            } else {
//              preferencesUtility.setString("storage", "true");
            }

            if (!permissions.isEmpty()) {
//              requestPermissions(permissions.toArray(new String[permissions.size()]), REQUEST_CODE_SOME_FEATURES_PERMISSIONS);

                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE},
                        REQUEST_PERMISSION);
            }
        }
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
        if (mImageUri3 != null) {
            mImage3.setImageURI(Uri.parse(mImageUri3));
        } else {
            mImage3.setImageResource(R.drawable.ic_launcher);
        }
        if (name1 != null) {
            name.setText(name1);
        }
        if (name22 != null) {
            name2.setText(name22);
        }
        if (name33 != null) {
            name3.setText(name33);
        }if (desc1 != null) {
            desc.setText(desc1);
        }
        if (desc22 != null) {
            desc2.setText(desc22);
        }
        if (desc33 != null) {
            desc3.setText(desc33);
        }
        if (bond1 != null) {
            info.setText(bond1);
        }
        if (bond2 != null) {
            info2.setText(bond2);
        }
        if (bond3 != null) {
            info3.setText(bond3);
        }

    }
    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setCursorVisible(false);
        editText.setEnabled(false);
    }
    private void enableEditText(EditText editText) {
        editText.setFocusableInTouchMode(true);
        editText.setCursorVisible(true);
        editText.setEnabled(true);
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
            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    public void imageSelect1(int idno) {
        permissionsCheck1();
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
    public void permissionsCheck1() {
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
                        case 1:editor.putString("image", String.valueOf(mImageUri));
                            Log.d("circle", "in 1");
                                mImage.setImageURI(mImageUri);
                                mImage.invalidate();break;
                        case 2:editor.putString("image2", String.valueOf(mImageUri));
                            mImage2.setImageURI(mImageUri);
                            mImage2.invalidate();break;
                        case 3:editor.putString("image3", String.valueOf(mImageUri));
                            mImage3.setImageURI(mImageUri);
                            mImage3.invalidate();break;
                        default:break;
                    }
                    editor.commit();

                    // Sets the ImageView with the Image URI
                }
            }
        }
    }
    public void clearData1(int idno) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        switch(idno){
            case 1:editor.putString("image", "NULL");
                mImage.setImageResource(R.drawable.ic_launcher);break;
            case 2:editor.putString("image2", "NULL");
                mImage2.setImageResource(R.drawable.ic_launcher);break;
            case 3:editor.putString("image3", "NULL");
                mImage3.setImageResource(R.drawable.ic_launcher);break;
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
                imageSelect1(idno);
                break;
            case R.id.lbl_btn_reset:
                idno=1;
                clearData1(idno);
                break;
            case R.id.lbl_btn_photo2:
                idno=2;
                imageSelect1(idno);
                break;
            case R.id.lbl_btn_reset2:
                idno=2;
                clearData1(idno);
                break;
            case R.id.lbl_btn_photo3:
                idno=3;
                imageSelect1(idno);
                break;
            case R.id.lbl_btn_reset3:
                idno=3;
                clearData1(idno);
                break;
            default:
                break;
        }
    }
}