package top.youlanqiang.mixorm.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import top.youlanqiang.mixorm.entity.User;
import top.youlanqiang.mixorm.mate.EntityMate;
import top.youlanqiang.mixorm.mate.EntityMateContainer;


@DisplayName("EntityMateContainer")
public class EntityMateContainerTest {

    private static EntityMateContainer container;

    @BeforeAll
    public static void init() {
        container = EntityMateContainer.getInstance();
    }

    @Test
    @DisplayName("测试 MateContainer 是否可以解析对象")
    public void createMate() {
        EntityMate<User> mate = container.get(User.class);
        Assertions.assertAll(
                () -> Assertions.assertEquals(mate.getClazz(), User.class),
                () -> Assertions.assertTrue(mate.hasId()),
                () -> Assertions.assertEquals(mate.getTableName(), "user"));
    }

    @Test
    @DisplayName("测试 MateContainer 是否保存对象缓存")
    public void containerCache(){
        EntityMate<User> mate1 = container.get(User.class);
        EntityMate<User> mate2 = container.get(User.class);
        Assertions.assertEquals(mate1, mate2);
    }

}
