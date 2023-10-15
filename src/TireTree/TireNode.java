package TireTree;

public class TireNode {
    public int pass; // 通过次数=有多少字符串是以现在的串为前缀
    public int end;
    public TireNode[] nexts;
    // HashMap<Character, TireNode> nexts; 字符种类多的时候可以换成哈希表对应
    // TreeMap<Character, TireNode> nexts; 字符种类多，但不想用散列表，可以换成有序表

    public TireNode(){
        pass = 0;
        end = 0;
        // nexts[0] == null 没有走向a的路
        // nexts[0] != null 存在走向a的路
        // ...
        // nexts[25] == null 没有走向z的路
        nexts = new TireNode[26];
    }
}
