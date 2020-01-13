package top.youlanqiang.mixorm.mate;

import top.youlanqiang.mixorm.annotation.DbColumn;
import top.youlanqiang.mixorm.annotation.DbId;
import top.youlanqiang.mixorm.annotation.DbName;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

class TableAbstractClassParser<T> extends AbstractClassParser<T> {

    TableAbstractClassParser(Class<T> clazz){
        super(clazz);
        if(needParse(clazz)){
            throw new RuntimeException("Class no DbName annotation.");
        }
    }

    @Override
    void loadClass(Class<T> clazz, EntityMate mate) {
        mate.setClazz(clazz);
    }

    @Override
    void loadFields(Class<T> clazz, EntityMate mate) {
        Field[] fields = clazz.getDeclaredFields();
        Map<String, EntityField> map = new HashMap<>(fields.length);
        for(Field field : fields){
            if(needParse(field)){
                AbstractFieldParser parser = new TableAbstractFieldParser(field);
                EntityField entityField = parser.getEntityField();
                map.put( entityField.getColumnName(), entityField);
            }
        }

        mate.setFields(map);
    }

    @Override
    void loadTableName(Class<T> clazz, EntityMate mate) {
        DbName dbName = clazz.getAnnotation(DbName.class);
        mate.setTableName(dbName.value());
    }

    @Override
    void loadHasId(Class<T> clazz, EntityMate mate) {
        Map<String, EntityField> map =  mate.getFields();
        for (String key : map.keySet()) {
            EntityField field = map.get(key);
            if(field.isId()){
                mate.setHasId(true);
                mate.setIdEntity(field);
            }
        }

    }

    private boolean needParse(Class<T> clazz){
        return clazz.getDeclaredAnnotation(DbName.class) != null;
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
}
