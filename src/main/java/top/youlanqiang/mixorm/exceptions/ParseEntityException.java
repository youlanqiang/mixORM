package top.youlanqiang.mixorm.exceptions;

/**
 * entity对象解析错误
 * @author youlanqiang
 */
public class ParseEntityException extends RuntimeException{

    public ParseEntityException(String msg){
        super(msg);
    }

}
