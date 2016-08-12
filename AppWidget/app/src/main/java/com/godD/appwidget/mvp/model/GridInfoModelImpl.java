package com.godD.appwidget.mvp.model;

import android.util.Log;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.godD.appwidget.bean.WidgetEntity;
import com.google.gson.Gson;

/**
 * @author David  create on 2016/8/10  19:09.
 * @email david.forever.god@gmail.com
 * Learn from yesterday, live for today, hope for tomorrow.
 */
public class GridInfoModelImpl implements GridInfoModel {
    @Override
    public void loadWidgetInfo(final OnLoadWidgetInfoListener onLoadWidgetInfoListener) {
        /**
         * @author  David  created at 2016/8/11 9:53
         *  测试接口使用的是百度ApiSore免费接口
         */
        Parameters para = new Parameters();
        ApiStoreSDK.execute("http://apis.baidu.com/txapi/huabian/newtop/?num=10&page=1&key=您自己的ApiKey",
                ApiStoreSDK.GET,
                para,
                new ApiCallBack() {
                    @Override
                    public void onSuccess(int status, String responseString) {
                        Log.i("sdkdemo", "onSuccess___" + responseString);
                        Gson gson = new Gson();
                        WidgetEntity entity = gson.fromJson(responseString, WidgetEntity.class);
                        onLoadWidgetInfoListener.loadSuccess(entity);


                    }

                    @Override
                    public void onComplete() {
                        Log.i("sdkdemo", "onComplete");
                    }

                    @Override
                    public void onError(int status, String responseString, Exception e) {
                        Log.i("sdkdemo", "onError, status: " + status);
                        Log.i("sdkdemo", "errMsg: " + (e == null ? "" : e.getMessage()));
                        onLoadWidgetInfoListener.loadFailed("请求失败");
                    }

                });

    }


}
