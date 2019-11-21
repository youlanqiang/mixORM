package top.youlanqiang.mixorm.mate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class EntityMateContainer {

    private static class V{
        private static final EntityMateContainer container = new EntityMateContainer();

        private static EntityMateContainer getInstance(){
            return container;
        }
    }

    public static EntityMateContainer getInstance(){
        return V.getInstance();
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
            try {
                lock.lock();
                ClassParser<T> parser = new TableClassParser<>(clazz);
                EntityMate<T> mate = parser.getEntityMate();
                containers.put(clazz, mate);
                return mate;
            }finally{
                lock.unlock();
            }
        }
    }

}
