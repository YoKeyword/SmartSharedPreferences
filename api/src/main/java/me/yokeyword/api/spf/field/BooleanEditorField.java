package me.yokeyword.api.spf.field;

import me.yokeyword.api.spf.BaseEditorField;
import me.yokeyword.api.spf.EditorHelper;

/**
 * Created by YoKeyword on 16/1/8.
 */
public class BooleanEditorField<E extends EditorHelper> extends BaseEditorField<Boolean, E> {

    public BooleanEditorField(E editorHelper, String key) {
        super(editorHelper, key);
    }

    @Override
    public E put(Boolean value) {
        _editorHelper.getEditor().putBoolean(_key, value);
        return _editorHelper;
    }
}
