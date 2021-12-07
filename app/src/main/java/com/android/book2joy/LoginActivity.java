package com.android.book2joy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity
{

	private static final String TAG = "LoginActivity";
	private final static int RC_SIGN_IN = 9001;
	private TextView registerText, forgotPassText;
	private EditText emailEditText;
	private EditText passwordEditText;
	private Button signInButton;
	private ImageButton googleButton, facebookButton;
	private FirebaseAuth mAuth;
	private String email, password;
	private GoogleSignInClient mGoogleSignInClient;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		registerText = findViewById(R.id.register);
		forgotPassText = findViewById(R.id.forgot_password);
		emailEditText = findViewById(R.id.email);
		passwordEditText = findViewById(R.id.password);
		signInButton = findViewById(R.id.sign_in);
		forgotPassText.setOnClickListener(v -> startActivity(new Intent(this, ForgotPasswordActivity.class)));
		mAuth = FirebaseAuth.getInstance();
		googleButton = findViewById(R.id.google);
		googleButton.setOnClickListener(v ->
				googleSignIn());
		//checking if user is logged in
		if (mAuth.getCurrentUser() != null)
		{
			updateUI(mAuth.getCurrentUser());
		}

		signInButton.setOnClickListener(v -> signIn());

		registerText.setOnClickListener(v ->
		{
			Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
			startActivity(registerIntent);
		});
	}

	private void signIn()
	{
		email = emailEditText.getText().toString();
		password = passwordEditText.getText().toString();
		mAuth.signInWithEmailAndPassword(email, password)
				.addOnCompleteListener(LoginActivity.this, task ->
				{
					if (task.isSuccessful())
					{
						// Sign in success, update UI with the signed-in user's information
						Log.d(TAG, "signInWithEmail:success");
						FirebaseUser user = mAuth.getCurrentUser();
						assert user != null;
						updateUI(user);
					} else
					{
						// If sign in fails, display a message to the user.
						Log.w(TAG, "signInWithEmail:failure", task.getException());
						Toast.makeText(getApplicationContext(), "Authentication failed.",
								Toast.LENGTH_SHORT).show();
					}
					// ...
				});
	}

	private void googleSignIn()
	{
		GoogleSignInOptions gso = new GoogleSignInOptions
				.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
				.requestIdToken(getString(R.string.default_web_client_id))
				.requestEmail()
				.build();
		// Build a GoogleSignInClient with the options specified by gso.
		mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
		Intent signInIntent = mGoogleSignInClient.getSignInIntent();
		startActivityForResult(signInIntent, RC_SIGN_IN);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		// Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
		if (requestCode == RC_SIGN_IN)
		{
			Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
			try
			{
				// Google Sign In was successful, authenticate with Firebase
				GoogleSignInAccount account = task.getResult(ApiException.class);
				Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
				firebaseAuthWithGoogle(account.getIdToken());
			}
			catch (ApiException e)
			{
				// Google Sign In failed, update UI appropriately
				Log.w(TAG, "Google sign in failed", e);
			}
		}
	}

	private void firebaseAuthWithGoogle(String idToken)
	{
		AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
		mAuth.signInWithCredential(credential)
				.addOnCompleteListener(this, task ->
				{
					if (task.isSuccessful())
					{
						// Sign in success, update UI with the signed-in user's information
						Log.d(TAG, "signInWithCredential:success");
						FirebaseUser user = mAuth.getCurrentUser();
						assert user != null;
						updateUI(user);
					} else
					{
						// If sign in fails, display a message to the user.
						Log.w(TAG, "signInWithCredential:failure", task.getException());
						updateUI(null);
					}
				});
	}

	@Override
	public void onStart()
	{
		super.onStart();
		// Check if user is signed in (non-null) and update UI accordingly.
		FirebaseUser currentUser = mAuth.getCurrentUser();
		if (currentUser != null)
		{
			updateUI(currentUser);
		}
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		// Check if user is signed in (non-null) and update UI accordingly.
		FirebaseUser currentUser = mAuth.getCurrentUser();
		if (currentUser != null)
		{
			updateUI(currentUser);
		}
	}

	public void updateUI(FirebaseUser currentUser)
	{
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		intent.putExtra("email", currentUser.getEmail());
		Log.v("DATA", currentUser.getUid());
		startActivity(intent);
	}
}
