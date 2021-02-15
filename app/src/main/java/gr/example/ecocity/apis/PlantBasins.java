package gr.example.ecocity.apis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlantBasins {

    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("basin")
    @Expose
    String basin;
    @SerializedName("nameel")
    @Expose
    String nameel;
    @SerializedName("nameen")
    @Expose
    String nameen;

    public PlantBasins(int id, String basin, String nameel, String nameen) {
        this.id = id;
        this.basin = basin;
        this.nameel = nameel;
        this.nameen = nameen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBasin() {
        return basin;
    }

    public void setBasin(String basin) {
        this.basin = basin;
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
