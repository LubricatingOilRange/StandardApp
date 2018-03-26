package com.net.work.util;

import android.drm.DrmStore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

public class GsonUtil {
    private static Gson gson;

    static Gson defaultGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .setDateFormat("yyyy/MM/dd HH:mm:ss")
                   // .registerTypeAdapter(Date.class,new DrmStore.Action())
                    .create();
        }
        return gson;
    }
}
