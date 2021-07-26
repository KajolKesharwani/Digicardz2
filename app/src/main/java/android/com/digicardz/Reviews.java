package android.com.digicardz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
        getSupportActionBar().setIcon(R.drawable.mainlogo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
    }
}











