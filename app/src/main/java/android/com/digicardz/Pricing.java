package android.com.digicardz;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Pricing extends AppCompatActivity {

    ConstraintLayout expandableview,expandableview2,expandableview3,expandableview4;
    Button arrowbtn,arrowbtn2,arrowbtn3,arrowbtn4;
    CardView cardview,cardview2,cardview3,cardview4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricing);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.getOverflowIcon().setTint(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        expandableview = findViewById(R.id.expandableView);
        arrowbtn = findViewById(R.id.arrowbtn);
        cardview = findViewById(R.id.cardView);
        arrowbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if(expandableview.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardview, new AutoTransition());
                    expandableview.setVisibility(View.VISIBLE);
                    arrowbtn.setBackgroundResource(R.drawable.uparrow);
                }else{
                    TransitionManager.beginDelayedTransition(cardview, new AutoTransition());
                    expandableview.setVisibility(View.GONE);
                    arrowbtn.setBackgroundResource(R.drawable.downarrow);
                }
            }
        });

        expandableview2 = findViewById(R.id.expandableView2);
        arrowbtn2 = findViewById(R.id.arrowbtn2);
        cardview2 = findViewById(R.id.cardView2);
        arrowbtn2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if(expandableview2.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardview2, new AutoTransition());
                    expandableview2.setVisibility(View.VISIBLE);
                    arrowbtn2.setBackgroundResource(R.drawable.uparrow);
                }else{
                    TransitionManager.beginDelayedTransition(cardview2, new AutoTransition());
                    expandableview2.setVisibility(View.GONE);
                    arrowbtn2.setBackgroundResource(R.drawable.downarrow);
                }
            }
        });

        expandableview3 = findViewById(R.id.expandableView3);
        arrowbtn3 = findViewById(R.id.arrowbtn3);
        cardview3 = findViewById(R.id.cardView3);
        arrowbtn3.setOnClickListener(v -> {
            if(expandableview3.getVisibility()==View.GONE){
                TransitionManager.beginDelayedTransition(cardview3, new AutoTransition());
                expandableview3.setVisibility(View.VISIBLE);
                arrowbtn3.setBackgroundResource(R.drawable.uparrow);
            }else{
                TransitionManager.beginDelayedTransition(cardview3, new AutoTransition());
                expandableview3.setVisibility(View.GONE);
                arrowbtn3.setBackgroundResource(R.drawable.downarrow);
            }
        });

        expandableview4 = findViewById(R.id.expandableView4);
        arrowbtn4 = findViewById(R.id.arrowbtn4);
        cardview4 = findViewById(R.id.cardView4);
        arrowbtn4.setOnClickListener(v -> {
            if(expandableview4.getVisibility()==View.GONE){
                TransitionManager.beginDelayedTransition(cardview4, new AutoTransition());
                expandableview4.setVisibility(View.VISIBLE);
                arrowbtn4.setBackgroundResource(R.drawable.uparrow);
            }else{
                TransitionManager.beginDelayedTransition(cardview4, new AutoTransition());
                expandableview4.setVisibility(View.GONE);
                arrowbtn4.setBackgroundResource(R.drawable.downarrow);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavview);
        bottomNavigationView.setSelectedItemId(R.id.nav_pricing);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(),Pricing.class) );
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_designs:
                        startActivity(new Intent(getApplicationContext(),Designs.class) );
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_pricing:
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