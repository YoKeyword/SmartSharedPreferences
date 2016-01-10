package me.yokeyword.api.spf.field;

import android.content.SharedPreferences;

import me.yokeyword.api.spf.BaseSpfField;

/**
 * Created by YoKeyword on 16/1/8.
 */
public class StringSpfField extends BaseSpfField<String> {

    public StringSpfField(SharedPreferences sharedPreferences, String key) {
        super(sharedPreferences, key);
    }

    @Override
    public String get(String defaultValue) {
        if (defaultValue == null) {
            defaultValue = "";
        }
        return _sharedPreferences.getString(_key, defaultValue);
    }

    @Override
    public void put(String value) {
        apply(edit().putString(_key, value));
    }
}
