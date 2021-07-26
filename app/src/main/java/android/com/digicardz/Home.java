package android.com.digicardz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setIcon(R.drawable.mainlogo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}