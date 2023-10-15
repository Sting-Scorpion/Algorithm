package Example;

/*
一张纸反复对折
从左到右打印折痕是凹还是凸
 */
public class PaperFolding {

    public static void PrintAllFolds(int N){
        printProcess(1, N, true);
    }

    /**
     * 递归过程，来到某一节点
     * @param i 当前层数
     * @param N 一共层数
     * @param down true == 凹，false == 凸
     */
    public static void printProcess(int i, int N, boolean down){
        if(i > N){
            return;
        }
        //模拟了一棵并不存在的二叉树，进行先序遍历
        printProcess(i + 1, N, true);
        System.out.println(down ? "凹" : "凸");
        printProcess(i + 1, N, false);
    }

    public static void main(String[] args) {
        int N = 3;
        PrintAllFolds(N);
    }
}
