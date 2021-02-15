package gr.example.ecocity.activity;

import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import gr.example.ecocity.R;
import gr.example.ecocity.apis.AreaClass;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    AreaClass areaClass;
    ArrayList<LatLng> geolocationList;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        areaClass = (AreaClass) getIntent().getSerializableExtra("area");
        builder = new AlertDialog.Builder(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //mMap.setOnPolygonClickListener(this);

        LatLng athens = new LatLng(	37.983810, 23.727539);
        mMap.addMarker(new MarkerOptions().position(athens).title("Marker in Athens"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(athens,14));


        String geolocations = areaClass.getLocations();
        JsonArray jsonArray = new JsonParser().parse(geolocations).getAsJsonArray();
        geolocationList = new ArrayList<>();

        for (int i=0; i<jsonArray.size(); i++){
            JsonObject geoObject = new JsonParser().parse(jsonArray.get(i).toString()).getAsJsonObject();
            double lat = geoObject.get("lat").getAsDouble();
            double lng = geoObject.get("lng").getAsDouble();
            geolocationList.add(new LatLng(lat,lng));
        }
        PolygonOptions poly = new PolygonOptions();
        for (LatLng latLng : geolocationList) {
            poly.add(latLng);
        }

        mMap.clear();

        Polygon polygon =  mMap.addPolygon(poly.strokeColor(Color.argb(255, 49, 101, 187)).fillColor(Color.argb(100, 49, 101, 187)));
        polygon.setTag("alpha");

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                boolean inside = isPointInPolygon(latLng,geolocationList);
                Log.d("dimitra"," "+inside);
                if (inside){
                    builder.setMessage("Θελετε να φορτωθουν οι γεωγραφικες συντενταγμενες\n" +
                            "lat "+latLng.latitude+","+" lng "+ latLng.longitude+"\n" +
                            "στα πεδια;")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent();
                                    intent.putExtra("lat", ""+latLng.latitude);
                                    intent.putExtra("lng", ""+latLng.longitude);
                                    setResult(RESULT_OK, intent);
                                    dialog.dismiss();
                                    finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Location");
                    alert.show();
                }
                else {
                    builder.setMessage("Δεν είναι δυνατή η επιλογή τοποθεσίας εκτός ορίων της επιλεγμένης περιοχής")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                    Toast.makeText(getApplicationContext(),"you choose yes action for alertbox",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Προειδοποίηση");
                    alert.show();
                }
            }
        });
    }

    private boolean isPointInPolygon(LatLng tap, ArrayList<LatLng> vertices) {
        int intersectCount = 0;
        for (int j = 0; j < vertices.size() - 1; j++) {
            if (rayCastIntersect(tap, vertices.get(j), vertices.get(j + 1))) {
                intersectCount++;
            }
        }

        return ((intersectCount % 2) == 1); // odd = inside, even = outside;
    }

    private boolean rayCastIntersect(LatLng tap, LatLng vertA, LatLng vertB) {

        double aY = vertA.latitude;
        double bY = vertB.latitude;
        double aX = vertA.longitude;
        double bX = vertB.longitude;
        double pY = tap.latitude;
        double pX = tap.longitude;

        if ((aY > pY && bY > pY) || (aY < pY && bY < pY)
                || (aX < pX && bX < pX)) {
            return false; // a and b can't both be above or below pt.y, and a or
            // b must be east of pt.x
        }

        double m = (aY - bY) / (aX - bX); // Rise over run
        double bee = (-aX) * m + aY; // y = mx + b
        double x = (pY - bee) / m; // algebra is neat!

        return x > pX;
    }

}