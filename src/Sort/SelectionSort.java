package Sort;

/**
 * 从小到大的选择排序
 * 时间复杂度与数据状况无关
 * 时间复杂度 O(N^2)
 * 空间复杂度 O(1)
 * 不稳定
 */
public class SelectionSort {
    public void mySort(int[] arr){
        //不用再排序的情况
        if(arr == null || arr.length < 2){
            return;
        }
        //在i~N-1范围内选最小值
        for(int i = 0; i < arr.length; i++){
            int minIndex = i;       //假定最小值在i
            for(int j = i + 1; j < arr.length; j++){
                //若j位置比目前最小值还要小，则最小值在j
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            //交换i位置和当前剩余值中的最小值
            swap(arr, i , minIndex);
        }
    }

    public void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
