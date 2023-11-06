package Example;

public class BitMap {
    public static void main(String[] args) {
        int a = 0;
        int[] arr = new int[10]; // 32 bit * 10 -> 320 bit
        // arr[0] -> bit 0 ~ 31
        // ...
        // arr[9] -> bit 288 ~ 319
        int i = 178; // 假设要获取178个bit的状态

        int numIndex = i / 32; // 先计算在arr的第几个
        int bitIndex = i % 32; // 计算在第几位

        // 拿到该位的状态
        int s = (arr[numIndex] >> bitIndex) & 1;

        // 修改该位的状态成 1
        arr[numIndex] = arr[numIndex] | (1 << bitIndex);

        // 修改该位的状态成 0
        arr[numIndex] = arr[numIndex] & ~(1 << bitIndex);
    }
}
