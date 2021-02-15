package gr.example.ecocity.apis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrunkSurfaces {

    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("surface")
    @Expose
    String surface;
    @SerializedName("nameel")
    @Expose
    String nameel;
    @SerializedName("nameen")
    @Expose
    String nameen;

    public TrunkSurfaces(int id, String surface, String nameel, String nameen) {
        this.id = id;
        this.surface = surface;
        this.nameel = nameel;
        this.nameen = nameen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getNameel() {
        return nameel;
    }

    public void setNameel(String nameel) {
        this.nameel = nameel;
    }

    public String getNameen() {
        return nameen;
    }

    public void setNameen(String nameen) {
        this.nameen = nameen;
    }
}
