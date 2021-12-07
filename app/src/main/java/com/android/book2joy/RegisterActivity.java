package com.android.book2joy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.atomic.AtomicReference;

public class RegisterActivity extends AppCompatActivity
{
	private static final String USERS = "users";
	private final String TAG = "RegisterActivity";
	private EditText nameText, retypePasswordText, passwordText;
	private EditText phoneText, emailText;
	private RadioGroup genderButton;
	private Button registerButton;
	private FirebaseDatabase database;
	private DatabaseReference mDatabase;
	private String name, email, phone;
	private AtomicReference<Character> gender;
	private String password, retypePassword;
	private User user;
	private FirebaseAuth mAuth;

	@SuppressLint("NonConstantResourceId")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		// find view by id
		nameText = findViewById(R.id.name);
		emailText = findViewById(R.id.email);
		passwordText = findViewById(R.id.password);
		retypePasswordText = findViewById(R.id.retype_password);
		phoneText = findViewById(R.id.phone);
		genderButton = findViewById(R.id.gender);
		registerButton = findViewById(R.id.register);
		// create firebase instance
		database = FirebaseDatabase.getInstance();
		mDatabase = database.getReference(USERS);
		mAuth = FirebaseAuth.getInstance();

		registerButton.setOnClickListener(v ->
		{
			//insert data into firebase database
			if (!emailText.getText().toString().isEmpty() && !passwordText.getText().toString().isEmpty())
			{
				name = nameText.getText().toString();
				email = emailText.getText().toString();
				phone = phoneText.getText().toString();
				password = passwordText.getText().toString();
				retypePassword = retypePasswordText.getText().toString();
				gender = new AtomicReference<>((char) 0);
				genderButton.setOnCheckedChangeListener((group, id) ->
				{
					switch (id)
					{
						case R.id.male:
							gender.set('m');
							break;
						case R.id.female:
							gender.set('f');
							break;
					}
				});
				user = new User(name, email, password, phone, gender.get());
				registerUser(email, password, retypePassword);
			}
		});

	}

	public void registerUser(String email, String password, String retypePassword)
	{
		final String emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		if (!email.matches(emailPattern))
			emailText.setError("Wrong pattern");
		else if (password.isEmpty() || password.length() < 6)
			passwordText.setError("Your password is too weak");
		else if (!retypePassword.equals(password))
			retypePasswordText.setError("Wrong confirm password");
		else
		{
			user = new User(name, email, password, phone, gender.get());
			mAuth.createUserWithEmailAndPassword(email, password)
					.addOnCompleteListener(this, task ->
					{
						if (task.isSuccessful())
						{
							// Sign in success, update UI with the signed-in user's information
							Log.d(TAG, "createUserWithEmail:success");
							FirebaseUser mUser = mAuth.getCurrentUser();
							assert mUser != null;
							mUser.sendEmailVerification();
							updateUI(mUser);
						} else
						{
							// If sign in fails, display a message to the user.
							Log.w(TAG, "createUserWithEmail:failure", task.getException());
							Toast.makeText(RegisterActivity.this,
									"Authentication failed.",
									Toast.LENGTH_SHORT).show();
						}
					});
		}

	}

	public void updateUI(FirebaseUser currentUser)
	{
		String keyid = mDatabase.push().getKey();
		assert keyid != null;
		mDatabase.child(keyid).setValue(user); //adding user info to database
		Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
		startActivity(loginIntent);

	}
}
