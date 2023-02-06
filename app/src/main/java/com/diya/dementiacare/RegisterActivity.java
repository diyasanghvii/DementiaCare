package com.diya.dementiacare;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;



public class RegisterActivity extends AppCompatActivity {

    //Declaration EditTexts
    EditText editTextUserName;
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextContact1Name;
    EditText editTextContact2Name;
    EditText editTextContact1No;
    EditText editTextContact2No;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutUserName;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;
    TextInputLayout textInputLayoutContact1Name;
    TextInputLayout textInputLayoutContact2Name;
    TextInputLayout textInputLayoutContact1No;
    TextInputLayout textInputLayoutContact2No;

    //Declaration Button
    Button buttonRegister;

    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sqliteHelper = new SqliteHelper(this);
        initTextViewLogin();
        initViews();
        buttonRegister.setOnClickListener(this::onClick);
    }

    //this method used to set Login TextView click event
    private void initTextViewLogin() {
        TextView textViewLogin = (TextView) findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(view -> finish());
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextContact1Name = (EditText) findViewById(R.id.editTextContact1Name);
        editTextContact2Name = (EditText) findViewById(R.id.editTextContact2Name);
        editTextContact1No = (EditText) findViewById(R.id.editTextContact1No);
        editTextContact2No = (EditText) findViewById(R.id.editTextContact2No);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutUserName = (TextInputLayout) findViewById(R.id.textInputLayoutUserName);
        textInputLayoutContact1Name = (TextInputLayout) findViewById(R.id.textInputLayoutContact1Name);
        textInputLayoutContact2Name = (TextInputLayout) findViewById(R.id.textInputLayoutContact2Name);
        textInputLayoutContact1No = (TextInputLayout) findViewById(R.id.textInputLayoutContact1No);
        textInputLayoutContact2No = (TextInputLayout) findViewById(R.id.textInputLayoutContact2No);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid;

        //Get values from EditText fields
        String Contact1Name = editTextContact1Name.getText().toString();
        String Contact2Name = editTextContact2Name.getText().toString();
        String Contact1No = editTextContact1No.getText().toString();
        String Contact2No = editTextContact2No.getText().toString();
        String UserName = editTextUserName.getText().toString();
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        //Handling validation for UserName field
        if (UserName.isEmpty()) {
            textInputLayoutUserName.setError("Please enter valid username!");
        } else {
            if (UserName.length() > 5) {
                textInputLayoutUserName.setError(null);
            } else {
                textInputLayoutUserName.setError("Username is to short!");
            }
        }
        //Handling validation for Contact1Name field
        if (Contact1Name.isEmpty()) {
            textInputLayoutContact1Name.setError("Please enter valid Contact Name!");
        } else {
            if (Contact1Name.length() > 5) {
                textInputLayoutContact1Name.setError(null);
            } else {
                textInputLayoutContact1Name.setError("Contact Name is to short!");
            }
        }
        //Handling validation for Contact2Name field
        if (Contact2Name.isEmpty()) {
            textInputLayoutContact2Name.setError("Please enter valid Contact Name!");
        } else {
            if (Contact2Name.length() > 5) {
                textInputLayoutContact2Name.setError(null);
            } else {
                textInputLayoutContact2Name.setError("Contact Name is to short!");
            }
        }

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            textInputLayoutEmail.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password is to short!");
            }
        }
        if (Contact1No.isEmpty() || Contact1No.length() != 10) {
            valid = false;
            textInputLayoutContact1No.setError("Please enter valid phone number (10 digits)!");
        }
        else {
            valid = true;
            textInputLayoutContact1No.setError(null);
        }
        if (Contact2No.isEmpty() || Contact2No.length() != 10) {
            valid = false;
            textInputLayoutContact2No.setError("Please enter valid phone number (10 digits)!");
        }
        else {
            valid = true;
            textInputLayoutContact2No.setError(null);
        }

        return valid;
    }

    private void onClick(View view) {
        if (validate()) {
            String UserName = editTextUserName.getText().toString();
            String Email = editTextEmail.getText().toString();
            String Password = editTextPassword.getText().toString();
            String Contact1Name = editTextContact1Name.getText().toString();
            String Contact2Name = editTextContact2Name.getText().toString();
            String Contact1No = editTextContact1No.getText().toString();
            String Contact2No = editTextContact2No.getText().toString();

            //Check in the database is there any user associated with  this email
            if (!sqliteHelper.isEmailExists(Email)) {

                //Email does not exist now add new user to database
                sqliteHelper.addUser(new User(null, UserName, Email, Password, Contact1Name, Contact2Name, Contact1No, Contact2No));
                Snackbar.make(buttonRegister, "User created successfully! Please Login ", Snackbar.LENGTH_LONG).show();
                new Handler().postDelayed(this::finish, Snackbar.LENGTH_LONG);
            } else {

                //Email exists with email input provided so show error user already exist
                Snackbar.make(buttonRegister, "User already exists with same email ", Snackbar.LENGTH_LONG).show();
            }


        }
    }
}