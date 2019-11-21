package top.youlanqiang.mixorm.mate;

public class TableClassParser<T> extends ClassParser<T> {

    public TableClassParser(Class<T> clazz){
        super(clazz);
    }

    @Override
    void loadClass(Class<T> clazz, EntityMate mate) {

    }

    @Override
    void loadTableName(Class<T> clazz, EntityMate mate) {

    }

    @Override
    void loadHasId(Class<T> clazz, EntityMate mate) {

    }

    @Override
    void loadIdClass(Class<T> clazz, EntityMate mate) {

    }

    @Override
    void loadFields(Class<T> clazz, EntityMate mate) {

    }
}
