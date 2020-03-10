package top.youlanqiang.mixorm;

/**
 * @author youlanqiang
 */
public class MixormConfig {

    public static class MixormConfigBuilder{

        private MixormConfig mixormConfig;

        public MixormConfigBuilder(){
            this.mixormConfig = new MixormConfig();
        }

        /**
         * 开启debug调试模式
         * @return config
         */
        public MixormConfigBuilder setDebug(boolean val){
            this.mixormConfig.debug = val;
            return this;
        }

        public MixormConfig build(){
            return this.mixormConfig;
        }

    }

    private boolean debug;

    private MixormConfig(){}

    public boolean isDebug() {
        return debug;
    }

    public static MixormConfigBuilder builder(){
        return new MixormConfigBuilder();
    }

}
