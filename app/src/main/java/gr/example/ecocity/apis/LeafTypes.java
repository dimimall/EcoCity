package gr.example.ecocity.apis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeafTypes {

    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("type")
    @Expose
    String type;
    @SerializedName("nameel")
    @Expose
    String nameel;
    @SerializedName("nameen")
    @Expose
    String nameen;

    public LeafTypes(int id, String type, String nameel, String nameen) {
        this.id = id;
        this.type = type;
        this.nameel = nameel;
        this.nameen = nameen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
