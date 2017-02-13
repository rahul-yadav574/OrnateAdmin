package in.evolve.ornateadmin.FcmData;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Brekkishhh on 28-06-2016.
 */
public class InstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        Intent intent = new Intent(this,RegistrationService.class);
        startService(intent);

    }



}
