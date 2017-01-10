package in.evolve.ornateadmin.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import in.evolve.ornateadmin.Adapters.PgVisitAdapter;
import in.evolve.ornateadmin.Models.GuestHouseBookInfo;
import in.evolve.ornateadmin.Models.PgVisitInfo;
import in.evolve.ornateadmin.R;

public class PgVisitActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PgVisitAdapter adapter;
    ProgressDialog  dialog;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_visit);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Pg Visit...");
        recyclerView= (RecyclerView) findViewById(R.id.pg_visit_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(PgVisitActivity.this));
        adapter = new PgVisitAdapter(PgVisitActivity.this,new ArrayList<PgVisitInfo>());
        recyclerView.setAdapter(adapter);
        fetchGuestHouseList();
    }

    private void fetchGuestHouseList(){
        showProgressDialog("Connecting");
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .get().url("http://ornateresidency.com/adminappapi/pgvisitcheck.php")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressDialog();
                        Toast.makeText(PgVisitActivity.this, "Unable To Connect To Internet ... Try Again", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {

                String res = response.body().string();

                try{
                    JSONObject jsonObject = new JSONObject(res);
                    if (jsonObject.getBoolean("status")){
                        JSONArray array = jsonObject.getJSONArray("results");
                        final List<PgVisitInfo> lis = fetchDataFromJson(array);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideProgressDialog();
                                adapter.changeList(lis);

                            }
                        });
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideProgressDialog();
                                Toast.makeText(PgVisitActivity.this, "Unable To Connect To Internet ... Try Again", Toast.LENGTH_LONG).show();
                                PgVisitActivity.this.finish();

                            }
                        });
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }


            }
        });

    }

    private List<PgVisitInfo> fetchDataFromJson(JSONArray  json) throws JSONException {

        List<PgVisitInfo> list = new ArrayList<>();

        for(int i=0;i<json.length();i++){

            String name = json.getJSONObject(i).getString("name");
            String email = json.getJSONObject(i).getString("email");
            String phone = json.getJSONObject(i).getString("phone");
            String pgid = json.getJSONObject(i).getString("pgid");
            String date = json.getJSONObject(i).getString("date");
            String time = json.getJSONObject(i).getString("time");
            String type = json.getJSONObject(i).getString("type");

            list.add(new PgVisitInfo(name,email,phone,type,date,time));

        }

        return list;

    }

    private void showProgressDialog(String msg){

        dialog = new ProgressDialog(PgVisitActivity.this);
        dialog.setMessage(msg);
        dialog.show();
    }

    private void hideProgressDialog(){

        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
