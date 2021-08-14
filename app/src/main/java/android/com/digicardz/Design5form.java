package android.com.digicardz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Design5form extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    SliderView sliderView;
    private EditText nametxt;
    private EditText emailidtxt;
    private EditText phnnotxt;
    private TextView packagetxtview;
    private TextView colortxtview;
    private TextView servicestxtv;
    private String spinner_text;
    private EditText messagetxt;
    private Button btn;
    public String selected_val;

    boolean[] selectedday;
    ArrayList<Integer> servicelist = new ArrayList<>();
    String[] servicesarray = {"Call","Gmail","Whatsapp","Facebook","Instagram",
            "LinkedIn","Github","Twitter","Blog","Website"};
    int[] images = {R.drawable.d5s1,R.drawable.d5s2,R.drawable.d5s3,R.drawable.d5s4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design5form);

        nametxt = findViewById(R.id.nametxt);
        emailidtxt = findViewById(R.id.emailidtxt);
        phnnotxt = findViewById(R.id.phnnotxt);
        packagetxtview = findViewById(R.id.packagetxtview);
        colortxtview = findViewById(R.id.colortxtview);
        messagetxt = findViewById(R.id.messagetxt);


        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Design5form.this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.packagetype));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        mySpinner.setOnItemSelectedListener(this);

        Spinner mySpinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(Design5form.this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.d5colorpattern));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner2.setAdapter(myAdapter2);
        //  mySpinner2.setOnItemSelectedListener(this);
        mySpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?>arg0, View view, int arg2, long arg3) {

                selected_val=mySpinner2.getSelectedItem().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });




        sliderView = findViewById(R.id.image_slider);
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        servicestxtv = findViewById(R.id.servicestxt);
        selectedday = new boolean[servicesarray.length];

        servicestxtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Design5form.this);
                builder.setTitle("Services on Card");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(servicesarray, selectedday, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {

                        if(b){
                            servicelist.add(i);
                            Collections.sort(servicelist);
                        }else {
                            servicelist.remove(i);
                        }
                    }
                });
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        StringBuilder stringBuilder = new StringBuilder();

                        for (int j=0; j<servicelist.size();j++){
                            stringBuilder.append(servicesarray[servicelist.get(j)]);

                            if(j != servicelist.size()-1){
                                stringBuilder.append(", ");
                            }
                        }
                        servicestxtv.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        for(int j=0; j<selectedday.length;j++){
                            selectedday[j] = false;
                            servicelist.clear();
                            servicestxtv.setText("");
                        }
                    }
                });

                builder.show();
            }
        });

        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = nametxt.getText().toString();
                String EmailId = emailidtxt.getText().toString();
                String PhoneNumber = phnnotxt.getText().toString();
                String Package = spinner_text;
                String Color_Pattern = selected_val;
                String Service = servicestxtv.getText().toString();
                String Message = messagetxt.getText().toString();
                String Send = btn.getText().toString();
                String subject = spinner_text;

                // Toast.makeText(Contact.this, spinner_text, Toast.LENGTH_SHORT).show();

                boolean check = validateinfo(Name, EmailId, PhoneNumber, Package, Color_Pattern, Service, Message, Send);

                if (check == true) {

                    Map<String, Object> v = new HashMap<>();
                    v.put("name", Name);
                    v.put("email", EmailId);
                    v.put("Phone_no", PhoneNumber);
                    v.put("Package", Package);
                    v.put("Color_Pattern", Color_Pattern);
                    v.put("Service", Service);
                    v.put("Subject", subject);
                    v.put("Message", Message);

                    FirebaseFirestore.getInstance().collection("Form5").add(v).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(getApplicationContext(), "INSERTED", Toast.LENGTH_SHORT).show();

                        }
                    });


                }
            }
        });

    }


    private boolean validateinfo(String name, String emailId, String phoneNumber, String Package, String color_pattern, String service, String message, String send) {
        if (name.length() == 0) {
            nametxt.requestFocus();
            nametxt.setError("FIELD CANNOT BE EMPTY");
            return false;
        } else if (!name.matches("[a-zA-Z]+")) {
            nametxt.requestFocus();
            nametxt.setError("ENTER ONLY ALPHABETICAL CHARACTER");
            return false;
        } else if (emailId.length() == 0) {
            emailidtxt.requestFocus();
            emailidtxt.setError("FIELD CANNOT BE EMPTY");
            return false;
        } else if (!emailId.matches("[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            emailidtxt.requestFocus();
            emailidtxt.setError("ENTER VALID EMAIL");
            return false;
        } else if (phoneNumber.length() == 0) {
            phnnotxt.requestFocus();
            phnnotxt.setError("FIELD CANNOT BE EMPTY");
            return false;
        } else if (!phoneNumber.matches("^[0-9]{10,13}$")) {
            phnnotxt.requestFocus();
            phnnotxt.setError("correct Format: 91xxxxxxxxxx");
            return false;
        } else if (message.isEmpty()) {
            messagetxt.requestFocus();
            messagetxt.setError("Message Cannot Be Empty");
            return false;
        } else {
            return true;
        }



    }

    @Override
    public void onItemSelected (AdapterView < ? > adapterView, View view,int i, long l){
        spinner_text = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected (AdapterView < ? > adapterView){

    }
}