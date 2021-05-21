package info.androidhive.crimedetection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity5 extends AppCompatActivity {
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        imageView=findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.dahanu401602);
    }
}