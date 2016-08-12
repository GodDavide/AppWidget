package com.godD.appwidget.appWidget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.godD.appwidget.MyApplication;
import com.godD.appwidget.R;
import com.godD.appwidget.bean.WidgetEntity;
import com.godD.appwidget.mvp.presenter.GridInfoPresenter;
import com.godD.appwidget.mvp.view.GridInfoView;
import com.lidroid.xutils.exception.DbException;

import java.util.List;

/**
 * @author David  create on 2016/8/11  11:34.
 * @email david.forever.god@gmail.com
 * Learn from yesterday, live for today, hope for tomorrow.
 */
public class MyService extends Service implements GridInfoView {

    private GridInfoPresenter gridInfoPresenter;
    private int[] appWidgetIds;
    private AppWidgetManager appWidgetManager;
    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //拿到更新所需要的内容：appWidgetIds;appWidgetManager;
        appWidgetIds = intent.getIntArrayExtra("appWidgetIds");
        appWidgetManager = AppWidgetManager.getInstance(MyService.this);
        //利用MVP模式，下载数据。备注：如果对MVP不是很了解的话，也可以使用自己的请求方式请求数据即可。
        //另：如果你想学习MVP模式的话：可前往“https://github.com/GodDavide/MVP”查看MVP模式介绍，感谢您的支持。
        Toast.makeText(MyService.this, "正在加载最新数据，请稍等... ...", Toast.LENGTH_SHORT).show();
        gridInfoPresenter = new GridInfoPresenter(this);
        gridInfoPresenter.loadWidgetInfo();

        //初始化Widget（此时Gridview还没有最新数据，如果是首次创建的话，数据为空，非首次，显示的是上次请求的数据。数据存储在数据库里）
        //为了方便，将更新方法直接写进构造函数里了
        new UpdaeWidget(MyService.this, appWidgetIds, appWidgetManager);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //当数据下砸完成后，返回信息，因为数据源发生了改变，所以直接调用：notifyAppWidgetViewDataChanged  方法；
                //WidgetGridService将自动执行:onDataSetChanged()方法，然后，从新对Gridview进行赋值刷新。
                if (msg.what == 100) {
                    appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_grid);
                }
            }
        };

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void loadWidgetInfo(WidgetEntity widgetEntity) {
        //利用MVP模式下载数据，下载成功后，首先更新数据库，然后，通过Handler返回信息，执行刷新Widget功能。
        try {
            if (widgetEntity != null) {
                Log.e("David", "______loadWidgetInfo: ___不为空___" + widgetEntity.getNewslist().size());
                //首先，清空数据库，然后，将新数据添加进去。
                MyApplication.getMyApp().getDbUtils().deleteAll(WidgetEntity.NewslistBean.class);
                List<WidgetEntity.NewslistBean> all = MyApplication.getMyApp().getDbUtils().findAll(WidgetEntity.NewslistBean.class);
                for (int i = 0; i < widgetEntity.getNewslist().size(); i++) {
                    String picUrl = widgetEntity.getNewslist().get(i).getPicUrl();
                    Log.e("David", "______loadWidgetInfo: ___pic___" + picUrl);
                    MyApplication.getMyApp().getDbUtils().saveOrUpdate(widgetEntity.getNewslist().get(i));
                }
                Message obtain = Message.obtain();
                obtain.what = 100;
                handler.sendMessage(obtain);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadFailed(String s) {
        Toast.makeText(MyService.this, "数据请求失败,请稍后重试... ...", Toast.LENGTH_SHORT).show();
    }
}
