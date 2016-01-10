package me.yokeyword.api.spf.field;

import me.yokeyword.api.spf.*;

/**
 * Created by YoKeyword on 16/1/8.
 */
public class FloatEditorField<E extends me.yokeyword.api.spf.EditorHelper> extends BaseEditorField<Float, E> {

    public FloatEditorField(E editorHelper, String key) {
        super(editorHelper, key);
    }

    @Override
    public E put(Float value) {
        _editorHelper.getEditor().putFloat(_key, value);
        return _editorHelper;
    }
}
