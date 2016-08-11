package com.godD.appwidget.mvp.presenter;

import com.godD.appwidget.bean.WidgetEntity;
import com.godD.appwidget.mvp.model.GridInfoModel;
import com.godD.appwidget.mvp.model.GridInfoModelImpl;
import com.godD.appwidget.mvp.view.GridInfoView;

/**
 * @author David  create on 2016/8/11  10:00.
 * @email david.forever.god@gmail.com
 * Learn from yesterday, live for today, hope for tomorrow.
 */
public class GridInfoPresenter implements GridInfoModel.OnLoadWidgetInfoListener {
    private GridInfoView gridInfoView;
    private GridInfoModel gridInfoModel;

    public GridInfoPresenter(GridInfoView gridInfoView) {
        this.gridInfoView = gridInfoView;
        gridInfoModel = new GridInfoModelImpl();
    }

    public void loadWidgetInfo() {
        gridInfoModel.loadWidgetInfo(this);
    }

    @Override
    public void loadSuccess(WidgetEntity infoList) {
        gridInfoView.loadWidgetInfo(infoList);
    }

    @Override
    public void loadFailed(String s) {
        gridInfoView.loadFailed(s);
    }
}
