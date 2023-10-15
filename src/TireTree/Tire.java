package TireTree;

public class Tire {
    private TireNode root;

    public Tire(){
        root = new TireNode();
    }

    /**
     * 插入字符串"word"
     * @param word
     */
    public void insert(String word){
        if(word == null){
            return;
        }
        char[] chs = word.toCharArray();
        TireNode node = root;
        node.pass++;
        int index = 0;
        // 遍历字符串
        for(int i = 0; i < chs.length; i++){
            index = chs[i] - 'a'; // 选择走向哪条路
            // 没有路，就新建路
            if(node.nexts[index] == null){
                node.nexts[index] = new TireNode();
            }
            node = node.nexts[index];
            node.pass++;
        }
        node.end++;
    }

    /**
     * 查看字符串"word"加入过几次
     * @param word
     * @return
     */
    public int search(String word){
        if(word == null){
            return 0;
        }
        char[] chs = word.toCharArray();
        TireNode node = root;
        int index = 0;
        for(int i = 0; i < chs.length; i++){
            index = chs[i] - 'a';
            // 没有路，即没加入过
            if(node.nexts[index] == null){
                return 0;
            }
            node = node.nexts[index];
        }
        return node.end;
    }

    /**
     * 查看字符串"pre"为前缀的串的数量
     * @param pre
     * @return
     */
    public int prefixNumber(String pre){
        if(pre == null){
            return 0;
        }
        char[] chs = pre.toCharArray();
        TireNode node = root;
        int index = 0;
        for(int i = 0; i < chs.length; i++){
            index = chs[i] - 'a';
            if(node.nexts[index] == null){
                return 0;
            }
            node = node.nexts[index];
        }
        return node.pass;
    }

    /**
     * 删除"word"
     * @param word
     */
    public void delete(String word){
        // 确认加入过"word"后才继续删
        if(search(word) != 0){
            char[] chs = word.toCharArray();
            TireNode node = root;
            node.pass--;
            int index = 0;
            for(int i = 0; i < chs.length; i++){
                index = chs[i] - 'a';
                //节点删完，已经没用了，将这条路删除
                if(--node.nexts[index].pass == 0){
                    node.nexts[index] = null;
                    // ...
                    return;
                }
                node = node.nexts[index];
            }
            node.end--;
        }
    }
}
