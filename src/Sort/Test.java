package Sort;

import java.util.Arrays;

/**
 * 对数器
 * 用已知的算法b来测试算法a是否正确
 */
public class Test {
    public static void main(String[] args){
        //InsertionSort te = new InsertionSort();
        //BubbleSort te = new BubbleSort();
        //SelectionSort te = new SelectionSort();
        //MergeSort te = new MergeSort();
        QuickSort te = new QuickSort();
        //RadicSort te = new RadicSort(); //仅可用于非负数排序
        //HeapSort te = new HeapSort();
        int testTime = 5;
        int maxSize = 10;
        int maxValue = 10;
        boolean succeed = true;
        for(int i = 0; i < testTime; i++){
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            te.mySort(arr1);
            comparator(arr2);
            if(!isEqual(arr1, arr2)){
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice" : "Fucking fucked!");
    }

    public static int[] generateRandomArray(int maxSize, int maxValue){
        int[] arr = new int[(int)((maxSize + 1) * Math.random())];  //长度随机
        for(int i = 0; i < arr.length; i++){
            arr[i] = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr){
        if(arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            res[i] = arr[i];
        }
        return res;
    }

    public static void comparator(int[] arr){
        Arrays.sort(arr);
    }

    public static boolean isEqual(int[] arr1, int[] arr2){
        if(arr1.length != arr2.length) return false;
        for(int i = 0; i < arr1.length; i++){
            if(arr1[i] != arr2[i]) return false;
        }
        return true;
    }
}
