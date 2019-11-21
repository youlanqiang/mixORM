package top.youlanqiang.mixorm.mate;


abstract class  ClassParser<T> {

    private Class<T> clazz;

    private EntityMate result;

    public ClassParser(Class<T> clazz){
        this.clazz = clazz;
    }

    private void parse(){
        if(clazz == null){
            throw new NullPointerException("Class is null!");
        }
        loadClass(clazz, result);
        loadTableName(clazz, result);
        loadHasId(clazz, result);
        loadIdClass(clazz, result);
        loadFields(clazz, result);
    }

    abstract void loadClass(Class<T> clazz, EntityMate mate);

    abstract void loadTableName(Class<T> clazz, EntityMate mate);

    abstract void loadHasId(Class<T> clazz, EntityMate mate);

    abstract void loadIdClass(Class<T> clazz, EntityMate mate);

    abstract void loadFields(Class<T> clazz, EntityMate mate);


    public EntityMate getEntityMate(){
        parse();
        return this.result;
    }
}
