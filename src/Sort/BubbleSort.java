package Sort;

/**
 * 从小到大的冒泡排序
 * 时间复杂度与数据状况无关
 * 时间复杂度 O(N^2)
 * 空间复杂度 O(1)
 * 稳定
 */
public class BubbleSort {
    public void mySort(int[] arr){
        //不用再排序的情况
        if(arr == null || arr.length < 2){
            return;
        }
        //o~e上把最大的值冒到最后一位
        for(int e = arr.length - 1; e > 0; e--){
            for(int i = 0; i < e; i++){
                if(arr[i] > arr[i + 1]){
                    swap(arr, i , i + 1);
                }
            }
        }
    }

    /*
    public void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
     */

    public void swap(int[] arr, int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
        /*
        原理：
        start: x=a, y=b

         x=a^b, y=b
         x=a^b, y=(a^b)^b=a^(b^b)=a^0=a
         x=(a^b)^a=(a^a)^b=0^b=b, y=a

        final:x=b, y=a

        (*)前提：x与y在内存中是两个不同的东西
         */
    }
}
