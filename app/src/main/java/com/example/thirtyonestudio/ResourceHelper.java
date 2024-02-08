package com.example.thirtyonestudio;

import android.content.Context;
import android.net.Uri;

public class ResourceHelper {

    public static Uri getResourceUri(Context context, int resId) {
        return Uri.parse("android.resource://" + context.getPackageName() + "/" + resId);
    }
}
