package it.antelli.giovanni.farfallese;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    EditText edt1;
    TextView descr2;
    android.content.ClipboardManager clip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final FloatingActionButton fabdel = (FloatingActionButton) findViewById(R.id.fabdel);
        final TextView tv1 = (TextView) findViewById(R.id.tv1);
        final EditText edt1 = (EditText) findViewById(R.id.edt1);
        final TextView descr2 = (TextView) findViewById(R.id.descr2);
        final Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);//Initiate the vibrate service
        final ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        final ClipData clip = ClipData.newPlainText("label", "Text");


            fabdel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edt1.setText("");
                    fabdel.setVisibility(View.GONE);
                    tv1.setVisibility(View.GONE);
                    descr2.setVisibility(View.GONE);
                    vib.vibrate(80);

                }
            });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String traduzione = edt1
                        .getText().toString();
                traduzione = traduzione.toLowerCase();

                if (!TextUtils.isEmpty(traduzione)) {

                    traduzione = traduzione.replaceAll("a", "afa").replaceAll("e", "efe").replaceAll("i", "ifi").replaceAll("o", "ofo").replaceAll("u", "ufu");
                    tv1.setText(traduzione);
                    fabdel.setVisibility(View.VISIBLE);
                    tv1.setVisibility(View.VISIBLE);
                    descr2.setVisibility(View.VISIBLE);

                    vib.vibrate(250);


                    clipboard.setText(tv1.getText().toString());
                    Snackbar.make(view, "Traduzione effettuata correttamente e copiata negli appunti", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }

                else

                    {
                        Snackbar.make(view, "Nessun testo presente, inserire un testo prima di procedere", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                        vib.vibrate(800);
                    }
                }
            });

         }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.doubleback, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}