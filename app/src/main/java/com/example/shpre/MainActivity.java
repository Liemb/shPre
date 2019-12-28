package com.example.shpre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView counttv;
    int x=0, count;
    EditText nametext;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counttv=(TextView) findViewById(R.id.tvcount);
        nametext=(EditText) findViewById(R.id.et);

        SharedPreferences settings= getSharedPreferences("prefs_name", MODE_PRIVATE);
        count=settings.getInt("count", -10);
        name=settings.getString("name", "");
        nametext.setText(name);
        if(count==-10)
            Toast.makeText(this, "there is an error in the shared files", Toast.LENGTH_SHORT).show();
        else {
            counttv.setText("clicks number:"+count);
            x=count;
        }
    }

    /**
     * this counts how many times the button "count" has been clicked and puts it in the text view.
     */
    public void count(View view) {
        x++;
        counttv.setText("clicks number:"+x);
    }

    /**
     * resetting the text view.
     */
    public void reset(View view) {
        x=0;
        counttv.setText("count");
    }

    /**
     * saving the last data that was entered and then exiting the app.
     */
    public void exit(View view) {
        String name=nametext.getText().toString();
        SharedPreferences settings= getSharedPreferences("prefs_name", MODE_PRIVATE);
        SharedPreferences.Editor editor= settings.edit();
        editor.putInt("count", x);
        editor.putString("name",name);
        editor.commit();
        finish();
    }


    /**
     * inflate the options menu
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * when choosing "credits" this will move to the credits
     * @param menu
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String st = menu.getTitle().toString();
        if ((st.equals("credits"))) {
            Intent gi = new Intent(this, credits.class);
            startActivity(gi);
        }
        return true;
    }

}