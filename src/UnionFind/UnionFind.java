package UnionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/*
 * 并查集
 */
public class UnionFind {
    // 样本进来先包一层，叫元素
    public class Element<V>{
        public V value;

        public Element(V value){
            this.value = value;
        }
    }

    public class UnionFindSet<V>{
        public HashMap<V, Element<V>> elementMap; // 样本和对应的元素
        public HashMap<Element<V>, Element<V>> fatherMap; // 某个元素和它的父
        public HashMap<Element<V>, Integer> sizeMap; // 某个集合的代表元素和该集合大小

        public UnionFindSet(List<V> list){
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for(V value: list){
                Element<V> element = new Element<V>(value);
                elementMap.put(value, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        private Element<V> findHead(Element<V> e){
            Stack<Element<V>> path = new Stack<>();
            while(e != fatherMap.get(e)){
                path.push(e);
                e = fatherMap.get(e);
            }
            // 扁平化，防止一条链过长
            while(!path.isEmpty()){
                fatherMap.put(path.pop(), e);
            }
            return e;
        }

        public boolean isSameSet(V a, V b){
            if(elementMap.containsKey(a) && elementMap.containsKey(b)){
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }

        public void Union(V a, V b){
            if(elementMap.containsKey(a) && elementMap.containsKey(b)){
                Element<V> aF = findHead(elementMap.get(a));
                Element<V> bF = findHead(elementMap.get(b));
                // 不属于同一集合，可以合并
                if(aF != bF){
                    // 较大、较小集合的代表元素
                    Element<V> big = sizeMap.get(aF) >= sizeMap.get(bF) ? aF : bF;
                    Element<V> small = big == aF ? bF : aF;
                    fatherMap.put(small, big);
                    sizeMap.put(big, sizeMap.get(aF) + sizeMap.get(bF));
                    sizeMap.remove(small);
                }
            }
        }
    }
}
