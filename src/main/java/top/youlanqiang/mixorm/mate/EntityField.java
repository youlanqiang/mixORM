package top.youlanqiang.mixorm.mate;

import top.youlanqiang.mixorm.annotation.IdType;

public class EntityField {

    private boolean isId;

    private String columnName;

    private String fieldName;

    private IdType idType;

    private Class columnType;

    private String setMethod;

    private String getMethod;

    public boolean isId() {
        return isId;
    }

    public void setId(boolean id) {
        isId = id;
    }

    String getColumnName() {
        return columnName;
    }

    void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    Class getColumnType() {
        return columnType;
    }

    void setColumnType(Class columnType) {
        this.columnType = columnType;
    }

    String getSetMethod() {
        return setMethod;
    }

    void setSetMethod(String setMethod) {
        this.setMethod = setMethod;
    }

    String getGetMethod() {
        return getMethod;
    }

    void setGetMethod(String getMethod) {
        this.getMethod = getMethod;
    }

    String getFieldName() {
        return fieldName;
    }

    void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    IdType getIdType() {
        return idType;
    }

    void setIdType(IdType idType) {
        this.idType = idType;
    }
}
