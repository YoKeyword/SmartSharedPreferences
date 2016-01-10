package me.yokeyword.api.spf.field;

import android.content.SharedPreferences;

import me.yokeyword.api.spf.BaseSpfField;

/**
 * Created by YoKeyword on 16/1/8.
 */
public class IntSpfField extends BaseSpfField<Integer> {

    public IntSpfField(SharedPreferences sharedPreferences, String key) {
        super(sharedPreferences, key);
    }

    @Override
    public Integer get(Integer defaultValue) {
        if (defaultValue == null) {
            defaultValue = 0;
        }
        return _sharedPreferences.getInt(_key, defaultValue);
    }

    @Override
    public void put(Integer value) {
        apply(edit().putInt(_key, value));
    }
}
