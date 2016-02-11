package ua.hey.hey;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import ua.hey.hey.Config;
import ua.hey.hey.MainActivity;

/**
 * Created by Maxim on 2/11/16.
 */
public class Notifications {
    public static void notification(Context context){
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.support.design.R.drawable.notification_template_icon_bg)
                .setContentTitle("new message!")
                .setContentText("you got a new message!");
        Intent mainActivity = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(mainActivity);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
}
