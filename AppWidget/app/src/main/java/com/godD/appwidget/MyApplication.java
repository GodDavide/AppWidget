package com.godD.appwidget;

import android.app.Application;

import com.baidu.apistore.sdk.ApiStoreSDK;
import com.godD.appwidget.bean.WidgetEntity;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

/**
 * @author David  create on 2016/8/10  16:52.
 * @email david.forever.god@gmail.com
 * Learn from yesterday, live for today, hope for tomorrow.
 */
public class MyApplication extends Application {
    private DbUtils dbUtils;
    private static MyApplication myApp;

    @Override
    public void onCreate() {
        super.onCreate();
        this.myApp = this;
        ApiStoreSDK.init(this, "您自己的ApiKey");
        initWidgetDb();
    }

    private void initWidgetDb() {

        dbUtils = DbUtils.create(this, "/sdcard/data", "godwidget.db", 1, new DbUtils.DbUpgradeListener() {

            @Override
            public void onUpgrade(DbUtils dbUtils, int i, int i1) {
                if (i < i1) {
                    try {
                        dbUtils.dropTable(WidgetEntity.NewslistBean.class);
                        dbUtils.createTableIfNotExist(WidgetEntity.NewslistBean.class);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //创建表
        try {
            dbUtils.createTableIfNotExist(WidgetEntity.NewslistBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public DbUtils getDbUtils() {
        return dbUtils;
    }

    public static MyApplication getMyApp() {
        return myApp;
    }
}
