package info.androidhive.crimedetection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class emergency extends AppCompatActivity {
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        imageView=findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.emergency);

    }
}