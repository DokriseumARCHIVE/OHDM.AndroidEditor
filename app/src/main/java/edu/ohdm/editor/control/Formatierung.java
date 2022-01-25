package edu.ohdm.editor.control;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import edu.ohdm.editor.R;
import edu.ohdm.editor.model.Eintrag;

public class Formatierung  extends ArrayAdapter<Eintrag> {
    private Context context;
    private Eintrag[] entries;

    public Formatierung(Context context, Eintrag[] entries) {
        super(context, R.layout.sample_row,entries);

        this.context = context;
        this.entries = entries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.sample_row, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.textView);
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox);
        name.setText(entries[position].getName());
        if(entries[position].getChecked())
            cb.setChecked(true);
        else
            cb.setChecked(false);
        return convertView;
    }
}
