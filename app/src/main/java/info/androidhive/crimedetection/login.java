package  info.androidhive.crimedetection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mButton;
    TextView GoToRegister;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mButton = findViewById(R.id.regBtn);
        GoToRegister = findViewById(R.id.textView2);
        fAuth = FirebaseAuth.getInstance();
        mButton.setOnClickListener(v -> {
                    String Email = mEmail.getText().toString().trim();
                    String pass = mPassword.getText().toString().trim();
                    if (TextUtils.isEmpty(Email)) {
                        mEmail.setError("please enter email");
                        return;
                    }
                    if (TextUtils.isEmpty(pass)) {
                        mPassword.setError("Please enter password");
                        return;

                    }
                    if (pass.length() < 6) {
                        mPassword.setError("please enter at least six letter password");
                    }

                    fAuth.signInWithEmailAndPassword(Email, pass).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(login.this, "Login  is successfull", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        } else {
                            Toast.makeText(login.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }

                    });
                }

        );

        GoToRegister.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), registor.class)));


    }
}