package top.youlanqiang.mixorm.mate;


import java.util.Map;

public class EntityMate<T> {

    private Class<T> clazz;

    private String tableName;

    private boolean hasId;

    private EntityField idEntity;


    private Map<String, EntityField> fields;


    Class<T> getClazz() {
        return clazz;
    }

    void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    String getTableName() {
        return tableName;
    }

    void setTableName(String tableName) {
        this.tableName = tableName;
    }

    boolean isHasId() {
        return hasId;
    }

    void setHasId(boolean hasId) {
        this.hasId = hasId;
    }

    Map<String, EntityField> getFields() {
        return fields;
    }

    void setFields(Map<String, EntityField> fields) {
        this.fields = fields;
    }

    EntityField getIdEntity() {
        return idEntity;
    }

    void setIdEntity(EntityField idEntity) {
        this.idEntity = idEntity;
    }
}


