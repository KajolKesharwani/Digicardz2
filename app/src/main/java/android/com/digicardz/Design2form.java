package android.com.digicardz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Collections;

public class Design2form extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String spinner_text;
    SliderView sliderView;
    TextView servicestxtv;
    boolean[] selectedday;
    ArrayList<Integer> servicelist = new ArrayList<>();
    String[] servicesarray = {"Call","Gmail","Whatsapp","Facebook","Instagram",
            "LinkedIn","Github","Twitter","Blog","Website"};
    int[] images = {R.drawable.d2s1,R.drawable.d2s2,R.drawable.d2s3,R.drawable.d2s4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design2form);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Design2form.this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.packagetype));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        mySpinner.setOnItemSelectedListener(this);

        Spinner mySpinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(Design2form.this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.d2colorpattern));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner2.setAdapter(myAdapter2);
        mySpinner2.setOnItemSelectedListener(this);

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
                AlertDialog.Builder builder = new AlertDialog.Builder(Design2form.this);
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
    }

    @Override
    public void onItemSelected (AdapterView< ? > adapterView, View view, int i, long l){
        spinner_text = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected (AdapterView < ? > adapterView){

    }
}