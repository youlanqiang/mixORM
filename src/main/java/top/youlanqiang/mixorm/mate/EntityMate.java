package top.youlanqiang.mixorm.mate;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author youlanqiang
 */
public class EntityMate<T> {

    private Class<T> clazz;

    private String tableName;

    private boolean hasId;

    private EntityField idEntity;


    private Map<String, EntityField> fields;


    /**
     * 获取主键value值
     * @param t 实体对象
     * @return 主键value值
     */
    public Object getPrimaryKeyValue(T t){
        try {
            EntityField idField = getIdEntity();
            Method method  = clazz.getDeclaredMethod(idField.getGetMethod(), null);
            method.setAccessible(true);
            return method.invoke(t,  null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 实体对象注入主类
     * @param keyValue 主键值
     * @param t 实体对象
     */
    public void autowiredKey(Object keyValue, T t){
        try {
            EntityField idField = getIdEntity();
            Method method  = clazz.getDeclaredMethod(idField.getSetMethod(), idField.getColumnType());
            method.setAccessible(true);
            method.invoke(t,  keyValue);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取字段名和值，包含主键,不包含为null的字段
     * @param result 对象实体类
     * @return 返回对象的字段名和值
     */
    public Map<String, Object> getVariableSkipNull(T result){
        List<EntityField> fields = new ArrayList<>(getFields().values());
        if(hasId){
            fields.add(getIdEntity());
        }
        Map<String, Object> variable = new HashMap<>(fields.size());
        for (EntityField field : fields) {
            Object value = null;
            try {
                Method method  = clazz.getDeclaredMethod(field.getGetMethod(),  null);
                method.setAccessible(true);
                value = method.invoke(result,  null);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            if(value != null) {
                variable.put(field.getColumnName(), value);
            }
        }
        return variable;
    }

    /**
     * 获取字段名和值,不包含为null的字段和主键的字段
     * @param result 对象实体类
     * @return 返回对象的字段名和值
     */
    public Map<String, Object> getVariableSkipNullAndId(T result){
        List<EntityField> fields = new ArrayList<>(getFields().values());
        Map<String, Object> variable = new HashMap<>(fields.size());
        for (EntityField field : fields) {
            if(field.isId()){
                continue;
            }
            Object value = null;
            try {
                Method method  = clazz.getDeclaredMethod(field.getGetMethod(),  null);
                method.setAccessible(true);
                value = method.invoke(result,  null);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            if(value != null) {
                variable.put(field.getColumnName(), value);
            }
        }
        return variable;
    }

    /**
     * 获取字段名和值,不包含主键的字段
     * @param result 对象实体类
     * @return 返回对象的字段名和值
     */
    public Map<String, Object> getVariableSkipId(T result){
        List<EntityField> fields = new ArrayList<>(getFields().values());
        Map<String, Object> variable = new HashMap<>(fields.size());
        for (EntityField field : fields) {
            if(field.isId()){
                continue;
            }
            Object value = null;
            try {
                Method method  = clazz.getDeclaredMethod(field.getGetMethod(),  null);
                method.setAccessible(true);
                value = method.invoke(result,  null);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            variable.put(field.getColumnName(), value);
        }
        return variable;
    }

    /**
     * 获取字段名和值
     * @param result 对象实体类
     * @return 返回对象的字段名和值
     */
    public Map<String, Object> getVariable(T result){
        List<EntityField> fields = new ArrayList<>(getFields().values());
        Map<String, Object> variable = new HashMap<>(fields.size());
        for (EntityField field : fields) {
            Object value = null;
            try {
                Method method  = clazz.getDeclaredMethod(field.getGetMethod(),  null);
                method.setAccessible(true);
                value = method.invoke(result,  null);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            variable.put(field.getColumnName(), value);
        }
        return variable;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public String getTableName() {
        return tableName;
    }

    void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean hasId() {
        return hasId;
    }

    void setHasId(boolean hasId) {
        this.hasId = hasId;
    }

    /**
     * 数据库字段名称为key,含主键EntityField
     * @return 字段数组
     */
    public Map<String, EntityField> getFields() {
        return fields;
    }

    /**
     * 数据库字段名称为key,含主键EntityField
     * @param fields 字段数组
     */
    void setFields(Map<String, EntityField> fields) {
        this.fields = fields;
    }

    public EntityField getIdEntity() {
        return idEntity;
    }

    void setIdEntity(EntityField idEntity) {
        this.idEntity = idEntity;
    }

    @Override
    public String toString() {
        return "EntityMate{" +
                "clazz=" + clazz +
                ", tableName='" + tableName + '\'' +
                ", hasId=" + hasId +
                ", idEntity=" + idEntity +
                ", fields=" + fields +
                '}';
    }
}


