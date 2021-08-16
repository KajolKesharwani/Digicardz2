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
    private TextView Reviews;
    private RatingBar rbStars;
    private EditText name;
    private EditText email;
    private EditText Mobile_no;
    private EditText Msg;
    private Button btnSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.getOverflowIcon().setTint(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);


        Reviews = findViewById(R.id.Reviews);
        rbStars = findViewById(R.id.rbStars);

        rbStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating == 0) {
                    Reviews.setText("Very Dissatisfied");
                } else if (rating == 1) {
                    Reviews.setText("Dissatisfied");
                } else if (rating == 2 || rating == 3) {
                    Reviews.setText("OK");
                } else if (rating == 4) {
                    Reviews.setText("Satisfied");
                } else if (rating == 5) {
                    Reviews.setText("Very Satisfied");
                } else {

                }
            }
        });


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        Mobile_no = findViewById(R.id.Mobile_No);
        Msg= findViewById(R.id.Msg);
        Reviews = findViewById(R.id.Reviews);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String Name = name.getText().toString();
                    String EmailId = email.getText().toString();
                    String MobileNumber = Mobile_no.getText().toString();
                    String Message = Msg.getText().toString();
                    String Rev = Reviews.getText().toString();
                    String Send = btnSend.getText().toString();




                boolean check = validateinfo(Name,  EmailId, MobileNumber, MobileNumber, Message, Send);

                if (check == true) {
                Map<String, Object> v = new HashMap<>();
                v.put("name", Name);
                v.put("email", EmailId);
                v.put("Mobile_no", MobileNumber);
                v.put("Message", Message);
                v.put("Rev", Rev);
                FirebaseFirestore.getInstance().collection("Reviews").add(v).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(getApplicationContext(), "INSERTED", Toast.LENGTH_SHORT).show();
                    }
                });



                }
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

    private boolean validateinfo (String Name,  String emailId, String
            mobileNumber, String message, String Rev, String send)
    {
        if (Name.length() == 0) {
            name.requestFocus();
            name.setError("FIELD CANNOT BE EMPTY");
            return false;
        } else if (!Name.matches("[a-zA-Z]+")) {
            name.requestFocus();
            name.setError("ENTER ONLY ALPHABETICAL CHARACTER");
            return false;
        } else if (emailId.length() == 0) {
            email.requestFocus();
            email.setError("FIELD CANNOT BE EMPTY");
            return false;
        } else if (!emailId.matches("[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            email.requestFocus();
            email.setError("ENTER VALID EMAIL");
            return false;
        } else if (mobileNumber.length() == 0) {
            Mobile_no.requestFocus();
            Mobile_no.setError("FIELD CANNOT BE EMPTY");
            return false;
        } else if (!mobileNumber.matches("^[0-9]{10,13}$")) {
            Mobile_no.requestFocus();
            Mobile_no.setError("correct Format: xxxxxxxxxx");
            return false;
        } else if (message.isEmpty()) {
            Msg.requestFocus();
            Msg.setError("Message Cannot Be Empty");
            return false;
        } else if (Rev.isEmpty()) {
            Reviews.requestFocus();
            Reviews.setError("Rev Cannot Be Empty");
            return false;

        } else {
            return true;
        }
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












