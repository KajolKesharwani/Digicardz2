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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Reviews extends AppCompatActivity {
    private TextView tvRev;
    private RatingBar rbStars;
    private EditText name;
    private EditText email;
    private EditText Mobile_no;
    private EditText Reviews;
    private Button btnSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.getOverflowIcon().setTint(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);


        tvRev = findViewById(R.id.tvRev);
        rbStars = findViewById(R.id.rbStars);

        rbStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating == 0) {
                    tvRev.setText("Very Dissatisfied");
                } else if (rating == 1) {
                    tvRev.setText("Dissatisfied");
                } else if (rating == 2 || rating == 3) {
                    tvRev.setText("OK");
                } else if (rating == 4) {
                    tvRev.setText("Satisfied");
                } else if (rating == 5) {
                    tvRev.setText("Very Satisfied");
                } else {

                }
            }
        });


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        Mobile_no = findViewById(R.id.Mobile_No);
        Reviews = findViewById(R.id.Reviews);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> v = new HashMap<>();
                v.put("name", name.getText().toString());
                v.put("email", email.getText().toString());
                v.put("Mobile_no", Mobile_no.getText().toString());
                v.put("Reviews", Reviews.getText().toString());
                FirebaseFirestore.getInstance().collection("Reviews").add(v).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(getApplicationContext(), "INSERTED", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavview);
        bottomNavigationView.setSelectedItemId(R.id.nav_reviews);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class) );
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_designs:
                        startActivity(new Intent(getApplicationContext(),Designs.class) );
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_pricing:
                        startActivity(new Intent(getApplicationContext(),Pricing.class) );
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_reviews:
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











