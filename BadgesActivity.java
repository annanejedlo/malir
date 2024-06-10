package com.example.zapoctak;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BadgesActivity extends AppCompatActivity {

    ImageView bdg1, bdg2, bdg3, bdg4;
    TextView tx1, tx2, tx3, tx4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_badges);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bdg1 = findViewById(R.id.imgBadge1);
        bdg2 = findViewById(R.id.imgBadge2);
        bdg3 = findViewById(R.id.imgBadge3);
        bdg4 = findViewById(R.id.imgBadge4);

        tx1 = findViewById(R.id.text1);
        tx2 = findViewById(R.id.text2);
        tx3 = findViewById(R.id.text3);
        tx4 = findViewById(R.id.text4);

        nactiPlaketkyUziv();



    }

    public void launchMenu(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        Log.d("medard", "zkouska menu");

    }

    public void nactiPlaketkyUziv() {
        SharedPreferences pref = this.getSharedPreferences("prefData", this.MODE_PRIVATE);
        if (pref.getBoolean("plakLVL1", false)) {
            bdg1.setImageAlpha(255);
            tx1.setVisibility(View.INVISIBLE);
        } else {
            bdg1.setImageAlpha(50);
            tx1.setVisibility(View.VISIBLE);
        };
        if (pref.getBoolean("plakLVL2", false)) {
            bdg2.setImageAlpha(255);
            tx2.setVisibility(View.INVISIBLE);
        } else {
            bdg2.setImageAlpha(50);
            tx2.setVisibility(View.VISIBLE);
        };
        if (pref.getBoolean("plakLVL3", false)) {
            bdg3.setImageAlpha(255);
            tx3.setVisibility(View.INVISIBLE);
        } else {
            bdg3.setImageAlpha(50);
            tx3.setVisibility(View.VISIBLE);
        };
        if ((pref.getInt("hvezdyLVL1", 0) != 0) && (pref.getInt("hvezdyLVL2", 0) != 0) && (pref.getInt("hvezdyLVL3", 0) != 0)) {
            bdg4.setImageAlpha(255);
            tx4.setVisibility(View.INVISIBLE);
        } else {
            bdg4.setImageAlpha(50);
            tx4.setVisibility(View.VISIBLE);
        };
    }
}