package edu.ohdm.editor.model;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.ohdm.editor.view.Karte;

public class GPS extends AppCompatActivity implements LocationListener {
    private Karte map;
    private double lastLong = 0, lastLat = 0, dif = 0.00001;

    public GPS(Karte map) {
        this.map = map;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (lastLong - location.getLongitude() > dif || lastLat - location.getLatitude() > dif || location.getLongitude() - lastLong > dif || location.getLatitude() - lastLat > dif) {
            map.setGPS(location.getLongitude(), location.getLatitude());
            lastLat = location.getLatitude();
            lastLong = location.getLongitude();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}