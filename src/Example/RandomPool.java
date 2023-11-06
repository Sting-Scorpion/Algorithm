package Example;

import java.util.HashMap;
import java.util.Random;

/**
 * 设计一种结构
 * 要求有insert，delete，getRandom三个功能
 * 且时间复杂度都为O(1)
 * # Hash
 */
public class RandomPool<K> {

    private HashMap<K, Integer> keyIndexMap;
    private HashMap<Integer, K> indexKeyMap;
    private int size;

    public RandomPool(){
        this.keyIndexMap = new HashMap<>();
        this.indexKeyMap = new HashMap<>();
        size = 0;
    }

    /**
     * 将某个key不重复地加入该结构
     */
    public void insert(K key){
        if(!keyIndexMap.containsKey(key)){
            keyIndexMap.put(key, size);
            indexKeyMap.put(size++, key);
        }
    }

    /**
     * 将原本在该结构中的key删除
     */
    public void delete(K key){
        if(keyIndexMap.containsKey(key)){
            int deleteIndex = keyIndexMap.get(key);
            int lastIndex = --size;
            K lastKey = indexKeyMap.get(lastIndex);
            keyIndexMap.put(lastKey, deleteIndex);
            keyIndexMap.remove(key);
            indexKeyMap.put(deleteIndex, lastKey);
            indexKeyMap.remove(lastIndex);
        }
    }

    /**
     * @return 等概率随即返回结构中的任一key
     */
    public K getRandom(){
        if(size == 0){
            return null;
        }
        int randomIndex = (int) (Math.random() * size); // 0 ~ size - 1 取随机数
        return indexKeyMap.get(randomIndex);
    }

    public static void main(String[] args) {
        RandomPool<String> pool = new RandomPool<>();
        pool.insert("first");
        pool.insert("second");
        pool.insert("third");
        System.out.println(pool.getRandom());
        pool.insert("third");
        pool.delete("third");
        System.out.println(pool.getRandom());
    }
}
