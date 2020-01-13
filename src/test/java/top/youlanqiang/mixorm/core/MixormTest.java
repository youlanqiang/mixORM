package top.youlanqiang.mixorm.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import top.youlanqiang.mixorm.Mixorm;
import top.youlanqiang.mixorm.MixormConfig;


/**
 * 测试Mixorm类的方法
 * @see top.youlanqiang.mixorm.Mixorm
 */
@DisplayName("Mixorm")
public class MixormTest {

    private static Mixorm mixorm;

    private static final String url = "jdbc:mysql://localhost:3306/customer";
    private static final String name = "com.mysql.jdbc.Driver";
    private static final String username = "root";
    private static final String password = "root";

    @BeforeAll
    public static void init(){
        mixorm = Mixorm.getInstance().config(MixormConfig.build().onDebug());
    }

    @Test
    @DisplayName("测试插入一条数据")
    public void insert(){
        
    }


}
