package edu.ohdm.editor.control;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import edu.ohdm.editor.R;
import edu.ohdm.editor.control.db.Datenbank;
import edu.ohdm.editor.model.Eintrag;

public class Konfiguration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_config);

        ListView lv =(ListView) findViewById(R.id.ListView);
        Datenbank dbh = new Datenbank(this,"LAYERS",null,1);
        Formatierung ca = new Formatierung(this,dbh.getEntries());

        // DataBind ListView with items from ArrayAdapter
        lv.setAdapter(ca);

    }

    public static Eintrag[] getEntries(String[] layerNames)
    {
        Eintrag[] entries = new Eintrag[layerNames.length];

        for(int i = 0; i<layerNames.length; i++)
        {
            entries[i] = new Eintrag(layerNames[i],false);
        }

        return entries;
    }
}