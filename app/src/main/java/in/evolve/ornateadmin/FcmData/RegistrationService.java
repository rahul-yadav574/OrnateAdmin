package in.evolve.ornateadmin.FcmData;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import in.evolve.ornateadmin.Utils;

/**
 * Created by Brekkishhh on 29-06-2016.
 */
public class RegistrationService extends IntentService {

    public RegistrationService() {
        super("arg");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.e("FCM","Registration Service Started");
        String newToken = FirebaseInstanceId.getInstance().getToken();
        sendNewTokenToServer(newToken);
    }

    private void sendNewTokenToServer(String token){
        Log.e("FCM","Sending Token To Server");
        Log.e("FCM",""+token);
        String acToken = null;


        try {
            JSONObject jsonObject = new JSONObject(token);
            acToken = jsonObject.getString("token");
        }catch (JSONException e){

            acToken = token;
            e.printStackTrace();
        }





            //TODO:We have to send this token to participateme server
           // Log.e("FCM","User With ID : "+sharedPrefUtil.getUserInfo().getId()+" Is Sending The Token To ParticipateMe Server");
            String requestUrl = "http://ornateresidency.com/adminappapi/adminlogincheck.php";

        OkHttpClient ok = new OkHttpClient();

        RequestBody body = new FormEncodingBuilder()
                .add("device_id",acToken)
                .build();

        Request request = new Request.Builder()
                .post(body)
                .url(requestUrl)
                .build();
        ok.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.e("resposne", "" + response.body().string());
            }
        });


        }
    }

