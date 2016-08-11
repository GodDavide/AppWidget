package com.godD.appwidget.appWidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.godD.appwidget.MyApplication;
import com.godD.appwidget.R;
import com.godD.appwidget.bean.WidgetEntity;
import com.godD.appwidget.utils.LruCacheCallBack;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David  create on 2016/8/10  19:44.
 * @email david.forever.god@gmail.com
 * Learn from yesterday, live for today, hope for tomorrow.
 */
public class WidgetGridService extends RemoteViewsService {


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViewFactory(this, intent);
    }

    private class GridRemoteViewFactory implements RemoteViewsFactory {

        private Context context;
        private int appWidgetId;
        private List<WidgetEntity.NewslistBean> newslist = new ArrayList<>();

        public GridRemoteViewFactory(Context context, Intent intent) {
            this.context = context;
            this.appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        /**
         * @author David  created at 2016/8/11 17:41
         * 首次执行，初始化数据时执行onCreate();数据是从数据库里拿的。
         */
        @Override
        public void onCreate() {
            try {
                newslist = MyApplication.getMyApp().getDbUtils().findAll(WidgetEntity.NewslistBean.class);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }

        /**
         * @author David  created at 2016/8/11 17:41
         * 当数据源发生变化时，AppWidgetManager调用了 notifyAppWidgetViewDataChanged();方法时执行
         */
        @Override
        public void onDataSetChanged() {
            try {
                newslist = MyApplication.getMyApp().getDbUtils().findAll(WidgetEntity.NewslistBean.class);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }

        /**
         * @author David  created at 2016/8/11 17:42
         * 销毁时，情况数据源
         */
        @Override
        public void onDestroy() {
            newslist.clear();
        }

        /**
         * @author David  created at 2016/8/11 17:43
         * 返回Gridview的Item条目数
         */
        @Override
        public int getCount() {
            return newslist.size();
        }

        /**
         * @author David  created at 2016/8/11 17:43
         * 给Gridview设置数据
         */
        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.item_grid);
            if (newslist != null) {
                WidgetEntity.NewslistBean newslistBean = newslist.get(position);
                /**
                 * @author David  created at 2016/8/11 13:52
                 * remoteViews.setImageViewUri(R.id.item_image, Uri.parse(newslistBean.getPicUrl()));
                 *  这个方法不知道为什么总是设置不上图片，于是改用setImageViewBitmap();
                 *
                 *  备注：此处加了图片的三级缓存，如果不需要的话，可直接调用
                 *  URL picUrl  = new URL(key);
                 *  Bitmap  bitmap = BitmapFactory.decodeStream(picUrl.openStream());
                 *  可拿到bitmap;
                 */
                Bitmap bitmap = LruCacheCallBack.bitmapCallMap(newslistBean.getPicUrl(), context);
                if (bitmap != null) {
                    remoteViews.setImageViewBitmap(R.id.item_image, bitmap);
                }
                remoteViews.setTextViewText(R.id.item_text, newslistBean.getTitle());

                //给item设置响应事件
                Intent intent = new Intent();
                intent.putExtra("url", newslistBean.getUrl());
                remoteViews.setOnClickFillInIntent(R.id.item_widget, intent);
            }
            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        /**
         * @author David  created at 2016/8/11 17:46
         * 无特殊要求，返回的View类型数 ==1；
         */
        @Override
        public int getViewTypeCount() {
            return 1;
        }

        /**
         * @author David  created at 2016/8/11 17:47
         * 返回item的id
         */
        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
