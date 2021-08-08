package android.com.digicardz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;




public class Contact extends AppCompatActivity {

    private EditText Firstname;
    private EditText LastName;
    private EditText email;
    private EditText Mobile_no;
    private EditText Msg;
    private Button btnSend;
     Object Spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.getOverflowIcon().setTint(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);


        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Contact.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Subjects));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        Firstname = findViewById(R.id.fnametxt);
        LastName = findViewById(R.id.lnametxt);
        email = findViewById(R.id.emailidtxt);
        Mobile_no = findViewById(R.id.phnno);
        Msg = findViewById(R.id.msgtxt);
        Spinner= findViewById(R.id.spinner1);
        btnSend = findViewById(R.id.sendbtn);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> v = new HashMap<>();
                v.put("FirstName", Firstname.getText().toString());
                v.put("LastName", LastName.getText().toString());
                v.put("email", email.getText().toString());
                v.put("Mobile_no", Mobile_no.getText().toString());
                v.put("Msg", Msg.getText().toString());
                FirebaseFirestore.getInstance().collection("Contact").add(v).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}









