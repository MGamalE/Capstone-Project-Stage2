package com.google.capstone.udacity.chattingme;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.google.capstone.udacity.chattingme.model.ChatMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class ChatWidget extends AppWidgetProvider {

    private static final String PREF = "pref";
    private static final String MESSAGES = "messages";

    private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                        int appWidgetId) {
        String allMessages = "";
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);

        String messages = sharedPreferences.getString(MESSAGES, "");
        Type list = new TypeToken<ArrayList<ChatMessage>>() {
        }.getType();

        ArrayList<ChatMessage> messageList = gson.fromJson(messages, list);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.user_widget);
        view.setOnClickPendingIntent(R.id.list_widget, appPendingIntent);

        if (messageList != null) {
            for (int i = 0; i < messageList.size(); i++) {
                allMessages += " " + messageList.get(i).getName() + ":" + " \n" + messageList.get(i).getText() + " \n";
            }

            if (allMessages != null) {
                view.setTextViewText(R.id.list_widget, allMessages);
            }
        }


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, view);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

