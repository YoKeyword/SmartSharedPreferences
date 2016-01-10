package me.yokeyword.api.spf.field;

import me.yokeyword.api.spf.*;

/**
 * Created by YoKeyword on 16/1/8.
 */
public class StringEditorField<E extends me.yokeyword.api.spf.EditorHelper> extends BaseEditorField<String, E> {

    public StringEditorField(E editorHelper, String key) {
        super(editorHelper, key);
    }

    @Override
    public E put(String value) {
        _editorHelper.getEditor().putString(_key, value);
        return _editorHelper;
    }
}
