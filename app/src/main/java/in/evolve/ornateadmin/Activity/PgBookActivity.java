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

import in.evolve.ornateadmin.Adapters.PgBookAdapter;
import in.evolve.ornateadmin.Models.PgBookInfo;
import in.evolve.ornateadmin.Models.PgRequest;
import in.evolve.ornateadmin.R;

public class PgBookActivity extends AppCompatActivity {

    ProgressDialog dialog;
    PgBookAdapter adapter;
    RecyclerView recyclerView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_book);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Pg Booking...");
        recyclerView = (RecyclerView) findViewById(R.id.pg_conditionsbook_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(PgBookActivity.this));
        adapter = new PgBookAdapter(PgBookActivity.this,new ArrayList<PgBookInfo>());
        fetchGuestHouseList();
    }

    private void fetchGuestHouseList(){
        showProgressDialog("Connecting");
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .get().url("http://ornateresidency.com/adminappapi/pgbookingcheck.php")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressDialog();
                        Toast.makeText(PgBookActivity.this, "Unable To Connect To Internet ... Try Again", Toast.LENGTH_LONG).show();
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
                        final List<PgBookInfo> lis = fetchDataFromJson(array);
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
                                Toast.makeText(PgBookActivity.this, "Unable To Connect To Internet ... Try Again", Toast.LENGTH_LONG).show();
                                PgBookActivity.this.finish();

                            }
                        });
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }


            }
        });

    }

    private List<PgBookInfo> fetchDataFromJson(JSONArray  json) throws JSONException {

        List<PgBookInfo> list = new ArrayList<>();

        for(int i=0;i<json.length();i++){

            String name = json.getJSONObject(i).getString("name");
            String email = json.getJSONObject(i).getString("email");
            String phone = json.getJSONObject(i).getString("phone");
            String pgid = json.getJSONObject(i).getString("pgid");
            String date = json.getJSONObject(i).getString("date");

            list.add(new PgBookInfo(name,email,phone,pgid,date,""));

        }

        return list;

    }

    private void showProgressDialog(String msg){

        dialog = new ProgressDialog(PgBookActivity.this);
        dialog.setMessage(msg);
        dialog.show();
    }

    private void hideProgressDialog(){

        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
