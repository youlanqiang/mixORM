package top.youlanqiang.mixorm;

/**
 * @author youlanqiang
 */
public class MixormConfig {

    private boolean debug;


    private MixormConfig(){}

    public static MixormConfig build(){
        return new MixormConfig();
    }

    /**
     * 开启debug调试模式
     * @return config
     */
    public MixormConfig onDebug(){
        this.debug = true;
        return this;
    }


    public boolean isDebug() {
        return debug;
    }


}
