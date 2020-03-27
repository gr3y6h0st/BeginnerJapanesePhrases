package com.jaydroid.beginnerleveljapanese.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.jaydroid.beginnerleveljapanese.R;


public class BeginnerJapaneseRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private static final int mCount = 10;
    private Context mContext;
    private int mAppWidgetId;
    String[] romajiArray;
    String randomRomajiPhrase;

    public BeginnerJapaneseRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }
    @Override
    public void onCreate() {

        //setup connections/cursors to data source here. Heavy lifting should be deferred to onDataSetchanged()
        //or getViewAt()

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if(position == AdapterView.INVALID_POSITION ){
            return null;
        }


        RemoteViews ib = new RemoteViews(mContext.getPackageName(), R.layout.beginner_japanese_widget);
        ib.setImageViewResource(R.id.widget_translator_search_icon, R.drawable.arrow_down);
        ib.setString(R.id.widget_phrases_romaji_tv, "getRomaji", randomRomajiPhrase);

        return ib;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
