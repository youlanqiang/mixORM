package top.youlanqiang.mixorm.mate;

import org.junit.Before;
import org.junit.Test;
import top.youlanqiang.mixorm.domain.DataEntity;
import top.youlanqiang.mixorm.entity.User;

public class EntityMateContainerTest {

    private EntityMateContainer container;

    @Before
    public void before(){
        container = EntityMateContainer.getInstance();
    }

    @Test
    public void testGetDataEntity(){
        DataEntity<User> userDataEntity = container.getDataEntity(User.class);
    }


}