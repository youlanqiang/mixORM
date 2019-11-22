package top.youlanqiang.mixorm.mate;

import java.lang.reflect.Field;

abstract class FieldParser {


    private Field field;

    private EntityField result;

    public FieldParser(Field field){
        this.field = field;
    }

    private void parse(){
        if(field == null){
            throw new NullPointerException("Field is null!");
        }
        loadIsId(field, result);
        loadFieldName(field, result);
        loadColumnName(field, result);
        loadColumnType(field, result);
        loadSetMethod(field, result);
        loadGetMethod(field, result);
    }

    abstract void loadIsId(Field field, EntityField result);

    abstract void loadFieldName(Field field, EntityField result);

    abstract void loadColumnName(Field field, EntityField result);

    abstract void loadColumnType(Field field, EntityField result);

    abstract void loadSetMethod(Field field, EntityField result);

    abstract void loadGetMethod(Field field, EntityField result);


    public EntityField getEntityField(){
        parse();
        return this.result;
    }

}
