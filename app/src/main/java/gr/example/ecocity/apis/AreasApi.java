package gr.example.ecocity.apis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AreasApi {

    @SerializedName("count")
    @Expose
    int count;

    @SerializedName("areas")
    @Expose
    List<AreaClass> areaClasses;

    public AreasApi(int count, List<AreaClass> areaClasses) {
        this.count = count;
        this.areaClasses = areaClasses;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<AreaClass> getAreaClasses() {
        return areaClasses;
    }

    public void setAreaClasses(List<AreaClass> areaClasses) {
        this.areaClasses = areaClasses;
    }
}
