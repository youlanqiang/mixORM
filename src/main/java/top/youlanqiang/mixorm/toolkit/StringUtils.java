package top.youlanqiang.mixorm.toolkit;

import java.util.Collection;
import java.util.Iterator;

public final class StringUtils {

    public static String foreach(String open, String close, String separator, Collection collection){
        StringBuilder str = new StringBuilder(open);
        Iterator iterator = collection.iterator();
        while(iterator.hasNext()){
            str.append(iterator.next());
            if(iterator.hasNext()){
                str.append(separator);
            }
        }
        str.append(close);
        return str.toString();
    }

    public static String foreachAddExt(String open, String close, String separator, Collection collection, String ext){
        StringBuilder str = new StringBuilder(open);
        Iterator iterator = collection.iterator();
        while(iterator.hasNext()){
            str.append(iterator.next()).append(" ").append(ext).append(" ");
            if(iterator.hasNext()){
                str.append(separator);
            }
        }
        str.append(close);
        return str.toString();
    }

    /**
     * 生成 size个数的mark字符串
     * 如 foreachByMark("(", ")", ",", 5, "?")
     * 结果: (?, ?, ?, ?, ?)
     */
    public static String foreachByMark(String open, String close, String separator, Integer size, String mark){
        StringBuilder str = new StringBuilder(open);
        for (int i = 0; i < size; i++) {
            str.append(mark);
            if(i != size - 1){
                str.append(separator);
            }
        }
        str.append(close);
        return str.toString();
    }
}
