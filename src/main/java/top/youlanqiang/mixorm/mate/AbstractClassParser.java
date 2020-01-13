package top.youlanqiang.mixorm.mate;

/**
 * Class对象解析类
 * @author youlanqiang
 */
abstract class AbstractClassParser<T> {

    private Class<T> clazz;

    private EntityMate<T> result;

    AbstractClassParser(Class<T> clazz){
        this.clazz = clazz;
        this.result = new EntityMate<>();
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

    /**
     * 将class信息添加到entity元数据中
     * @param clazz class
     * @param mate  entity元数据
     */
    abstract void loadClass(Class<T> clazz, EntityMate<T> mate);

    /**
     * 将表名信息添加到entity元数据中
     * @param clazz class
     * @param mate  entity元数据
     */
    abstract void loadTableName(Class<T> clazz, EntityMate<T> mate);

    /**
     * 是否存在主键信息添加到entity元数据中
     * @param clazz class
     * @param mate entity元数据
     */
    abstract void loadHasId(Class<T> clazz, EntityMate<T> mate);

    /**
     * 将字段信息添加到entity元数据中
     * @param clazz class
     * @param mate entity元数据
     */
    abstract void loadFields(Class<T> clazz, EntityMate<T> mate);


    public EntityMate<T> getEntityMate(){
        parse();
        return this.result;
    }
}
