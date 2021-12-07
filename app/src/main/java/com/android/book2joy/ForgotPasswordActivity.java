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
	private EditText inputEmail;
	FirebaseAuth mAuth;
	FirebaseUser mUser;
	private final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	private Button btn;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot_password);
		btn = findViewById(R.id.reset_password);
		inputEmail = findViewById(R.id.email);
		mAuth = FirebaseAuth.getInstance();
		mUser = mAuth.getCurrentUser();
		btn.setOnClickListener(v ->
		{
			String email = inputEmail.getText().toString();
			if (email.matches(EMAIL_PATTERN))
			{
				mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task ->
				{
					if (task.isSuccessful())
						Toast.makeText(this, "Validation email has been sent", Toast.LENGTH_SHORT).show();
					else
						Toast.makeText(this, "Fail to send validation email", Toast.LENGTH_SHORT).show();
				});
			}
			else
				inputEmail.setError("Wrong email pattern");
		});
	}
}

