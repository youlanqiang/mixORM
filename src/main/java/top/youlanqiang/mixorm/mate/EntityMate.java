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

    public boolean isHasId() {
        return hasId;
    }

    void setHasId(boolean hasId) {
        this.hasId = hasId;
    }

    public Map<String, EntityField> getFields() {
        return fields;
    }

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


