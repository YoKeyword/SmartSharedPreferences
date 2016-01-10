package me.yokeyword.api.spf.field;

import android.content.SharedPreferences;

import me.yokeyword.api.spf.BaseSpfField;

/**
 * Created by YoKeyword on 16/1/8.
 */
public class FloatSpfField extends BaseSpfField<Float> {

    public FloatSpfField(SharedPreferences sharedPreferences, String key) {
        super(sharedPreferences, key);
    }

    @Override
    public Float get(Float defaultValue) {
        if (defaultValue == null) {
            defaultValue = 0F;
        }
        return _sharedPreferences.getFloat(_key, defaultValue);
    }

    @Override
    public void put(Float value) {
        apply(edit().putFloat(_key, value));
    }
}
