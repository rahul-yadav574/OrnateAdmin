package in.evolve.ornateadmin.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import in.evolve.ornateadmin.Adapters.PgRequestAdapter;
import in.evolve.ornateadmin.Models.Pg;
import in.evolve.ornateadmin.Models.PgRequest;
import in.evolve.ornateadmin.R;

public class PgListRequest extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressDialog dialog;
    PgRequestAdapter  adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_list_request);
        recyclerView= (RecyclerView) findViewById(R.id.pg_request_notifications);
        adapter = new PgRequestAdapter(PgListRequest.this,new ArrayList<PgRequest>());
        recyclerView.setLayoutManager(new LinearLayoutManager(PgListRequest.this));
        recyclerView.setAdapter(adapter);
        fetchGuestHouseList();
    }

    private void fetchGuestHouseList(){
        showProgressDialog("Connecting");
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .get().url("http://ornateresidency.com/adminappapi/listyourhousecheck.php")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressDialog();
                        Toast.makeText(PgListRequest.this, "Unable To Connect To Internet ... Try Again", Toast.LENGTH_LONG).show();
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
                            final List<PgRequest> lis = fetchDataFromJson(array);
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
                                    Toast.makeText(PgListRequest.this, "Unable To Connect To Internet ... Try Again", Toast.LENGTH_LONG).show();
                                    PgListRequest.this.finish();

                                }
                            });
                        }

                    }catch (JSONException e){
                        e.printStackTrace();
                    }


            }
        });

    }

    private List<PgRequest> fetchDataFromJson(JSONArray  json) throws JSONException {

        List<PgRequest> list = new ArrayList<>();

        for(int i=0;i<json.length();i++){

            String name = json.getJSONObject(i).getString("name");
            String email = json.getJSONObject(i).getString("email");
            String phone = json.getJSONObject(i).getString("phone");
            String address = json.getJSONObject(i).getString("address");
            String rooms = json.getJSONObject(i).getString("rooms");

            list.add(new PgRequest(name,email,phone,rooms,address));

        }

        return list;

    }

    private void showProgressDialog(String msg){

        dialog = new ProgressDialog(PgListRequest.this);
        dialog.setMessage(msg);
        dialog.show();
    }

    private void hideProgressDialog(){

        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
