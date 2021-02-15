package gr.example.ecocity.apis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geolocation {
    @SerializedName("lat")
    @Expose
    long lat;
    @SerializedName("lng")
    @Expose
    long lng;

    public Geolocation(long lat, long lng){
        this.lat = lat;
        this.lng = lng;
    }

    public long getLat() {
        return lat;
    }

    public long getLon() {
        return lng;
    }
}
