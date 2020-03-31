package top.youlanqiang.mixorm.core;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import top.youlanqiang.mixorm.DataEntity;
import top.youlanqiang.mixorm.Mixorm;
import top.youlanqiang.mixorm.MixormConfig;
import top.youlanqiang.mixorm.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@DisplayName("查询测试")
public class SelectTest {


    private static Mixorm mixorm;

    private static final String url = "jdbc:mysql://localhost:3306/test?relaxAutoCommit=true&zeroDateTimeBehavior=convertToNull&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String username = "root";
    private static final String password = "root";

    private static Connection connection;

    @BeforeAll
    public static void init() throws ClassNotFoundException, SQLException {
        mixorm = Mixorm.getInstance().config(MixormConfig.builder().setDebug(true).build());
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);
    }


    @Test
    @DisplayName("根据ID查询")
    public void select1(){
        DataEntity<User> entity =  mixorm.create(User.class);
        User user = entity.use(connection).selectById(1);
        System.out.println(user);
    }



}
