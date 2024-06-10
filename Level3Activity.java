package com.example.zapoctak;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Level3Activity extends AppCompatActivity {




    Button barva;
    Button vyhodnoceni;
    Platno platno;

    Bitmap sample;
    Bitmap submitted;

    int tolerance = 50;


    SeekBar sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_level3);

        vyhodnoceni = findViewById(R.id.TLACvyhodnotit);



        barva = findViewById(R.id.barva);
        if (barva.performClick()) {
            Log.d("medard", "klinuto na barvu");
        };
        sb = findViewById(R.id.SBvelikost);
        sb.setMax(100);
        upozorneni();


        platno = findViewById(R.id.platno);
        ToggleButton guma= findViewById(R.id.gumaToggle);
        guma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                platno.nastavGumu(guma.isChecked());
            }
        });


        sample = BitmapFactory.decodeResource(getResources(), R.drawable.vincent_sample);




        vyhodnoceni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitted = platno.mBitmapa;
                if (submitted != null) {
                    Log.d("medard", submitted.getClass().getName());
                } else {
                    Log.d("medard", "submitted je nula");
                }

                Log.d("medard","kliknuto na vyhodnoceni");
                //vyhodnoceni();
                float similarity = porovnaniBM(submitted, sample);
                hvezdicky(similarity);

            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });








        final int[] cisloBarvy = {0};

        barva.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                switch(cisloBarvy[0]) {
                    case 0:
                        zmenBarvu("1", Color.rgb(49, 77, 80));
                        cisloBarvy[0]++;
                        Log.d("medard", "zmena barvy na 1");
                        break;
                    case 1:
                        zmenBarvu("2", Color.rgb(190, 158, 73));
                        cisloBarvy[0]++;
                        Log.d("medard", "zmena barvy na 2");
                        break;
                    case 2:
                        zmenBarvu("3", Color.rgb(42, 51, 46));
                        cisloBarvy[0]++;
                        Log.d("medard", "zmena barvy na 3");
                        break;
                    case 3:
                        zmenBarvu("4", Color.rgb(199,194,191));
                        cisloBarvy[0] = 0;
                        Log.d("medard", "zmena barvy na 3");
                        break;
                }

            }} );

        Button navod = findViewById(R.id.TLACnavod);
        navod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("medard", "kliknuto na navod");
                new AlertDialog.Builder(Level3Activity.this)
                        .setTitle("návod")
                        .setMessage("Pomocí tlačítek nahoře měň barvy, tloušťku štětce a využívej gumu. Pomocí tlačítek dole si zobraz předlohu, znovu tento návod a výsledně vyhodnoť podobnost své malby vůči originálu.")
                        .setPositiveButton("chápu", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing
                            }
                        }).show();
            }
        });

        Button pred = findViewById(R.id.TLACpredloha);
        pred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("medard", "kliknuto na predlohu");
                if (pred.getText().toString().equals("předloha")) {
                    Log.d("medard", "zobrazena predloha");
                    platno.setVisibility(View.INVISIBLE);
                    pred.setText("skrýt předlohu");
                } else if (pred.getText().toString().equals("skrýt předlohu")) {
                    Log.d("medard", "skryta predloha");
                    platno.setVisibility(View.VISIBLE);
                    pred.setText("předloha");
                } else {
                    Log.d("medard", "chyba v zobrazovani predlohy");
                }

            }
        });

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                platno.zmenVelikost(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        barva.performClick();




    }

    private void upozorneni() {
        if (nactiVyslUzivatele("hvezdyLVL3") == 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Úroveň 3")
                    .setMessage("Pokus se domalovat zbytek známé malby. Pomocí tlačítek nahoře měň barvy, tloušťku štětce a využívej gumu. Pomocí tlačítek dole si zobraz předlohu, znovu tento návod a výsledně vyhodnoť podobnost své malby vůči originálu. Cítíš se na to? ")
                    .setPositiveButton("ano", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing
                        }
                    })
                    .setNegativeButton("ne", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Start LevelsActivity
                            Intent intent = new Intent(Level3Activity.this, LevelsActivity.class);
                            startActivity(intent);
                        }
                    })
                    .show();
        }
    }



    private void zmenBarvu (String nazev, int kod) {
        barva.setBackgroundColor(kod);
        barva.setText(nazev);
        platno.zmenStetec(kod);

    }


    public float porovnaniBM(Bitmap submitted, Bitmap sample) {

        Log.d("medard","zahajeno porovnavani pixelu bitmap");
        if (submitted.getWidth() != sample.getWidth() || submitted.getHeight() != sample.getHeight()) {
            Log.d("medard","nesedi velikosti bitmap");
            Log.d("medard","width submitted: " +submitted.getWidth() +", height submitted: " + submitted.getHeight());
            Log.d("medard","width sample: " +sample.getWidth() +", height sample: " + sample.getHeight());
            return 0;
        } else {

            Log.d("medard","velikosti bitmap ok");
        }

        int width = submitted.getWidth();
        int height = submitted.getHeight();
        int totalPixels = width * height;
        int matchingPixels = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (areColorsSimilar(submitted.getPixel(x, y), sample.getPixel(x, y))) {
                    matchingPixels++;

                }
            }
        }
        Log.d("medard","matching pixels: " + matchingPixels);
        Log.d("medard","all pixels: " + totalPixels);
        float uspesnost = ((float) matchingPixels / totalPixels);
        Log.d("medard","uspesnost: " + uspesnost);


        return (uspesnost * 100);
    }

    private boolean areColorsSimilar(int color1, int color2) {
        int r1 = (color1 >> 16) & 0xff;
        int g1 = (color1 >> 8) & 0xff;
        int b1 = color1 & 0xff;

        int r2 = (color2 >> 16) & 0xff;
        int g2 = (color2 >> 8) & 0xff;
        int b2 = color2 & 0xff;

        //Log.d("medard", "barvicky v arecolorsimilar " +color1 +" a " +color2);

        return Math.abs(r1 - r2) <= tolerance &&
                Math.abs(g1 - g2) <= tolerance &&
                Math.abs(b1 - b2) <= tolerance;
    }

    private void hvezdicky(float sim) {
        Log.d("medard", "zavolana fce hvezdicky");
        if (sim < 50) {
            new AlertDialog.Builder(Level3Activity.this)
                    .setTitle("vyhodnocení")
                    .setMessage("bohužel! máš pouze " +sim +"% shodu. Level si musíš zopakovat.")
                    .setPositiveButton("zopakovat level", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(Level3Activity.this, Level3Activity.class);
                            startActivity(i);
                            Log.d("medard", "opakuju level");
                        }
                    })
                    .setNegativeButton("zpět na výběr levelů", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(Level3Activity.this, LevelsActivity.class);
                            startActivity(i);
                            Log.d("medard", "zpatky na levely");
                        }
                    }).show();
        } else {
            String mess;
            int hvezdy;
            if (sim < 65) {
                hvezdy = 1;
                mess = "gratuluji! máš " + Math.round(sim) + "% shodu! Získáváš " + hvezdy + " hvězdičku";
            } else if (sim < 75) {
                hvezdy = 2;
                mess = "gratuluji! máš " + Math.round(sim) + "% shodu! Získáváš " + hvezdy + " hvězdičky";
            } else if (sim < 85) {
                hvezdy = 3;
                mess = "gratuluji! máš " + Math.round(sim) + "% shodu! Získáváš " + hvezdy + " hvězdičky";
            } else {
                hvezdy = 4;
                mess = "gratuluji! máš " + Math.round(sim) + "% shodu! Získáváš " + hvezdy + " hvězdičky";
                ulozPlaketkyUziv("plakLVL3", true);
            }


            if (nactiVyslUzivatele("hvezdyLVL3") == 0 || nactiVyslUzivatele("hvezdyLVL3") < hvezdy){
                Log.d("medard", "dopocitany hvezdy, ziskany " + hvezdy);
                new AlertDialog.Builder(Level3Activity.this)
                        .setTitle("vyhodnocení")
                        .setMessage(mess)
                        .setPositiveButton("zpět na výběr levelů", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(getApplicationContext(), LevelsActivity.class);
                                i.putExtra("level", 3);
                                i.putExtra("pocet", hvezdy);
                                startActivity(i);
                                Log.d("medard", "zpatky na levely");
                            }
                        }).show();
                SharedPreferences pref = this.getSharedPreferences("prefData", this.MODE_PRIVATE);
                if (pref.getBoolean("plakLVL3", false)) {
                    new AlertDialog.Builder(Level3Activity.this)
                            .setTitle("Plaketka")
                            .setMessage("Gratulujeme, získáváš plaketku za splnění úrovně se čtyřmi hvězdami!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Log.d("medard", "ziskana plaketka 3");
                                }
                            }).show();
                }
            } else {
                Log.d("medard", "ziskano stejně nebo meně hvězd než nejlepší pokus");
                new AlertDialog.Builder(Level3Activity.this)
                        .setTitle("vyhodnocení")
                        .setMessage("Máš " + Math.round(sim) +"% shodu. Tvůj výsledek je menší nebo stejný jako tvůj nejlepší pokus, nezískáváš žádné nové hvězdy")
                        .setPositiveButton("zpět na výběr úrovní", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(getApplicationContext(), LevelsActivity.class);
                                startActivity(i);
                                Log.d("medard", "zpatky na levely");
                            }
                        }).show();
            }

        }
    }

    private int nactiVyslUzivatele(String klic) {
        SharedPreferences pref = this.getSharedPreferences("prefData", this.MODE_PRIVATE);
        return pref.getInt(klic, 0);
    }

    private void ulozPlaketkyUziv(String klic, boolean bool) {
        SharedPreferences pref = this.getSharedPreferences("prefData", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(klic, bool);
        editor.apply();
    }






}
