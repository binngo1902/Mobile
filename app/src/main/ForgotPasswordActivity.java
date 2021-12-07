package com.android.book2joy;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPasswordActivity extends AppCompatActivity
{
	private EditText email;
	FirebaseAuth mAuth;
	FirebaseUser mUser;
	private final String emailPattern = "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";
	private Button btn;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot_password);
		btn = findViewById(R.id.btnResetPass);
		email = findViewById(R.id.inputEmail);
		mAuth = FirebaseAuth.getInstance();
		mUser = mAuth.getCurrentUser();
		btn.setOnClickListener(v ->
		{
			String stringEmail = email.getText().toString();
			if (stringEmail.matches(emailPattern))
			{
				mAuth.sendPasswordResetEmail(stringEmail).addOnCompleteListener(task ->
				{
					if (task.isSuccessful())
						Toast.makeText(this, "Validate email has been sent", Toast.LENGTH_SHORT).show();
					else
						Toast.makeText(this, "Fail to send validate email", Toast.LENGTH_SHORT).show();
				});
			}
			else
				Toast.makeText(this, "Wrong email pattern", Toast.LENGTH_SHORT).show();
		});
	}
}
