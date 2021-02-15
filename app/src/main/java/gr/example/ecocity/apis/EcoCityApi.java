package gr.example.ecocity.apis;

import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface EcoCityApi {

    @GET("/api/areas")
    Call<AreasApi> getAreas();

    @GET("/api/treetypes")
    Call<List<TreeType>> getTreeTypes();

    @GET("/api/treeangles")
    Call<List<TreeAngles>> getTreeAngles();

    @GET("/api/leaftypes")
    Call<List<LeafTypes>> getLeafTypes();

    @GET("/api/leafbranchings")
    Call<List<LeafBranchings>> getLeafBranchings();

    @GET("/api/trunksurfaces")
    Call<List<TrunkSurfaces>> getTrunkSurfaces();

    @GET("/api/plantbasins")
    Call<List<PlantBasins>> getPlantBasins();

    @GET("/api/groundtypes")
    Call<List<GroundTypes>> getGrountTypes();

    @GET("/api/treeconditions")
    Call<List<TreeConditions>> getTreeConditions();

    @Multipart
    @POST("api/trees")
    Call<FormData> uploadFormData(@Part("photos\"; filename=\"photo.jpg\" ") RequestBody file,
                                  @Part("areaid") RequestBody areaid,
                                  @Part("typeid") RequestBody type,
                                  @Part("latitude") RequestBody latitude,
                                  @Part("longitude") RequestBody longitude,
                                  @Part("height") RequestBody height,
//                                  @Path("angleid") RequestBody angleid,
//                                  @Path("leaftype") RequestBody leaftype,
//                                  @Path("leafbranch") RequestBody leafbranch,
//                                  @Path("diameter") RequestBody diameter,
//                                  @Path("trunksurfaceid") RequestBody trunksurfaceid,
//                                  @Path("plantbasinid") RequestBody plantbasinid,
//                                  @Path("groundtypeid") RequestBody groundtypeid,
//                                  @Path("conditionid") RequestBody conditionid,
                                  @Path("municipalityId") RequestBody municipalityId);
}
