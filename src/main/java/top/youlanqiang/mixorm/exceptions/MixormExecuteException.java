package top.youlanqiang.mixorm.exceptions;

public class MixormExecuteException extends RuntimeException {

    public MixormExecuteException() {
        super("SQL执行错误,若开启事物则已经回滚.");
    }

    public MixormExecuteException(String message) {
        super(message);
    }
}
