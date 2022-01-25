package edu.ohdm.editor.control.listener;

import android.content.DialogInterface;

import edu.ohdm.editor.view.Karte;

public class PopUpListener  implements DialogInterface.OnClickListener {
    private String t;
    private Karte callback;

    public PopUpListener(Karte callback) {
        this.callback = callback;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        switch (which) {
            case 0: // polygon
                t = "Polygon";
                break;
            case 1: // lineString
                t = "LineString";
                break;
            case 2: // point
                t = "Point";
                break;
            default:
                t = "error";
        }

        callback.cont();
    }

    public String getResult() {
        return this.t;
    }
}