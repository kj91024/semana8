package utp.sanchezsanchez.semana8;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static String CHANNEL_ID = "Notificacion";
    private final static int NOTIFICATION_ID = 0;
    private static final String ACTION_SNOOZE = "Snooze";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.enviar);
    }

    private void createNoticationChannel() {
        //Toast.makeText(this, String.valueOf(Build.VERSION.SDK_INT), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, String.valueOf(Build.VERSION_CODES.O), Toast.LENGTH_SHORT).show();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Toast.makeText(this, "Debe de mostrar una notificación", Toast.LENGTH_SHORT).show();

            CharSequence name = "Notificacion";
            String description = "Soy una descripcion";

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            Intent intent = new Intent(this, Prueba.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
            builder.setSmallIcon(R.drawable.ic_campana);
            builder.setContentTitle("Titulo");
            builder.setContentText("Mensaje UTP 2023");
            builder.setColor(Color.BLUE);
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
            builder.setLights(Color.MAGENTA, 1000, 1000);
            builder.setVibrate(new long[]{1000, 1000, 1000, 1000});
            builder.setDefaults(Notification.DEFAULT_SOUND);
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);

            builder.setChannelId(CHANNEL_ID);

            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }

    private void createNotification() {

        /*Intent snoozeIntent = new Intent(this, Prueba.class);
        snoozeIntent.setAction(ACTION_SNOOZE);
        snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, snoozeIntent, 0);
        */

        /*
        Intent intent = new Intent(this, Prueba.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        */

        String KEY_TEXT_REPLY = "key_text_reply";
        String replyLabel = "HELLO";
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel(replyLabel)
                .build();


        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_campana);
        builder.setContentTitle("Titulo");
        builder.setContentText("Mensaje UTP 2023");
        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);
        //builder.setContentIntent(pendingIntent);
        /*builder.setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_snooze, "Esperar",
                        pendingIntent);
                        */
        builder.setAutoCancel(true);


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }

    public void sendNotification(View view) {
        //createNoticationChannel();
        //createNotification();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

        NotificationCompat.Builder builder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "asd";
            String description = "ddd";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this.
            //NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


            Intent intent = new Intent(this, Prueba.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);


            String KEY_TEXT_REPLY = "key_text_reply";

            String replyLabel = "mama";
            RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                    .setLabel(replyLabel)
                    .build();


            PendingIntent replyPendingIntent = PendingIntent
                    .getBroadcast(getApplicationContext(),1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Action action =
                    new NotificationCompat.Action.Builder(R.drawable.ic_snooze, "Cool", replyPendingIntent)
                            .addRemoteInput(remoteInput)
                            .build();

            builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_campana)
                    .setContentTitle("My notification 1")
                    .setContentText("Hello World! 1")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    // Set the intent that fires when the user taps the notification.
                    //.setContentIntent(pendingIntent)
                    .addAction(action)
                    .setAutoCancel(true);


        } else {
            builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_campana)
                    .setContentTitle("My notification 2")
                    .setContentText("Hello World! 2")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    // Set the intent that fires when the user taps the notification.
                    //.setContentIntent(pendingIntent)
                    .setAutoCancel(true);
        }

        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000});

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(1234, builder.build());
    }
}