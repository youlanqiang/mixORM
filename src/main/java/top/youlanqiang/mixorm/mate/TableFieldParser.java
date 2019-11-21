package top.youlanqiang.mixorm.mate;

import java.lang.reflect.Field;

public class TableFieldParser extends FieldParser {

    public TableFieldParser(Field field){
        super(field);
    }

    @Override
    void loadIsId(Field field, EntityField result) {

    }

    @Override
    void loadColumnName(Field field, EntityField result) {

    }

    @Override
    void loadColumnType(Field field, EntityField result) {

    }

    @Override
    void loadSetMethod(Field field, EntityField result) {

    }

    @Override
    void loadGetMethod(Field field, EntityField result) {

    }
}
