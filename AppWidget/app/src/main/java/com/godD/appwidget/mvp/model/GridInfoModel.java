package com.godD.appwidget.mvp.model;/**
 * @author David  create on 2016/8/10  16:57.
 * @email david.forever.god@gmail.com
 * Learn from yesterday, live for today, hope for tomorrow.
 */

import com.godD.appwidget.bean.WidgetEntity;

/**
 * author: {Davide} on 2016/8/10.
 * email : {suwei@acooo.cn}
 * Difficulties vanish when faced boldly.
 */
public interface GridInfoModel {

    void loadWidgetInfo(OnLoadWidgetInfoListener onLoadWidgetInfoListener);

    interface OnLoadWidgetInfoListener {
        void loadSuccess(WidgetEntity  infoList);

        void loadFailed(String s);
    }
}
