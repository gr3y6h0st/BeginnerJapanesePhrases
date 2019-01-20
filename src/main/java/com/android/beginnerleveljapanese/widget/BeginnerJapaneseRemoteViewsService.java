package com.android.beginnerleveljapanese.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class BeginnerJapaneseRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BeginnerJapaneseRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}
