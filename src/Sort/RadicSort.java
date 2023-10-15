package Sort;

/**
 * 基数排序
 * 不基于比较的，使用额外容器的排序
 * 只能非负数
 */
public class RadicSort {
    public void mySort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    /**
     * @return 最大值有多少个十进制位
     */
    public int maxbits(int[] arr){
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while(max != 0){
            res++;
            max /= 10;
        }
        return res;
    }

    public void radixSort(int[] arr, int l, int r, int digit){
        final int radix = 10;
        int i = 0, j = 0;
        int[] bucket = new int[r - l + 1];
        for(int d = 1; d <= digit; d++){
            int[] count = new int[radix];
            for(i = l; i <= r; i++){
                j = getDigit(arr[i], d);
                count[j]++;
            }
            for(i = 1; i < radix; i++){
                count[i] = count[i] + count[i - 1];
            }
            for(i = r; i >= l; i--){
                j = getDigit(arr[i], d);
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }
            for(i = l, j = 0; j <= r; i++, j++){
                arr[i] = bucket[j];
            }
        }
    }

    /**
     * @return 从x中取出从右向左第d位的数字
     */
    public int getDigit(int x, int d){
        return ((x / ((int)Math.pow(10, d - 1))) % 10);
    }
}
