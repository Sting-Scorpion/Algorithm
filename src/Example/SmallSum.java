package Example;

/*
小和问题：
在一个数组中，每一个数左边比当前数小的数累加起来的和叫做小和
eg. [1,3,4,2,5] 小和为1 + 1+3 + 1 + 1+3+4+2=16
 */
public class SmallSum {
    /**
     * 由归排改进而来
     * @param arr
     * @return
     */
    public static int smallSum(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        return processer(arr, 0, arr.length - 1);
    }

    /**
     * 排序并求和
     */
    public static int processer(int[] arr, int l, int r){
        if(l == r)
            return 0;
        int mid = (l + r) / 2;
        return processer(arr, l, mid) + processer(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    /**
     *
     * @param arr 输入数组
     * @param l 最左
     * @param m 中间
     * @param r 最右
     * @return 返回归并两数组间的小和
     */
    public static int merge(int[] arr, int l, int m, int r){
        int[] tmp = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        int result = 0;
        while(p1 <= m && p2 <= r){
            //若左数比右数小，且右数组已经有序，结果需要加上对应数量的左数
            result += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            //归并排序，当左数=右数时需要将右数优先加入
            //先加入右数才能保证右数组的长度
            tmp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1 <= m){
            tmp[i++] = arr[p1++];
        }
        while(p2 <= r){
            tmp[i++] = arr[p2++];
        }
        return result;
    }

    public static void main(String[] args){
        int[] test = {1, 3, 4, 2, 5};
        int re = smallSum(test);
        System.out.println(re);
    }
}
