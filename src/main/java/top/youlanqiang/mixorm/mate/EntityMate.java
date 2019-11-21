package top.youlanqiang.mixorm.mate;

import java.util.HashMap;

class EntityMate {

    private Class clazz;

    private String tableName;

    private boolean hasId;

    private Class idClazz;

    private HashMap<String, EntityField> fields;


    Class getClazz() {
        return clazz;
    }

    void setClazz(Class clazz) {
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

    Class getIdClazz() {
        return idClazz;
    }

    void setIdClazz(Class idClazz) {
        this.idClazz = idClazz;
    }

    HashMap<String, EntityField> getFields() {
        return fields;
    }

    void setFields(HashMap<String, EntityField> fields) {
        this.fields = fields;
    }
}
