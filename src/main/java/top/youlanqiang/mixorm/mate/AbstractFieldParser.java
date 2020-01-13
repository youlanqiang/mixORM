package top.youlanqiang.mixorm.mate;

import java.lang.reflect.Field;

/**
 * 字段解析类
 * @author youlanqiang
 */
abstract class AbstractFieldParser {


    private Field field;

    private EntityField result;

    AbstractFieldParser(Field field){
        this.field = field;
        this.result = new EntityField();
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

    /**
     * 将字段是否是主键添加到字段元数据中
     * @param field class字段
     * @param result 字段元数据
     */
    abstract void loadIsId(Field field, EntityField result);

    /**
     * 将字段名称添加到字段元数据中
     * @param field class字段
     * @param result 字段元数据
     */
    abstract void loadFieldName(Field field, EntityField result);

    /**
     * 将数据库字段名称添加到字段元数据中
     * @param field class字段
     * @param result 字段元数据
     */
    abstract void loadColumnName(Field field, EntityField result);

    /**
     * 将数据库字段类型添加到字段元数据中
     * @param field class字段
     * @param result 字段元数据
     */
    abstract void loadColumnType(Field field, EntityField result);

    /**
     * 将字段set方法添加到字段元数据中
     * @param field class字段
     * @param result 字段元数据
     */
    abstract void loadSetMethod(Field field, EntityField result);

    /**
     * 将字段get方法添加到字段元数据中
     * @param field class字段
     * @param result 字段元数据
     */
    abstract void loadGetMethod(Field field, EntityField result);


    public EntityField getEntityField(){
        parse();
        return this.result;
    }

}
