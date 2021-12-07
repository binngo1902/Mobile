package com.android.book2joy;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.atomic.AtomicBoolean;

public class RegisterActivity extends AppCompatActivity
{
	private EditText inputEmail;
	private EditText inputPassword, inputConfirmPassword;
	ProgressDialog progressDialog;
	FirebaseAuth mAuth;
	FirebaseUser mUser;
	private final String emailPattern = "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		// find view
		Button btn = findViewById(R.id.btnRegister);
		inputEmail = findViewById(R.id.inputEmail);
		inputPassword = findViewById(R.id.inputPassword);
		inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
		progressDialog = new ProgressDialog(this);
		mAuth = FirebaseAuth.getInstance();
		mUser = mAuth.getCurrentUser();
		validateRegister();
		btn.setOnClickListener(v ->
		{
			if (validateRegister())
			{
				Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
				startActivity(intent);
			}
			else recreate();
		});
	}
	protected boolean validateRegister()
	{
		// do something
		AtomicBoolean status = new AtomicBoolean(false);
		String email = inputEmail.getText().toString();
		String password = inputPassword.getText().toString();
		String confirmPassword = inputConfirmPassword.getText().toString();
		if (!email.matches(emailPattern))
			inputEmail.setError("Wrong pattern");
		else if (password.isEmpty() || password.length()<6)
			inputPassword.setError("Wrong pattern");
		else if (!password.equals(confirmPassword))
			inputConfirmPassword.setError("Wrong confirm password");
		else
		{
			progressDialog.setMessage("Registration...");
			progressDialog.setTitle("Register");
			progressDialog.setCanceledOnTouchOutside(false);
			progressDialog.show();
			mUser.sendEmailVerification();
			mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task ->
			{
				if (task.isSuccessful())
				{
					progressDialog.dismiss();
					Toast.makeText(this, "Registration complete", Toast.LENGTH_SHORT).show();
					status.set(true);
				}
				else
				{
					progressDialog.dismiss();
					Toast.makeText(this, ""+task.getException(), Toast.LENGTH_SHORT).show();
				}
			});
		}
		return status.get();
	}

}
