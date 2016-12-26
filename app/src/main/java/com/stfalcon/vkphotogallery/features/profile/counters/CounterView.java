package com.stfalcon.vkphotogallery.features.profile.counters;

import android.content.Context;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stfalcon.vkphotogallery.R;

/*
 * Created by troy379 on 21.12.16.
 */
public class CounterView extends RelativeLayout {

    private TextView tvCounter;
    private TextView tvTitle;

    public CounterView(Context context) {
        super(context);
        init(context);
    }

    public CounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CounterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View root = inflate(context, R.layout.view_counter, this);

        tvCounter = (TextView) root.findViewById(R.id.tvCounter);
        tvTitle = (TextView) root.findViewById(R.id.tvTitle);
    }


    public void setTitle(@StringRes int title) {
        tvTitle.setText(title);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setCount(int count) {
        tvCounter.setText(getCounterShortText(count));
    }

    public static String getCounterShortText(int count) {
        int value = count;
        String suffix = "";

        for (int i = count; i >= 10000; i = i / 1000) {
            suffix = suffix.concat("k");
            value = value / 1000;
        }

        return value + suffix;
    }
}
