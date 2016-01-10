package me.yokeyword.api.spf;

import android.content.SharedPreferences;

/**
 * Created by YoKeyword on 16/1/8.
 */
public abstract class BaseSpfField<T> {
    protected final SharedPreferences _sharedPreferences;
    protected final String _key;

    public BaseSpfField(SharedPreferences sharedPreferences, String key) {
        this._sharedPreferences = sharedPreferences;
        this._key = key;
    }

    public final boolean exists() {
        return _sharedPreferences.contains(_key);
    }

    public final void remove() {
        apply(edit().remove(_key));
    }

    public final String key() {
        return this._key;
    }

    public final T get() {
        return get(null);
    }

    public abstract T get(T defaultValue);

    public abstract void put(T value);

    protected SharedPreferences.Editor edit() {
        return _sharedPreferences.edit();
    }

    protected final void apply(SharedPreferences.Editor editor) {
        editor.apply();
    }
}
