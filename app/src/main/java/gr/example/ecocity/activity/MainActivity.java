package gr.example.ecocity.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gr.example.ecocity.R;
import gr.example.ecocity.apis.AreaClass;
import gr.example.ecocity.apis.AreasApi;
import gr.example.ecocity.apis.EcoCityApi;
import gr.example.ecocity.apis.FormData;
import gr.example.ecocity.apis.GroundTypes;
import gr.example.ecocity.apis.LeafBranchings;
import gr.example.ecocity.apis.LeafTypes;
import gr.example.ecocity.apis.NetworkClient;
import gr.example.ecocity.apis.PlantBasins;
import gr.example.ecocity.apis.TreeAngles;
import gr.example.ecocity.apis.TreeConditions;
import gr.example.ecocity.apis.TreeType;
import gr.example.ecocity.apis.TrunkSurfaces;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    TextInputLayout latInput, lonInput, heightInput;
    TextInputLayout  diameterTreeInput;
    Spinner areasSpinner, speciesSpinner, addressSpinner, speciesLeafSpinner, branchTreeSpinner, surfaceSpinner;
    Spinner basinSpinner, soilSpinner, conditionTreeSpinner;
    Button clear, submit, photoButton;
    ImageButton mapButton;
    Retrofit retrofit;
    EcoCityApi ecoCityApi;
    ArrayList<AreaClass> areaClasses;
    ArrayList<TreeType> treeTypes;
    ArrayList<TreeAngles> treeAngles;
    ArrayList<LeafTypes> leafTypes;
    ArrayList<LeafBranchings> leafBranchings;
    ArrayList<TrunkSurfaces> trunkSurfaces;
    ArrayList<PlantBasins> plantBasins;
    ArrayList<GroundTypes> groundTypes;
    ArrayList<TreeConditions> treeConditions;
    ArrayAdapter<String> areasAdapter;
    ArrayAdapter<String> typesTreeAdapter;
    ArrayAdapter<String> treeAnglesAdapter;
    ArrayAdapter<String> leafTypesAdapter;
    ArrayAdapter<String> leafBranchingsAdapter;
    ArrayAdapter<String> trunkSurfacesAdapter;
    ArrayAdapter<String> plantBasinsAdapter;
    ArrayAdapter<String> groundTypesAdapter;
    ArrayAdapter<String> treeConditionsAdapter;
    AreaClass area;
    String selectedArea;
    String typeId;
    String addressPlant;
    String leafType;
    String leafBranch;
    String surfacePlant;
    String basinPlant;
    String soilPlant;
    String conditionPlant;
    File file;

    private final static int MY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermisions();
        initLayout();

        areasSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedArea = String.valueOf(adapterView.getSelectedItem());
                for (int j=0; j<areaClasses.size(); j++)
                {
                    if (areaClasses.get(j).getName().equals(selectedArea))
                    {
                        area = areaClasses.get(j);
                        Log.d("dimitra",selectedArea);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        speciesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                typeId = String.valueOf(adapterView.getSelectedItem());;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        addressSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                addressPlant = String.valueOf(adapterView.getSelectedItem());;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        speciesLeafSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                leafType = String.valueOf(adapterView.getSelectedItem());;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        branchTreeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                leafBranch = String.valueOf(adapterView.getSelectedItem());;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        surfaceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                surfacePlant = String.valueOf(adapterView.getSelectedItem());;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        basinSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                basinPlant = String.valueOf(adapterView.getSelectedItem());;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        soilSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                soilPlant = String.valueOf(adapterView.getSelectedItem());;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        conditionTreeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                conditionPlant = String.valueOf(adapterView.getSelectedItem());;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (area != null){
                    Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                    intent.putExtra("area", (Serializable) area);
                    startActivityForResult(intent,MY_REQUEST_CODE);
                }
            }
        });
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/png");
                startActivityForResult(intent, 2);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (file != null || !selectedArea.isEmpty() || !typeId.isEmpty())
                    uploadFormData(file,selectedArea,typeId,latInput.getEditText().getText().toString(),lonInput.getEditText().getText().toString(),heightInput.getEditText().getText().toString()/*,addressPlant,leafType,leafBranch,diameterTreeInput.getEditText().getText().toString(),surfacePlant,basinPlant,soilPlant,conditionPlant*/,"48");
            }
        });


        retrofit= NetworkClient.getRetrofitClient();

        ecoCityApi = retrofit.create(EcoCityApi.class);

        Call<AreasApi> call = ecoCityApi.getAreas();
        call.enqueue(new Callback<AreasApi>() {
            @Override
            public void onResponse(Call<AreasApi> call, Response<AreasApi> response) {
                int count = response.body().getCount();
                areaClasses = new ArrayList<>(response.body().getAreaClasses());
                ArrayList<String> areas = new ArrayList<>();
                for (int i=0; i<areaClasses.size(); i++){
                    String name = areaClasses.get(i).getName();
                    areas.add(name);
                }
                String[] areasString = areas.toArray(new String[areas.size()]);
                areasAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,areasString);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                areasSpinner.setAdapter(areasAdapter);
            }

            @Override
            public void onFailure(Call<AreasApi> call, Throwable t) {
                Log.d("dimitra",t.getLocalizedMessage());
            }
        });

        Call<List<TreeType>> call2 = ecoCityApi.getTreeTypes();
        call2.enqueue(new Callback<List<TreeType>>() {
            @Override
            public void onResponse(Call<List<TreeType>> call, Response<List<TreeType>> response) {
                treeTypes = new ArrayList<>(response.body());
                ArrayList<String> types = new ArrayList<>();
                for (int i=0; i<treeTypes.size(); i++){
                    String type = treeTypes.get(i).getType();
                    types.add(type);
                }
                String[] typesString = types.toArray(new String[types.size()]);
                typesTreeAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,typesString);
                typesTreeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                speciesSpinner.setAdapter(typesTreeAdapter);
            }

            @Override
            public void onFailure(Call<List<TreeType>> call, Throwable t) {
                Log.d("dimitra",t.getLocalizedMessage());
            }
        });

        Call<List<TreeAngles>> call3 = ecoCityApi.getTreeAngles();
        call3.enqueue(new Callback<List<TreeAngles>>() {
            @Override
            public void onResponse(Call<List<TreeAngles>> call, Response<List<TreeAngles>> response) {
                treeAngles = new ArrayList<>(response.body());
                ArrayList<String> types = new ArrayList<>();
                for (int i=0; i<treeAngles.size(); i++){
                    String type = treeAngles.get(i).getAngle();
                    types.add(type);
                }
                String[] typesString = types.toArray(new String[types.size()]);
                treeAnglesAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,typesString);
                treeAnglesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                addressSpinner.setAdapter(treeAnglesAdapter);
            }

            @Override
            public void onFailure(Call<List<TreeAngles>> call, Throwable t) {
                Log.d("dimitra",t.getLocalizedMessage());
            }
        });

        Call<List<LeafTypes>> call4 = ecoCityApi.getLeafTypes();
        call4.enqueue(new Callback<List<LeafTypes>>() {
            @Override
            public void onResponse(Call<List<LeafTypes>> call, Response<List<LeafTypes>> response) {
                leafTypes = new ArrayList<>(response.body());
                ArrayList<String> types = new ArrayList<>();
                for (int i=0; i<leafTypes.size(); i++){
                    String type = leafTypes.get(i).getType();
                    types.add(type);
                }
                String[] typesString = types.toArray(new String[types.size()]);
                leafTypesAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,typesString);
                leafTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                speciesLeafSpinner.setAdapter(leafTypesAdapter);
            }

            @Override
            public void onFailure(Call<List<LeafTypes>> call, Throwable t) {
                Log.d("dimitra",t.getLocalizedMessage());
            }
        });

        Call<List<LeafBranchings>> call5 = ecoCityApi.getLeafBranchings();
        call5.enqueue(new Callback<List<LeafBranchings>>() {
            @Override
            public void onResponse(Call<List<LeafBranchings>> call, Response<List<LeafBranchings>> response) {
                leafBranchings = new ArrayList<>(response.body());
                ArrayList<String> types = new ArrayList<>();
                for (int i=0; i<leafBranchings.size(); i++){
                    String type = leafBranchings.get(i).getBranch();
                    types.add(type);
                }
                String[] typesString = types.toArray(new String[types.size()]);
                leafBranchingsAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,typesString);
                leafBranchingsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                branchTreeSpinner.setAdapter(leafBranchingsAdapter);
            }

            @Override
            public void onFailure(Call<List<LeafBranchings>> call, Throwable t) {
                Log.d("dimitra",t.getLocalizedMessage());
            }
        });

        Call<List<TrunkSurfaces>> call6 = ecoCityApi.getTrunkSurfaces();
        call6.enqueue(new Callback<List<TrunkSurfaces>>() {
            @Override
            public void onResponse(Call<List<TrunkSurfaces>> call, Response<List<TrunkSurfaces>> response) {
                trunkSurfaces = new ArrayList<>(response.body());
                ArrayList<String> types = new ArrayList<>();
                for (int i=0; i<trunkSurfaces.size(); i++){
                    String type = trunkSurfaces.get(i).getSurface();
                    types.add(type);
                }
                String[] typesString = types.toArray(new String[types.size()]);
                trunkSurfacesAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,typesString);
                trunkSurfacesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                surfaceSpinner.setAdapter(trunkSurfacesAdapter);
            }

            @Override
            public void onFailure(Call<List<TrunkSurfaces>> call, Throwable t) {
                Log.d("dimitra",t.getLocalizedMessage());
            }
        });

        Call<List<PlantBasins>> call7 = ecoCityApi.getPlantBasins();
        call7.enqueue(new Callback<List<PlantBasins>>() {
            @Override
            public void onResponse(Call<List<PlantBasins>> call, Response<List<PlantBasins>> response) {
                plantBasins = new ArrayList<>(response.body());
                ArrayList<String> types = new ArrayList<>();
                for (int i=0; i<plantBasins.size(); i++){
                    String type = plantBasins.get(i).getBasin();
                    types.add(type);
                }
                String[] typesString = types.toArray(new String[types.size()]);
                plantBasinsAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,typesString);
                plantBasinsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                basinSpinner.setAdapter(plantBasinsAdapter);
            }

            @Override
            public void onFailure(Call<List<PlantBasins>> call, Throwable t) {
                Log.d("dimitra",t.getLocalizedMessage());
            }
        });

        Call<List<GroundTypes>> call8 = ecoCityApi.getGrountTypes();
        call8.enqueue(new Callback<List<GroundTypes>>() {
            @Override
            public void onResponse(Call<List<GroundTypes>> call, Response<List<GroundTypes>> response) {
                groundTypes = new ArrayList<>(response.body());
                ArrayList<String> types = new ArrayList<>();
                for (int i=0; i<groundTypes.size(); i++){
                    String type = groundTypes.get(i).getType();
                    types.add(type);
                }
                String[] typesString = types.toArray(new String[types.size()]);
                groundTypesAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,typesString);
                groundTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                soilSpinner.setAdapter(groundTypesAdapter);
            }

            @Override
            public void onFailure(Call<List<GroundTypes>> call, Throwable t) {
                Log.d("dimitra",t.getLocalizedMessage());
            }
        });

        Call<List<TreeConditions>> call9 = ecoCityApi.getTreeConditions();
        call9.enqueue(new Callback<List<TreeConditions>>() {
            @Override
            public void onResponse(Call<List<TreeConditions>> call, Response<List<TreeConditions>> response) {
                treeConditions = new ArrayList<>(response.body());
                ArrayList<String> types = new ArrayList<>();
                for (int i=0; i<treeConditions.size(); i++){
                    String type = treeConditions.get(i).getCondition();
                    types.add(type);
                }
                String[] typesString = types.toArray(new String[types.size()]);
                treeConditionsAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,typesString);
                treeConditionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                conditionTreeSpinner.setAdapter(treeConditionsAdapter);
            }

            @Override
            public void onFailure(Call<List<TreeConditions>> call, Throwable t) {
                Log.d("dimitra",t.getLocalizedMessage());
            }
        });
    }

    public void initLayout(){
        speciesSpinner = (Spinner) findViewById(R.id.spinner);
        areasSpinner = (Spinner) findViewById(R.id.spinner2);
        latInput = (TextInputLayout)findViewById(R.id.textInputLayout3);
        lonInput = (TextInputLayout)findViewById(R.id.textInputLayout4);
        heightInput = (TextInputLayout)findViewById(R.id.textInputLayout5);
        addressSpinner = (Spinner) findViewById(R.id.spinner3);
        speciesLeafSpinner = (Spinner) findViewById(R.id.spinner4);
        branchTreeSpinner = (Spinner) findViewById(R.id.spinner5);
        diameterTreeInput = (TextInputLayout)findViewById(R.id.textInputLayout9);
        surfaceSpinner = (Spinner) findViewById(R.id.spinner6);
        basinSpinner = (Spinner) findViewById(R.id.spinner7);
        soilSpinner = (Spinner) findViewById(R.id.spinner8);
        conditionTreeSpinner = (Spinner) findViewById(R.id.spinner9);
        photoButton = (Button) findViewById(R.id.button3);
        clear = (Button)findViewById(R.id.button2);
        submit = (Button)findViewById(R.id.button);
        mapButton = (ImageButton)findViewById(R.id.imageButton);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == MY_REQUEST_CODE) {
                if (data.getExtras().containsKey("lat") && data.getExtras().containsKey("lng")){
                    latInput.getEditText().setText(data.getExtras().getString("lat"));
                    lonInput.getEditText().setText(data.getExtras().getString("lng"));
                }
            }
            else if (requestCode == 2){
                if (data != null) {
                    file = new File(getRealPathFromURI(data.getData()));
                    Log.d("dimitra",file.getPath());
                }
            }
        }
    }

    public void uploadFormData(File file, String areaid, String typeid, String latitude, String longitude,
                               String height, /*String angleid, String leaftype, String leafbranch, String diameter,
                               String trunksurfaceid, String plantbasinid, String groundtypeid, String conditionid,*/ String municipalityId){
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
        RequestBody areaIdBody = RequestBody.create(MediaType.parse("text/plain"), areaid);
        RequestBody typeIdBody = RequestBody.create(MediaType.parse("text/plain"), typeid);
        RequestBody latitudeBody = RequestBody.create(MediaType.parse("text/plain"), latitude);
        RequestBody longitudeBody = RequestBody.create(MediaType.parse("text/plain"), longitude);
        RequestBody heightBody = RequestBody.create(MediaType.parse("text/plain"), height);
        /*RequestBody angleIdBody = RequestBody.create(MediaType.parse("text/plain"), angleid);
        RequestBody leaftypeBody = RequestBody.create(MediaType.parse("text/plain"), leaftype);
        RequestBody leafbranchBody = RequestBody.create(MediaType.parse("text/plain"), leafbranch);
        RequestBody diameterBody = RequestBody.create(MediaType.parse("text/plain"), diameter);
        RequestBody trunkSurfaceIdBody = RequestBody.create(MediaType.parse("text/plain"), trunksurfaceid);
        RequestBody plantBasinIdBody = RequestBody.create(MediaType.parse("text/plain"), plantbasinid);
        RequestBody groundTypeIdBody = RequestBody.create(MediaType.parse("text/plain"), groundtypeid);
        RequestBody conditionIdBody = RequestBody.create(MediaType.parse("text/plain"), conditionid);*/
        RequestBody municipalityIdBody = RequestBody.create(MediaType.parse("text/plain"), municipalityId);

        Call<FormData> call = ecoCityApi.uploadFormData(requestFile,areaIdBody,typeIdBody,latitudeBody,longitudeBody,heightBody/*,angleIdBody,
                leaftypeBody,leafbranchBody,diameterBody,trunkSurfaceIdBody,plantBasinIdBody,groundTypeIdBody,conditionIdBody*/,municipalityIdBody);
        call.enqueue(new Callback<FormData>() {
            @Override
            public void onResponse(Call<FormData> call, Response<FormData> response) {
                Log.d("dimitra",""+response.code()+" "+response.message());
                if (response.code()==200) {
                    Toast.makeText(getApplicationContext(), "File Uploaded Successfully...", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Some error occurred...", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<FormData> call, Throwable t) {
                Log.d("dimitra",t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void requestPermisions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            finish();
            startActivity(intent);
            return;
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
}