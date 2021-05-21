package info.androidhive.crimedetection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class registor extends AppCompatActivity {
    EditText mfullName, mEmail, mPassword, mPhone;
    Button button;
    FirebaseAuth fAuth;
    TextView mgoToLogin;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registor);
        mfullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.mobileNumber);
        button = findViewById(R.id.regBtn);
        mgoToLogin = findViewById(R.id.textView2);
        fAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        button.setOnClickListener(v -> {

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
                        mPassword.setError("please enter atleast six word password");
                    }
                    fAuth.createUserWithEmailAndPassword(Email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Map<String, String> userMap = new HashMap<>();
                                userMap.put("Name", mfullName.getText().toString());
                                userMap.put("Email", Email);
                                userMap.put("Password", pass);
                                userMap.put("Phone", mPhone.getText().toString());
                                databaseReference.push().setValue(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(registor.this, "Registration is successful", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(registor.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });


                            } else {
                                Toast.makeText(registor.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }

        );
        mgoToLogin.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), login.class))
        );
    }
}