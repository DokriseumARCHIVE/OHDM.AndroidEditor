package edu.ohdm.editor.control;

import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONObject;
import org.osmdroid.util.GeoPoint;

import java.util.List;

import edu.ohdm.editor.view.Karte;

public class JSON {

    private String type;
    private Karte karte;

    public JSON(Karte karte){
        this.karte = karte;
    }

    public String createJson(List<GeoPoint> pts, String type) throws Exception{
        this.type = type;
        JSONArray features = new JSONArray();
        JSONArray coor = new JSONArray();
        for (GeoPoint point : pts){
            JSONArray coordinates = new JSONArray();
            coordinates.put(point.getLongitude());
            coordinates.put(point.getLatitude());
            coor.put(coordinates);
        }
        JSONObject geometry = new JSONObject();
        geometry.put("type", this.type);
        geometry.put("coordinates", coor);

        JSONObject prop = new JSONObject();
        prop.put("name", "test");

        JSONObject feature = new JSONObject();
        feature.put("type", "Feature");
        feature.put("properties", prop);
        feature.put("geometry", geometry);
        features.put(feature);
        JSONObject featureCollection = new JSONObject();
        featureCollection.put("type", "FeatureCollection");
        featureCollection.put("features", features);
        //Toast.makeText((Context) map, "success", Toast.LENGTH_LONG).show();

        return featureCollection.toString();
    }
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/EditorOHDM";

}
