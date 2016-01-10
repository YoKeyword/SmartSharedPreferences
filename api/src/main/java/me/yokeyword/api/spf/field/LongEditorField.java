package me.yokeyword.api.spf.field;

import me.yokeyword.api.spf.*;

/**
 * Created by YoKeyword on 16/1/8.
 */
public class LongEditorField<E extends me.yokeyword.api.spf.EditorHelper> extends BaseEditorField<Long, E> {

    public LongEditorField(E editorHelper, String key) {
        super(editorHelper, key);
    }

    @Override
    public E put(Long value) {
        _editorHelper.getEditor().putLong(_key, value);
        return _editorHelper;
    }
}
