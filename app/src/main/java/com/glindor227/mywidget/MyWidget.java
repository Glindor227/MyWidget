package com.glindor227.mywidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Arrays;
import java.util.Objects;


public class MyWidget extends AppWidgetProvider {
    final String LOG_TAG = "glindorLog";
    public static String ACTION_WIDGET_RECEIVER = "ActionReceiverWidget";
    private static String INTENT_KEY = "msg_glindor";

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(LOG_TAG, "onEnabled");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        RemoteViews remoteViews;
        Log.d(LOG_TAG, "onUpdate " + Arrays.toString(appWidgetIds));

        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        Intent active = new Intent(context, MyWidget.class);
        active.setAction(ACTION_WIDGET_RECEIVER);
        active.putExtra(INTENT_KEY, "Button message");
        PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context, 0, active, 0);
        remoteViews.setOnClickPendingIntent(R.id.widget_button, actionPendingIntent);

        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (ACTION_WIDGET_RECEIVER.equals( intent.getAction())) {
            Log.d(LOG_TAG, Objects.requireNonNull(intent.getStringExtra(INTENT_KEY)));
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(LOG_TAG, "onDeleted " + Arrays.toString(appWidgetIds));
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(LOG_TAG, "onDisabled");
    }
}
