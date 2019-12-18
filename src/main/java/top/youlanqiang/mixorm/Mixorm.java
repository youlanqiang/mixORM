package top.youlanqiang.mixorm;

public final class Mixorm {

    private Mixorm(){}

    private static final String VERSION = "0.0.1_alpha";

    public static <T> BaseDataEntity<T> create(Class<T> clazz){
        return new BaseDataEntity<>(clazz);
    }

    public static String getVersion(){
        return VERSION;
    }
}
