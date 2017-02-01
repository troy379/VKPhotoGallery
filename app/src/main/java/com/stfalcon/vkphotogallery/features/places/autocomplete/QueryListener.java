package com.stfalcon.vkphotogallery.features.places.autocomplete;

import android.text.Editable;
import android.text.TextWatcher;

/*
 * Created by troy379 on 30.01.17.
 */
public abstract class QueryListener implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        onQueryChanged(editable.toString());
    }

    public abstract void onQueryChanged(String query);
}
