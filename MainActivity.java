package com.example.zapoctak;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int verzeComp, verzeFono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;



        });


        SharedPreferences pref = this.getSharedPreferences("prefData", this.MODE_PRIVATE);

        verzeFono = pref.getInt("verzeFono", -1);
        verzeComp = 4;


        Log.d("medard", "verze fono: " +verzeFono +", verze comp: " +verzeComp);

        if(verzeFono != verzeComp) {
            init();
            Log.d("medard", "PRVNI RUN!!!!!!!!!");
        }

        Log.d("medard", "verze fono: " +verzeFono +", verze comp: " +verzeComp);


    }

    public void init() {
        SharedPreferences pref = this.getSharedPreferences("prefData", this.MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();

        editor.putInt("verzeFono", verzeComp);
        editor.apply();

        verzeFono = pref.getInt("verzeFono", -1);
    }


    public void launchLevels(View v) {
        Intent intent = new Intent(this, LevelsActivity.class);
        startActivity(intent);
        Log.d("medard", "zkouska levelu launch");

    }


    public void launchBadges(View v) {
        Intent i = new Intent(this, BadgesActivity.class);
        startActivity(i);
        Log.d("medard", "zkouska badges launch");

    }

}