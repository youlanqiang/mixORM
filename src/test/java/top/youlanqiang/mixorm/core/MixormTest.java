package top.youlanqiang.mixorm.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import top.youlanqiang.mixorm.Mixorm;
import top.youlanqiang.mixorm.MixormConfig;
import top.youlanqiang.mixorm.domain.DataEntity;
import top.youlanqiang.mixorm.entity.User;

/**
 * 测试Mixorm类的方法
 * @see top.youlanqiang.mixorm.Mixorm
 */
@DisplayName("Mixorm")
public class MixormTest {

    private static Mixorm mixorm;

    @BeforeAll
    public static void init(){
        mixorm = Mixorm.getInstance().config(MixormConfig.build().onDebug());
    }

    @Test
    public void test(){
         DataEntity<User> userEntity = mixorm.create(User.class);
    }


}
