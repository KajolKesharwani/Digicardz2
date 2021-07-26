package android.com.digicardz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Designs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designs);
        getSupportActionBar().setIcon(R.drawable.mainlogo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}