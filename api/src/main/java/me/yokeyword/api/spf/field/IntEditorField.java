package me.yokeyword.api.spf.field;

import me.yokeyword.api.spf.*;

/**
 * Created by YoKeyword on 16/1/8.
 */
public class IntEditorField<E extends me.yokeyword.api.spf.EditorHelper> extends BaseEditorField<Integer, E> {

    public IntEditorField(E editorHelper, String key) {
        super(editorHelper, key);
    }

    @Override
    public E put(Integer value) {
        _editorHelper.getEditor().putInt(_key, value);
        return _editorHelper;
    }
}
