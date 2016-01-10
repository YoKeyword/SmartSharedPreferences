package me.yokeyword.api.spf;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by YoKeyword on 16/1/8.
 */
public abstract class SpfHelper {

    protected final SharedPreferences sharedPreferences;

    public SpfHelper(Context context, String suffixName) {
        this.sharedPreferences = context.getSharedPreferences(getSpfName() + suffixName, 0);
    }

    private String getSpfName() {
        return "MySpf";
    }

    public final SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }

    public final void clear() {
        sharedPreferences.edit().clear().apply();
    }
}
