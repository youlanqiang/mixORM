package top.youlanqiang.mixorm.exceptions;

public class SqlGeneratorException extends RuntimeException {

    public SqlGeneratorException(){
        super("SQL构建错误，暂不支持这种构建方式.");
    }

    public SqlGeneratorException(String msg){
        super(msg);
    }

}
