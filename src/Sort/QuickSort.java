package Sort;

/**
 * 随机快排
 * 时间复杂度 O(NlogN)
 * 空间复杂度 O(logN)
 * 不稳定
 */
public class QuickSort {
    public void mySort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    public void quickSort(int[] arr, int left, int right){
        if(left < right){
            //等概率随机选位置，和最后一个位置交换
            swap(arr, left + (int)(Math.random() * (right - left + 1)), right);
            int[] p = partition(arr, left, right);
            quickSort(arr, left, p[0] - 1); //小于部分递归再排序
            quickSort(arr, p[1] + 1, right); //大于部分递归再排序

            /*
            //只以最右边一位作为划分值
            //将数组分为小于等于划分值和大于划分值的两个部分
            int p = Partition(arr, left, right);
            quickSort(arr, left, p - 1);
            quickSort(arr, p + 1, right);
             */
        }
    }

    /**
     * @return 返回两个元素的数组，分别是相等区域的左右边界
     */
    public int[] partition(int[] arr, int left, int right){
        int less = left - 1; //小于区域右边界
        int more = right; //大于区域左边界
        //left为当前数的位置(当前指针)，right为划分值的位置
        while(left < more){
            //当前数小于划分值
            //当前数和小于区域右一位交换后，小于区域扩大(++less)
            //当前指针继续右移(left++)
            if(arr[left] < arr[right]){
                swap(arr, ++less, left++);
            }
            //当前数大于划分值
            //当前数和大于区域左一位交换，大于区域扩大(--more)
            //当前指针位置不变
            else if(arr[left] > arr[right]){
                swap(arr, --more, left);
            }
            //当前值等于划分值
            //当前指针右移
            else{
                left++;
            }
        }
        swap(arr, more, right);
        return new int[] {less + 1, more };
    }

    /**
     * @return 返回值为划分值（最右元素）在交换完后的位置，其之前为小于等于区域，之后为大于区域
     */
    public int Partition(int[] arr, int left, int right){
        int x = arr[right];
        int i = left - 1;
        for(int j = left; j < right; j++){
            if(arr[j] <= x){
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, ++i, right);
        return i;
    }

    public void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
