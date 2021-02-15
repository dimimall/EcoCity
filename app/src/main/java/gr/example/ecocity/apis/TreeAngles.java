package gr.example.ecocity.apis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TreeAngles {

    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("angle")
    @Expose
    String angle;
    @SerializedName("nameel")
    @Expose
    String nameel;
    @SerializedName("nameen")
    @Expose
    String nameen;

    public TreeAngles(int id, String angle, String nameel, String nameen) {
        this.id = id;
        this.angle = angle;
        this.nameel = nameel;
        this.nameen = nameen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
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
