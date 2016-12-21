package com.stfalcon.vkphotogallery.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/*
 * Created by troy379 on 21.12.16.
 */
public final class AppUtils {
    private AppUtils() {
        throw new AssertionError();
    }

    public static void makeToast(Context context, @StringRes int text, boolean isLong) {
        makeToast(context, context.getString(text), isLong);
    }

    public static void makeToast(Context context, String text, boolean isLong) {
        Toast.makeText(
                context,
                text,
                isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT
        ).show();
    }
}
