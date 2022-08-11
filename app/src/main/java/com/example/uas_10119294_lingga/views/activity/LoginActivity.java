package com.example.uas_10119294_lingga.views.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uas_10119294_lingga.MainActivity;
import com.example.uas_10119294_lingga.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextView txtRegister;
    private TextView txtEmail;
    private TextView txtPassword;
    private Button btnlogin;
    private FirebaseAuth mAuth;
    private ProgressBar progressbar;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtRegister = findViewById(R.id.txtDaftar);
        btnlogin = findViewById(R.id.btnLogin);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        progressbar = findViewById(R.id.progressBar);
        linearLayout = findViewById(R.id.linearLayout);
        mAuth = FirebaseAuth.getInstance();

        txtRegister.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        btnlogin.setOnClickListener(view -> {
            if(!validateForm()){
                return;
            }
            login(txtEmail.getText().toString(), txtPassword.getText().toString());
        });
    }

    public void login(String email,String password){
        linearLayout.setVisibility(View.GONE);
        progressbar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("AUTH", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            progressbar.setVisibility(View.GONE);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("AUTH", "createUserWithEmail:failure", task.getException());
                            progressbar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Username atau password salah.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                });
    }

    public boolean validateForm(){
         boolean valid = true;
         String email = txtEmail.getText().toString();
         String password = txtPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            txtEmail.setError("Tidak Boleh Kosong");
            valid = false;
        }else{
            txtEmail.setError(null);
        }

        if(TextUtils.isEmpty(password)){
            txtPassword.setError("Tidak Boleh Kosong");
            valid = false;
        }else{
            txtEmail.setError(null);
        }
        return valid;
    }
}