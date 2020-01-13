package top.youlanqiang.mixorm.mate;

import top.youlanqiang.mixorm.annotation.IdType;

/**
 * @author youlanqiang
 */
public class EntityField {

    private boolean isId;

    private String columnName;

    private String fieldName;

    private IdType idType;

    private Class<?> columnType;

    private String setMethod;

    private String getMethod;

    public boolean isId() {
        return isId;
    }

    void setId(boolean id) {
        isId = id;
    }

    public String getColumnName() {
        return columnName;
    }

    void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Class<?> getColumnType() {
        return columnType;
    }

    void setColumnType(Class<?> columnType) {
        this.columnType = columnType;
    }

    public String getSetMethod() {
        return setMethod;
    }

    void setSetMethod(String setMethod) {
        this.setMethod = setMethod;
    }

    public String getGetMethod() {
        return getMethod;
    }

    void setGetMethod(String getMethod) {
        this.getMethod = getMethod;
    }

    public String getFieldName() {
        return fieldName;
    }

    void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public IdType getIdType() {
        return idType;
    }

    void setIdType(IdType idType) {
        this.idType = idType;
    }
}
