package com.godD.appwidget.appWidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.godD.appwidget.MainActivity;
import com.godD.appwidget.R;
import com.godD.appwidget.config.WidgetAction;

/**
 * @author David  create on 2016/8/11  11:39.
 * @email david.forever.god@gmail.com
 * Learn from yesterday, live for today, hope for tomorrow.
 */
public class UpdaeWidget {
    private RemoteViews remoteViews;
    private Context context;
    private int[] appWidgetIds;
    private AppWidgetManager appWidgetManager;

    public UpdaeWidget(Context context, int[] appWidgetIds, AppWidgetManager appWidgetManager) {
        this.context = context;
        this.appWidgetIds = appWidgetIds;
        this.appWidgetManager = appWidgetManager;
        this.remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
        //为了方便，在创建UpdateWidget实例的时候，直接调用UpdateWidgetView();
        UpdateWidgetView();

    }

    public void UpdateWidgetView() {
        for (int appWidgetId : appWidgetIds) {
            /**
             * @author David  created at 2016/8/11 17:37
             *  刷新按钮的点击事件,通过Intent启动服务，PendingIntent.getSwevice();
             */
            Intent intent = new Intent(context, MyService.class);
            intent.putExtra("appWidgetIds", appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.widget_title, pendingIntent);
            remoteViews.setOnClickPendingIntent(R.id.widget_refresh, pendingIntent);

            /**
             * @author David  created at 2016/8/11 17:37
             *  显示Topic及点击事件,通过Intent启动Activity，PendingIntent.getActivity();
             */
            remoteViews.setImageViewResource(R.id.widget_image, R.drawable.ss_bg);
            Intent topicIntent = new Intent(context, MainActivity.class);
            topicIntent.setAction(WidgetAction.GOD_START_ACTIVITY);
            topicIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntentActivity = pendingIntent.getActivity(context, 0, topicIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.widget_image, pendingIntentActivity);

            /**
             * @author David  created at 2016/8/11 17:37
             *  加载Gridview
             */
            showGridInfo();

            /**
             * @author David  created at 2016/8/11 17:37
             *  最后，刷新remoteViews
             */
            appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
        }
    }

    private void showGridInfo() {
        //设置数据显示
        Intent intent = new Intent(context, WidgetGridService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds);
        remoteViews.setRemoteAdapter(R.id.widget_grid, intent);
        remoteViews.setEmptyView(R.id.widget_grid, R.id.widget_empty_view);
        //响应事件
        Intent intentEvent = new Intent(context, MainActivity.class);
        intentEvent.setAction(WidgetAction.GOD_GRID);
        intentEvent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentEvent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setPendingIntentTemplate(R.id.widget_grid, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

}
