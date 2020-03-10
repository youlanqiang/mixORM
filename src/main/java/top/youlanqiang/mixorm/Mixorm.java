package top.youlanqiang.mixorm;

import top.youlanqiang.mixorm.domain.DataEntity;

/**
 * @author youlanqiang
 */
public final class Mixorm {

    private MixormConfig mixormConfig;

    /**
     * 默认初始的配置文件
     */
    private static final MixormConfig DEFAULT_CONFIG = MixormConfig.builder().setDebug(false).build();

    private Mixorm(){
        this.mixormConfig = DEFAULT_CONFIG;
    }

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
