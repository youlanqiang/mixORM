package top.youlanqiang.mixorm.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import top.youlanqiang.mixorm.Mixorm;
import top.youlanqiang.mixorm.MixormConfig;
import top.youlanqiang.mixorm.DataEntity;
import top.youlanqiang.mixorm.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;


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

    @Test
    @DisplayName("测试查询数据")
    public void select() throws SQLException{
        DataEntity<User> entity =  mixorm.create(User.class);
        User user = entity.use(connection).selectById(21);
        System.out.println(user);
    }

    @Test
    @DisplayName("测试更新数据")
    public void update() throws SQLException {
        DataEntity<User> entity =  mixorm.create(User.class);
        User user = new User();
        user.setId(21);
        user.setName("alex wang");
        int result = entity.use(connection).updateById(user);
        if(result == 1){
            System.out.println("更新成功");
        }else {
            throw new RuntimeException("更新失败.");
        }
    }

    @Test
    @DisplayName("测试删除一条数据")
    public void delete() throws SQLException {
        DataEntity<User> entity =  mixorm.create(User.class);
        Integer i = entity.use(connection).deleteById(1);
        if(i == 1){
            System.out.println("更新成功");
        }else {
            throw new RuntimeException("插入失败.");
        }
    }


    @Test
    @DisplayName("测试删除N条数据")
    public void deleteSome() throws SQLException {
        DataEntity<User> entity =  mixorm.create(User.class);
        Integer i = entity.use(connection).deleteBatchIds(Arrays.asList(2,3,4,5,6));
        if(i == 5){
            System.out.println("更新成功");
        }else {
            throw new RuntimeException("插入失败.");
        }
    }
}
