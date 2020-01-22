package top.youlanqiang.mixorm.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import top.youlanqiang.mixorm.Mixorm;
import top.youlanqiang.mixorm.MixormConfig;
import top.youlanqiang.mixorm.domain.DataEntity;
import top.youlanqiang.mixorm.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * 测试Mixorm类的方法
 * @see top.youlanqiang.mixorm.Mixorm
 */
@DisplayName("Mixorm")
public class MixormTest {

    private static Mixorm mixorm;

    private static final String url = "jdbc:mysql://192.168.81.134:3306/test";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String username = "root";
    private static final String password = "root";

    private static Connection connection;

    @BeforeAll
    public static void init() throws ClassNotFoundException, SQLException {
        mixorm = Mixorm.getInstance().config(MixormConfig.build().onDebug());
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);
    }

    @Test
    @DisplayName("测试插入一条数据")
    public void insert() throws SQLException {
        DataEntity<User> entity =  mixorm.create(User.class);
        User user = new User();
        user.setAge(10);
        user.setEmail("youlanqiang@hotmail.com");
        user.setName("youlanqiang");
        Integer i = entity.use(connection).insert(user);
        if(i == 1){
            System.out.println("主键:"+user.getId());;
        }else {
            throw new RuntimeException("插入失败.");
        }
    }


}
