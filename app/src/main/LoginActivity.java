package com.android.book2joy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity
{
	private EditText email;
	private EditText password;
	private TextView forgotPass;
	private FirebaseAuth mAuth;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Button btn = findViewById(R.id.btnSignUp);
		email = findViewById(R.id.inputEmail);
		password = findViewById(R.id.inputPassword);
		mAuth = FirebaseAuth.getInstance();
		forgotPass = findViewById(R.id.forgotPassword);
		btn.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

		forgotPass.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)));
	}
	public void onStart() {
		super.onStart();
		// Check if user is signed in (non-null) and update UI accordingly.
		FirebaseUser currentUser = mAuth.getCurrentUser();
		updateUI(currentUser);
	}

	private void updateUI(FirebaseUser currentUser)
	{
	}
}




