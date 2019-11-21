package top.youlanqiang.mixorm.mate;

import top.youlanqiang.mixorm.domain.DataEntity;

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


    private EntityMateContainer(){}

    private void put(Class clazz){

    }

    public <T> DataEntity<T> getDataEntity(Class<T> clazz){
        put(clazz);
        return null;
    }




}
