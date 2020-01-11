package top.youlanqiang.mixorm;

public final class Mixorm {

    private MixormConfig mixormConfig;

    private Mixorm(){}

    public static final String VERSION = "1.0.0_alpha";

    public static Mixorm getInstance(){
        return InnerObject.mixorm;
    }

    public Mixorm config(MixormConfig config){
        this.mixormConfig = config;
        return this;
    }

    public MixormConfig getConfig(){
        return this.mixormConfig;
    }


    public <T> BaseDataEntity<T> create(Class<T> clazz){
        return new BaseDataEntity<>(clazz);
    }



    private static class InnerObject{
        private static final Mixorm mixorm = new Mixorm();
    }


}
