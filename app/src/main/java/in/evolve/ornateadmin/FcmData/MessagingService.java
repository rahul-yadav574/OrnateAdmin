package in.evolve.ornateadmin.FcmData;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import in.evolve.ornateadmin.Activity.GuestHouseBookActivity;
import in.evolve.ornateadmin.Activity.LoginActivity;
import in.evolve.ornateadmin.Activity.PgBookActivity;
import in.evolve.ornateadmin.Activity.PgListRequest;
import in.evolve.ornateadmin.Activity.PgVisitActivity;
import in.evolve.ornateadmin.R;

/**
 * Created by Brekkishhh on 28-06-2016.
 */
public class MessagingService extends FirebaseMessagingService {

    int NOTIFICATION_ID = 574;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        Map<String,String> notificationData = remoteMessage.getData();
       // surveyId = remoteMessage.getData().get("message");

        Log.d("tag is :","notification recieved");

        if (notificationData.containsKey("type") ){
            //it is share in team notification

            if (notificationData.get("type").equals("pgBook")){
                Intent intent = new Intent(this, PgBookActivity.class);
                Bundle extras = new Bundle();
                //this.surveyID = extras.getString("surveyID");

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                        PendingIntent.FLAG_ONE_SHOT);
                notifyUser("New Pg Book",pendingIntent);
            }

            else if (notificationData.get("type").equals("pgVisit")){
                Intent intent = new Intent(this, PgVisitActivity.class);
                Bundle extras = new Bundle();
                //this.surveyID = extras.getString("surveyID");

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                        PendingIntent.FLAG_ONE_SHOT);
                notifyUser("New Pg visit book",pendingIntent);
            }
            else if(notificationData.get("type").equals("ghBook")){
                Intent intent = new Intent(this, GuestHouseBookActivity.class);
                Bundle extras = new Bundle();
                //this.surveyID = extras.getString("surveyID");

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                        PendingIntent.FLAG_ONE_SHOT);
                notifyUser("New Guest House Book",pendingIntent);
            }else if(notificationData.get("type").equals("listP")){

                Intent intent = new Intent(this, PgListRequest.class);
                Bundle extras = new Bundle();
                //this.surveyID = extras.getString("surveyID");

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                        PendingIntent.FLAG_ONE_SHOT);

                notifyUser("New List Property ",pendingIntent);
            }
        }

    }


    /*private void notifyForJoinTeam(String message){


      *//*  Intent intent = new Intent(this, SurveyFillActivity.class);
        intent.setAction(Constants.ACTION_FILL_TEAM_SURVEY);
        Bundle extras = new Bundle();
        //this.surveyID = extras.getString("surveyID");
        extras.putString("surveyID",surveyId);
        extras.putString("surveyTitle","Survey Title");
        intent.putExtras(extras);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                PendingIntent.FLAG_ONE_SHOT);*//*

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Participate/\\/\\e Alert")
                .setContentText(message)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE)
                .setSound(defaultSoundUri);


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(AppContext.NOTIFICATION_ID++, notificationBuilder.build());


    }*/


    private void notifyUser(String message,PendingIntent pendingIntent){



            Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Ornate Notification")
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setOngoing(true)
                    .setDefaults(Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(NOTIFICATION_ID++, notificationBuilder.build());
    }

    /*private void notifyUserForTakeSurvey(String message){

        Intent intent = new Intent(this, SurveyFillActivity.class);
        intent.setAction(Constants.ACTION_FILL_TEAM_SURVEY);
        Bundle extras = new Bundle();
        //this.surveyID = extras.getString("surveyID");
        extras.putString("surveyID",surveyId);
        extras.putString("surveyTitle","Survey Title");
        intent.putExtras(extras);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Participate/\\/\\e Alert")
                .setContentText(message)
                .setOngoing(true)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(AppContext.NOTIFICATION_ID++, notificationBuilder.build());
    }*/
}

