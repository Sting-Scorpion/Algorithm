package Sort;

/**
 * 堆排序
 * 大根堆，则输出为从小到大
 * 时间复杂度 O(NlogN)
 * 空间复杂度 O(1)
 * 不稳定
 */
public class HeapSort {
    public void mySort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        for(int i = 0; i < arr.length; i++){
            heapSort(arr, i);
        }
        /* 若只要求成为大根堆可用此方法，更快一点（但时间复杂度仍相同）
        for(int i = arr.length - 1; i >= 0; i--){
            heapify(arr, i, arr.length);
        }
         */

        //每次将大根堆中最大元素（根节点）移到堆后，堆的大小减一
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while(heapSize > 0){
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    /**
     * 将新加入堆的末尾的元素不断上移到正确位置
     */
    public void heapSort(int[] arr, int index){
        //和父节点比较，比父节点大就交换
        // * (-1) / 2 = 0
        while(arr[index] > arr[(index - 1) / 2]){
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 重新形成大根堆
     */
    public void heapify(int[] arr, int index, int heapsize){
        int left = index * 2 + 1; //左孩子的下标
        //判断是否还有子节点
        while(left < heapsize){
            //若有右子节点，将两个节点值较大的一方下标返回给max
            int max = left + 1 < heapsize && arr[left + 1] > arr[left] ? left + 1 : left;
            max = arr[max] > arr[index] ? max : index; //max存放子节点和父节点之中值较大的一方下标
            if(max == index) break; //父节点大，仍为大根堆，结束
            swap(arr, max, index); //不是大根堆，将值更大的子节点与父结点交换
            //交换后继续检查子树是否仍为大根堆
            index = max;
            left = index * 2 + 1;
        }
    }

    public void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
