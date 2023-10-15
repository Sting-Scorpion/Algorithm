package Sort;

/**
 * 从小到大的插入排序
 * 时间复杂度与数据状况有关
 * 最好：O(N), 最坏：O(N^2)。 一般时间复杂度按最坏情况判断
 * 时间复杂度 O(N^2)
 * 空间复杂度 O(1)
 * 稳定
 */
public class InsertionSort {
    public void mySort(int[] arr){
        //不用再排序的情况
        if(arr == null || arr.length < 2){
            return;
        }
        //在0~i范围上有序
        for(int i = 1; i < arr.length; i++){
            //0~i-1已经有序
            //i位置的数不断左移直到处在正确的位置
            for(int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--){
                swap(arr, j , j + 1);
            }
        }
    }

    public void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
