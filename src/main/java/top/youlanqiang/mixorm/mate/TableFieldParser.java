package top.youlanqiang.mixorm.mate;

import top.youlanqiang.mixorm.annotation.DbColumn;
import top.youlanqiang.mixorm.annotation.DbId;

import java.lang.reflect.Field;

public class TableFieldParser extends FieldParser {

    public TableFieldParser(Field field) {
        super(field);
        if (!needParse(field)) {
            throw new RuntimeException("");
        }
    }

    @Override
    void loadIsId(Field field, EntityField result) {
        DbId dbId = field.getAnnotation(DbId.class);
        result.setId(true);
        result.setIdType(dbId.type());

    }

    @Override
    void loadFieldName(Field field, EntityField result) {

        result.setFieldName(field.getName());

    }

    @Override
    void loadColumnName(Field field, EntityField result) {

        DbId dbId = field.getAnnotation(DbId.class);
        if (dbId != null) {
            result.setColumnName(dbId.value());
        } else {
            DbColumn dbColumn = field.getAnnotation(DbColumn.class);
            result.setColumnName(dbColumn.value());
        }

    }

    @Override
    void loadColumnType(Field field, EntityField result) {

        result.setColumnType(field.getType());

    }

    @Override
    void loadSetMethod(Field field, EntityField result) {

        result.setGetMethod("set" + toFirstUpper(field.getName()));

    }

    @Override
    void loadGetMethod(Field field, EntityField result) {

        result.setGetMethod("get" + toFirstUpper(field.getName()));

    }

    private boolean needParse(Field field) {
        boolean needParse = false;
        if (field.getAnnotation(DbId.class) != null) {
            needParse = true;
        }
        if (field.getAnnotation(DbColumn.class) != null) {
            needParse = true;
        }
        return needParse;
    }

    private String toFirstUpper(String str) {
        String first = str.substring(0, 1);
        String after = str.substring(1);
        return first.toUpperCase() + after;
    }
}
