package top.youlanqiang.mixorm.mate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author youlanqiang
 */
public final class EntityMateContainer {

    private static class InnerClass{
        private static final EntityMateContainer CONTAINER = new EntityMateContainer();
    }

    public static EntityMateContainer getInstance(){
        return InnerClass.CONTAINER;
    }

    private final Map<Class, EntityMate> containers;

    private final Lock lock;

    private EntityMateContainer(){
        this.containers = new HashMap<>();
        this.lock = new ReentrantLock();
    }


    public  <T> EntityMate<T> get(Class<T> clazz){
        if(containers.containsKey(clazz)){
            return  containers.get(clazz);
        }else{
            lock.lock();
            try {
                AbstractClassParser<T> parser = new TableAbstractClassParser<>(clazz);
                EntityMate<T> mate = parser.getEntityMate();
                containers.put(clazz, mate);
                return mate;
            }finally{
                lock.unlock();
            }
        }
    }

}
