package edu.ohdm.editor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.ohdm.editor.control.db.Datenbank;
import edu.ohdm.editor.control.db.StartDB;
import edu.ohdm.editor.view.Config;
import edu.ohdm.editor.view.Karte;

public class Start extends AppCompatActivity {

    private boolean TESTMODE = false;

    Button config;
    TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_main);

        Datenbank db = new Datenbank(this, "LAYERS", null, 1);

        config = (Button) findViewById(R.id.button3);
        msg = (TextView) findViewById(R.id.msg);

        if (!TESTMODE) {
            config.setVisibility(View.INVISIBLE);
            msg.setText("Bei Dieser App handelt es sich momentan nur um einen Prototypen");
        } else {
            config.setVisibility(View.VISIBLE);
        }
        (new StartDB()).start();
    }

    public void config(View v) {
        Intent i = new Intent(this, Config.class);
        startActivity(i);
    }

    public void startMap(View v) {
        Intent i = new Intent(this, Karte.class);
        startActivity(i);
    }
}
