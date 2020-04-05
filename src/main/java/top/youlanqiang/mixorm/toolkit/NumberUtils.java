package top.youlanqiang.mixorm.toolkit;

/**
 * @author youlanqiang
 */
public final class NumberUtils {

    private NumberUtils(){ }

    /**
     * 数组求和
     * @param array 数组
     * @return 和
     */
    public static int arraySum(int[] array){
        int len = array.length;
        if(len == 1) {
            return array[0];
        }else {
            array[len - 2] += array[len - 1];
            int[] tempArr = new int[len - 1];
            System.arraycopy(array, 0, tempArr, 0, len - 1);
            return arraySum(tempArr);
        }
    }

}
