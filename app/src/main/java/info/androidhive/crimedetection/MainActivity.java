package info.androidhive.crimedetection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    EditText editText;
    Button button,mbutton;
    Toolbar toolbar;
    private FirebaseAuth mAuth;
int pin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        mAuth = FirebaseAuth.getInstance();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

        editText = findViewById(R.id.editTextNumber);
        mbutton=findViewById(R.id.button2);
        mbutton.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),emergency.class ));
        });
        button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            int i=Integer.parseInt(editText.getText().toString());
           if(i==401501){
               startActivity(new Intent(getApplicationContext(),MainActivity2.class ));
               return;
           }
            else if(i==401209){
                startActivity(new Intent(getApplicationContext(),MainActivity3.class ));
               return;
            }
            else if(i==401305){
                startActivity(new Intent(getApplicationContext(),MainActivity4.class ));
               return;
            }
            else if(i==401602){
                startActivity(new Intent(getApplicationContext(),MainActivity5.class ));
               return;
            }
            else if(i==401404){
                startActivity(new Intent(getApplicationContext(),MainActivity6.class ));
               return;
            }
            else {
                Toast.makeText(this, "No data for this pincode", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_logout) {
            mAuth.signOut();
            startActivity(new Intent(this, registor.class));
            finish();
        }

        if(item.getItemId()==R.id.contactus)
        {
            sendEmail("rajvalmiki01@gmail.com");

        }
        if (item.getItemId() == R.id.about) {
            mAuth.signOut();
            startActivity(new Intent(this, About.class));
        }

        if(item.getItemId()==R.id.complaint)
        {
            complaint();

        }

        return true;
    }

    private void complaint() {
        String url = "https://citizen.mahapolice.gov.in/Citizen/MH/index.aspx";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }

    private void sendEmail(String subject) {
        Intent gmail = new  Intent(
                Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", "", null)
        );
        gmail.putExtra(Intent.EXTRA_SUBJECT, subject);
        gmail.putExtra(Intent.EXTRA_TEXT, "");
        try {
            startActivity(Intent.createChooser(gmail, "Send Email"));
        } catch (ActivityNotFoundException e) {
            //Toaster.show(this, "There are no email clients installed.")
        }
    }


}