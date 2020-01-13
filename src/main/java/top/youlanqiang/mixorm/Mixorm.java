package top.youlanqiang.mixorm;

import top.youlanqiang.mixorm.domain.DataEntity;

/**
 * @author youlanqiang
 */
public final class Mixorm {

    private MixormConfig mixormConfig;

    private Mixorm(){}

    public static Mixorm getInstance(){
        return InnerObject.MIXORM;
    }

    public Mixorm config(MixormConfig config){
        this.mixormConfig = config;
        return this;
    }

    public MixormConfig getConfig(){
        return this.mixormConfig;
    }


    public <T> DataEntity<T> create(Class<T> clazz){
        return new BaseDataEntity<>(clazz);
    }



    private static class InnerObject{
        private static final Mixorm MIXORM = new Mixorm();
    }


}
