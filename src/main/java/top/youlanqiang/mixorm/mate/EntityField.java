package top.youlanqiang.mixorm.mate;

class EntityField {

    private String columnName;

    private Class columnType;

    private String setMethod;

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
}
