package com.godD.appwidget;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.godD.appwidget.config.WidgetAction;

public class MainActivity extends AppCompatActivity {

    private ImageView showIv;
    private TextView showTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showIv = (ImageView) findViewById(R.id.main_iv);
        showTv = (TextView) findViewById(R.id.main_tv);

        Intent intent = getIntent();
        getWidgetInfo(intent);
    }

    private void getWidgetInfo(Intent intent) {

        switch (intent.getAction()) {
            case WidgetAction.GOD_GRID:
                //接口中有图片网址，就顺便让大家去看看吧,虽然，很俗... ...
                String url = intent.getStringExtra("url");
                Uri uri = Uri.parse(url);
                Intent intentWeb = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intentWeb);
                break;
            case WidgetAction.GOD_START_ACTIVITY:
                showTv.setVisibility(View.GONE);
                showIv.setVisibility(View.VISIBLE);
                showIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showIv.setImageResource(R.drawable.refreshing);
                    }
                });
                break;
        }

    }
}
