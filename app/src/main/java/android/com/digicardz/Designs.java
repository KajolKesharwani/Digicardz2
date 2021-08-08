package android.com.digicardz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Designs extends AppCompatActivity {

    ImageView des1,des2,des3,des4,des5,des6,des7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designs);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.getOverflowIcon().setTint(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        des1 = findViewById(R.id.imageView1);
        des1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Designs.this,Design1Form.class);
                startActivity(intent);

            }
        });

        des2 = findViewById(R.id.imageView2);
        des2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Designs.this,Design2form.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavview);
        bottomNavigationView.setSelectedItemId(R.id.nav_designs);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class) );
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_designs:
                        return true;
                    case R.id.nav_pricing:
                        startActivity(new Intent(getApplicationContext(),Pricing.class) );
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_reviews:
                        startActivity(new Intent(getApplicationContext(),Reviews.class) );
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){

            case R.id.abt:
                Intent intent2 = new Intent(this, About.class);
                this.startActivity(intent2);
                return true;
            case R.id.cn:
                Intent intent6 = new Intent(this, Contact.class);
                this.startActivity(intent6);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}