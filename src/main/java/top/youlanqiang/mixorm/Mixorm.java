package top.youlanqiang.mixorm;

public final class Mixorm {

    private Mixorm(){}

    public static <T> BaseDataEntity<T> create(Class<T> clazz){
        BaseDataEntity<T> entity = new BaseDataEntity<>(clazz);
        return entity;
    }

}
