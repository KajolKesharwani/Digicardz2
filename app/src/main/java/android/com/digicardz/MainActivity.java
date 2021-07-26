package android.com.digicardz;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setIcon(R.drawable.mainlogo);

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
            case R.id.home:
                Intent intent1 = new Intent(this, Home.class);
                this.startActivity(intent1);
                return true;
            case R.id.abt:
                Intent intent2 = new Intent(this, About.class);
                this.startActivity(intent2);
                return true;
            case R.id.ds:
                Intent intent3 = new Intent(this, Designs.class);
                this.startActivity(intent3);
                return true;
            case R.id.pr:
                Intent intent4 =new Intent(this, Pricing.class);
                this.startActivity(intent4);
                return true;
            case R.id.rv:
                Intent intent5 = new Intent(this, Reviews.class);
                this.startActivity(intent5);
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
