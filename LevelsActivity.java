package com.example.zapoctak;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class LevelsActivity extends AppCompatActivity {

    Button TLlvl2, TLAClvl3;
    ImageView hv11, hv12, hv13, hv14, hv21, hv22 , hv23 , hv24 , hv31, hv32, hv33, hv34;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_levels);



        TLlvl2 = (Button) findViewById(R.id.TLAClvl2);
        TLlvl2.setClickable(false);
        hv11 = (ImageView) findViewById(R.id.hv11);
        hv12 = (ImageView) findViewById(R.id.hv12);
        hv13 = (ImageView) findViewById(R.id.hv13);
        hv14 = (ImageView) findViewById(R.id.hv14);

        TLAClvl3 = findViewById(R.id.TLAClvl3);
        TLAClvl3.setClickable(false);
        hv21 = findViewById(R.id.hv21);
        hv22 = findViewById(R.id.hv22);
        hv23 = findViewById(R.id.hv23);
        hv24 = findViewById(R.id.hv24);

        hv31 = findViewById(R.id.hv31);
        hv32 = findViewById(R.id.hv32);
        hv33 = findViewById(R.id.hv33);
        hv34 = findViewById(R.id.hv34);

        vykresliVyslUziv();

        Intent intent = getIntent();
        int level = intent.getIntExtra("level", -1);
        int pocet = intent.getIntExtra("pocet", -1);
        String kodPref = "hvezdyLVL" +level;

        if (level != -1 && pocet != -1 ) {
            if (nactiVyslUzivatele(kodPref) != 0) {
                if (pocet > nactiVyslUzivatele(kodPref)) {
                    ulozVyslUziv(kodPref, pocet);
                }
                vykresliVyslUziv();
            } else {
                vykresliVyslUziv();
                upravaHvezdicky(level, pocet);
            }

        }


            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }





    public void launchMenu(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        Log.d("medard", "launch menu");

    }

    public void launch1(View v) {
        Intent i = new Intent(this, LevelActivity.class);
        startActivity(i);
        Log.d("medard", "launch level 1");

    }




    public void upravaHvezdicky(int level, int pocet) {

        switch (level) {
            case 1:
                TLlvl2.getBackground().setTint(Color.parseColor("#FFFFFF"));
                TLlvl2.setTextColor(Color.parseColor("#000000"));
                TLlvl2.setClickable(true);
                TLlvl2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LevelsActivity.this, Level2Activity.class);
                        startActivity(intent);
                        Log.d("medard", "launch level 2 pres onclick listener");
                    }
                });
                Log.d("medard", "setnuty hvezdy v LevelsActivity pro level 1");
                switch (pocet) {
                    case 1:
                        hv11.setVisibility(View.VISIBLE);
                        ulozVyslUziv("hvezdyLVL1", 1);
                        break;
                    case 2:
                        hv11.setVisibility(View.VISIBLE);
                        hv12.setVisibility(View.VISIBLE);
                        ulozVyslUziv("hvezdyLVL1", 2);
                        break;
                    case 3:
                        hv11.setVisibility(View.VISIBLE);
                        hv12.setVisibility(View.VISIBLE);
                        hv13.setVisibility(View.VISIBLE);
                        ulozVyslUziv("hvezdyLVL1", 3);
                        break;
                    case 4:
                        hv11.setVisibility(View.VISIBLE);
                        hv12.setVisibility(View.VISIBLE);
                        hv13.setVisibility(View.VISIBLE);
                        hv14.setVisibility(View.VISIBLE);
                        ulozVyslUziv("hvezdyLVL1", 4);

                        break;
                }
                break;
            case 2:
                TLAClvl3.getBackground().setTint(Color.parseColor("#FFFFFF"));
                TLAClvl3.setTextColor(Color.parseColor("#000000"));
                TLAClvl3.setClickable(true);
                TLAClvl3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LevelsActivity.this, Level3Activity.class);
                        startActivity(intent);
                        Log.d("medard", "launch level 3 pres onclick listener");
                    }
                });
                Log.d("medard", "setnuty hvezdy v LevelsActivity pro level 2");
                switch (pocet) {
                    case 1:
                        hv21.setVisibility(View.VISIBLE);
                        ulozVyslUziv("hvezdyLVL2", 1);
                        break;
                    case 2:
                        hv21.setVisibility(View.VISIBLE);
                        hv22.setVisibility(View.VISIBLE);
                        ulozVyslUziv("hvezdyLVL2", 2);
                        break;
                    case 3:
                        hv21.setVisibility(View.VISIBLE);
                        hv22.setVisibility(View.VISIBLE);
                        hv23.setVisibility(View.VISIBLE);
                        ulozVyslUziv("hvezdyLVL2", 3);
                        break;
                    case 4:
                        hv21.setVisibility(View.VISIBLE);
                        hv22.setVisibility(View.VISIBLE);
                        hv23.setVisibility(View.VISIBLE);
                        hv24.setVisibility(View.VISIBLE);
                        ulozVyslUziv("hvezdyLVL2", 4);
                        break;
                }
                break;
            case 3:
                Log.d("medard", "setnuty hvezdy v LevelsActivity pro level 3");
                switch (pocet) {
                    case 1:
                        hv31.setVisibility(View.VISIBLE);
                        ulozVyslUziv("hvezdyLVL3", 1);
                        break;
                    case 2:
                        hv31.setVisibility(View.VISIBLE);
                        hv32.setVisibility(View.VISIBLE);
                        ulozVyslUziv("hvezdyLVL3", 2);
                        break;
                    case 3:
                        hv31.setVisibility(View.VISIBLE);
                        hv32.setVisibility(View.VISIBLE);
                        hv33.setVisibility(View.VISIBLE);
                        ulozVyslUziv("hvezdyLVL3", 3);
                        break;
                    case 4:
                        hv31.setVisibility(View.VISIBLE);
                        hv32.setVisibility(View.VISIBLE);
                        hv33.setVisibility(View.VISIBLE);
                        hv34.setVisibility(View.VISIBLE);
                        ulozVyslUziv("hvezdyLVL3", 4);
                        break;
                }

        }

    }



    private void ulozVyslUziv(String klic, int hodnota) {
        SharedPreferences pref = this.getSharedPreferences("prefData", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(klic, hodnota);
        editor.apply();
    }

    private int nactiVyslUzivatele(String klic) {
        SharedPreferences pref = this.getSharedPreferences("prefData", this.MODE_PRIVATE);
        return pref.getInt(klic, 0);
    }

    private void vykresliVyslUziv() {
        //SharedPreferences prefs = this.getSharedPreferences("prefData", this.MODE_PRIVATE);
        if (nactiVyslUzivatele("hvezdyLVL1") != 0) {
            upravaHvezdicky(1, nactiVyslUzivatele("hvezdyLVL1"));
            if (nactiVyslUzivatele("hvezdyLVL2") != 0) {
                upravaHvezdicky(2, nactiVyslUzivatele("hvezdyLVL2"));
                if (nactiVyslUzivatele("hvezdyLVL3") != 0) {
                    upravaHvezdicky(3, nactiVyslUzivatele("hvezdyLVL3"));
                }
            }
        }
    }

}