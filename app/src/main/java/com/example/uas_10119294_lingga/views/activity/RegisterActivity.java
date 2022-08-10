package com.example.uas_10119294_lingga.views.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uas_10119294_lingga.MainActivity;
import com.example.uas_10119294_lingga.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView txtNama;
    private TextView txtEmail;
    private TextView txtPassword;
    private TextView txtConfirmPass;
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        txtNama = findViewById(R.id.txtNama);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPass = findViewById(R.id.confirmPassword);
        btnRegister = findViewById(R.id.btnRegis);

        btnRegister.setOnClickListener(view -> registerUser());
    }

    private void registerUser(){
        if(!validateForm()){
            return;
        }

        mAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(),txtPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("AUTH", "createUser:success");
//                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("AUTH", "signInWithCustomToken:failure", task.getException());
                    Toast.makeText(RegisterActivity.this, "Register failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateForm(){
        boolean valid = true;
        String email = txtEmail.getText().toString();
        String nama = txtNama.getText().toString();
        String password = txtPassword.getText().toString();
        String confPassword = txtConfirmPass.getText().toString();
        if(TextUtils.isEmpty(email)){
            txtEmail.setError("Required");
            valid = false;
        }else{
            txtEmail.setError(null);
        }
        if( TextUtils.isEmpty(nama)){
            txtNama.setError("Required");
            valid = false;
        }else{
            txtNama.setError(null);
        }
        if( TextUtils.isEmpty(password)){
            txtPassword.setError("Required");
            valid = false;
        }else{
            txtPassword.setError(null);
        }
        if(!TextUtils.equals(password,confPassword)){
            txtPassword.setError("passwod tidak sama");
            valid = false;
        }
        return valid;
    }

    private void showError(EditText inpt){
        inpt.setError("Required");
    }
}