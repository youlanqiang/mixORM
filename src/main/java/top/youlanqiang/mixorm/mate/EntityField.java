package top.youlanqiang.mixorm.mate;

class EntityField {

    private boolean isId;

    private String columnName;

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
}
