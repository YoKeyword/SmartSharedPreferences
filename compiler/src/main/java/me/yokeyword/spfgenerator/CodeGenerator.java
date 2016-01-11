package me.yokeyword.spfgenerator;


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.lang.model.element.Modifier;

import me.yokeyword.api.spf.EditorHelper;
import me.yokeyword.api.spf.SpfHelper;
import me.yokeyword.api.spf.field.BooleanEditorField;
import me.yokeyword.api.spf.field.BooleanSpfField;
import me.yokeyword.api.spf.field.FloatEditorField;
import me.yokeyword.api.spf.field.FloatSpfField;
import me.yokeyword.api.spf.field.IntEditorField;
import me.yokeyword.api.spf.field.IntSpfField;
import me.yokeyword.api.spf.field.LongEditorField;
import me.yokeyword.api.spf.field.LongSpfField;
import me.yokeyword.api.spf.field.StringEditorField;
import me.yokeyword.api.spf.field.StringSpfField;


/**
 * 代码生成
 * Created by YoKeyword on 16/1/7.
 */
public class CodeGenerator {
    private static final String CLASS_PREFIX = "Spf_";
    private static final String CLASS_EDITOR_PREFIX = "Editor_";

    private static final String METHOD_CREATE = "create";
    private static final String METHOD_EDIT = "edit";

    private final String className;
    private final String generatedClassName;
    private final ArrayList<HashMap<Class, String>> methodsList;

    private ClassName contextClass, spfClass, editorClass;

    public CodeGenerator(String packageName, String className, ArrayList<HashMap<Class, String>> methodsMap) {
        this.className = className;
        this.generatedClassName = CLASS_PREFIX + className;
        this.methodsList = methodsMap;

        contextClass = ClassName.get("android.content", "Context");
        editorClass = ClassName.get(packageName + "." + generatedClassName, CLASS_EDITOR_PREFIX + className);
        spfClass = ClassName.get(packageName, generatedClassName);
    }


    public TypeSpec generateClass() {
        return TypeSpec.classBuilder(generatedClassName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .superclass(SpfHelper.class)
                .addMethod(construcotr())
                .addMethod(create())
                .addMethods(fieldMethods())
                .addType(classEditor())
                .addMethod(edit())
                .build();
    }

    /**
     * 构造函数
     */
    private MethodSpec construcotr() {
        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PRIVATE)
                .addParameter(contextClass, "context")
                .addCode("super(context, $S);\n", "_" + className)
                .build();
    }

    /**
     * 构造者
     */
    private MethodSpec create() {
        return MethodSpec.methodBuilder(METHOD_CREATE)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(spfClass)
                .addParameter(contextClass, "context")
                .addCode("return new $T(context);\n", spfClass)
                .build();
    }

    /**
     * edit()
     */
    private MethodSpec edit() {
        return MethodSpec.methodBuilder(METHOD_EDIT)
                .addModifiers(Modifier.PUBLIC)
                .returns(editorClass)
                .addCode("return new $T(getEditor());\n", editorClass)
                .build();
    }

    /**
     * 属性方法
     */
    private Iterable<MethodSpec> fieldMethods() {
        List<MethodSpec> methodSpecs = new ArrayList<>();
        for (HashMap<Class, String> map : methodsList) {

            Class clazz = map.keySet().iterator().next();
            String fieldName = map.get(clazz);
            Class typeClass = null;
            if (clazz == String.class) {
                typeClass = StringSpfField.class;
            } else if (clazz == Integer.class) {
                typeClass = IntSpfField.class;
            } else if (clazz == Long.class) {
                typeClass = LongSpfField.class;
            } else if (clazz == Float.class) {
                typeClass = FloatSpfField.class;
            } else if (clazz == Boolean.class) {
                typeClass = BooleanSpfField.class;
            }
            if (typeClass != null) {
                MethodSpec methodSpec = getSpfFieldMethodSpec(fieldName, typeClass);
                methodSpecs.add(methodSpec);
            }
        }
        return methodSpecs;
    }

    private MethodSpec getSpfFieldMethodSpec(String fieldName, Class clazz) {
        return MethodSpec.methodBuilder(fieldName)
                .addModifiers(Modifier.PUBLIC)
                .returns(clazz)
                .addCode("return new $T(sharedPreferences,$S);\n", clazz, fieldName)
                .build();
    }

    /**
     * Editor类
     */
    private TypeSpec classEditor() {
        return TypeSpec.classBuilder(CLASS_EDITOR_PREFIX + className)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .superclass(ClassName.get(EditorHelper.class))
                .addMethod(editorConstrucotr())
                .addMethods(editorFieldMethods())
                .build();
    }

    /**
     * Editor类 构造函数
     *
     * @return
     */
    private MethodSpec editorConstrucotr() {
        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ClassName.get("android.content.SharedPreferences", "Editor"), "editor")
                .addCode("super(editor);\n")
                .build();
    }

    /**
     * Editor类 属性阿福
     *
     * @return
     */
    private Iterable<MethodSpec> editorFieldMethods() {
        List<MethodSpec> methodSpecs = new ArrayList<>();

        for (HashMap<Class, String> map : methodsList) {

            Class clazz = map.keySet().iterator().next();
            String fieldName = map.get(clazz);

            Class typeClass = null;
            if (clazz == String.class) {
                typeClass = StringEditorField.class;
            } else if (clazz == Integer.class) {
                typeClass = IntEditorField.class;
            } else if (clazz == Long.class) {
                typeClass = LongEditorField.class;
            } else if (clazz == Float.class) {
                typeClass = FloatEditorField.class;
            } else if (clazz == Boolean.class) {
                typeClass = BooleanEditorField.class;
            }
            if (typeClass != null) {
                MethodSpec methodSpec = getEditorFieldsMethodSpec(fieldName, typeClass);
                methodSpecs.add(methodSpec);
            }
        }
        return methodSpecs;
    }

    private MethodSpec getEditorFieldsMethodSpec(String fieldName, Class clazz) {
        MethodSpec methodSpec;
        methodSpec = MethodSpec.methodBuilder(fieldName)
                .addModifiers(Modifier.PUBLIC)
                .returns(ParameterizedTypeName.get(ClassName.get(clazz), editorClass))
                .addCode("return new $T(this,$S);\n", clazz, fieldName)
                .build();
        return methodSpec;
    }
}
