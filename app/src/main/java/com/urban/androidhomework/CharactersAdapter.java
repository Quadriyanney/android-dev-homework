package com.urban.androidhomework;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class CharactersAdapter extends ArrayAdapter<String> {

    public CharactersAdapter(@NonNull Context context, int resource, @NonNull String[] objects) {
        super(context, resource, objects);
    }

}
