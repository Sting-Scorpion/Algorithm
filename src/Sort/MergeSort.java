package Sort;

/**
 * 从小到大的归并排序
 * 时间复杂度 O(NlogN)
 * 空间复杂度 O(N)
 * 稳定
 */
public class MergeSort {
    public void mySort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public void process(int[] arr, int L, int R){
        if(L == R)
            return;
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public void merge(int[] arr, int L, int M, int R){
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        //都不越界
        while(p1 <= M && p2 <= R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //p2已经越界
        while(p1 <= M){
            help[i++] = arr[p1++];
        }
        //p1已经越界
        while(p2 <= R){
            help[i++] = arr[p2++];
        }
        for(i = 0; i < R - L + 1; i++){
            arr[L + i] = help[i];
        }
    }
}
