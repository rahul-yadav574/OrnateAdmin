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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import in.evolve.ornateadmin.Adapters.GhBookAdapter;
import in.evolve.ornateadmin.Adapters.PgBookAdapter;
import in.evolve.ornateadmin.Models.GuestHouseBookInfo;
import in.evolve.ornateadmin.Models.PgBookInfo;
import in.evolve.ornateadmin.R;

public class GuestHouseBookActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressDialog dialog;
    GhBookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_house_book);
        recyclerView= (RecyclerView) findViewById(R.id.gh_book_recyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(GuestHouseBookActivity.this));
        adapter = new GhBookAdapter(GuestHouseBookActivity.this,new ArrayList<GuestHouseBookInfo>());
        recyclerView.setLayoutManager(new LinearLayoutManager(GuestHouseBookActivity.this));
        recyclerView.setAdapter(adapter);
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
                        Toast.makeText(GuestHouseBookActivity.this, "Unable To Connect To Internet ... Try Again", Toast.LENGTH_LONG).show();
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
                        final List<GuestHouseBookInfo> lis = fetchDataFromJson(array);
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
                                Toast.makeText(GuestHouseBookActivity.this, "Unable To Connect To Internet ... Try Again", Toast.LENGTH_LONG).show();
                                GuestHouseBookActivity.this.finish();

                            }
                        });
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }


            }
        });

    }

    private List<GuestHouseBookInfo> fetchDataFromJson(JSONArray  json) throws JSONException {

        List<GuestHouseBookInfo> list = new ArrayList<>();

        for(int i=0;i<json.length();i++){

            String name = json.getJSONObject(i).getString("name");
            String email = json.getJSONObject(i).getString("email");
            String phone = json.getJSONObject(i).getString("phone");
            String pgid = json.getJSONObject(i).getString("ghid");
            String date = json.getJSONObject(i).getString("date");
            String nPeople = json.getJSONObject(i).getString("people");
            String nRooms = json.getJSONObject(i).getString("nrroms");

            list.add(new GuestHouseBookInfo(name,email,phone,pgid,date,nRooms));

        }

        return list;

    }

    private void showProgressDialog(String msg){

        dialog = new ProgressDialog(GuestHouseBookActivity.this);
        dialog.setMessage(msg);
        dialog.show();
    }

    private void hideProgressDialog(){

        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
