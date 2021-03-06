package gr.example.ecocity.apis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeafBranchings {

    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("branch")
    @Expose
    String branch;
    @SerializedName("nameel")
    @Expose
    String nameel;
    @SerializedName("nameen")
    @Expose
    String nameen;

    public LeafBranchings(int id, String branch, String nameel, String nameen) {
        this.id = id;
        this.branch = branch;
        this.nameel = nameel;
        this.nameen = nameen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
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
