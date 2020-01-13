package top.youlanqiang.mixorm.mate;


abstract class AbstractClassParser<T> {

    private Class<T> clazz;

    private EntityMate result;

    AbstractClassParser(Class<T> clazz){
        this.clazz = clazz;
        this.result = new EntityMate();
    }

    private void parse(){
        if(clazz == null){
            throw new NullPointerException("Class is null!");
        }
        loadFields(clazz, result);
        loadClass(clazz, result);
        loadTableName(clazz, result);
        loadHasId(clazz, result);
    }

    abstract void loadClass(Class<T> clazz, EntityMate mate);

    abstract void loadTableName(Class<T> clazz, EntityMate mate);

    abstract void loadHasId(Class<T> clazz, EntityMate mate);


    abstract void loadFields(Class<T> clazz, EntityMate mate);


    public EntityMate getEntityMate(){
        parse();
        return this.result;
    }
}
