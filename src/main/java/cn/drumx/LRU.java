package cn.drumx;


import java.util.HashMap;

/**
 * @author Mark Zheng drumx.cn @鸡腿毁灭者
 * @description
 * @create 2025-04-07 11:28
 */
public class LRU {
    public static void main(String[] args) {
    }

    class LRUCache {
        private int capacity;
        private DoubleList list;
        public LRUCache(int capacity) {
            list = new DoubleList(capacity);
        }

        public int get(int key) {
            return list.getVal(key);
        }

        public void put(int key, int value) {
            list.put(key, value);
        }
    }

    class Node{
        public Node prev;
        public Node next;
        public int val;
        public int key;

        public Node(int key, int val){
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }

    class DoubleList{
        private Node head, tail;
        private HashMap<Integer, Node> cache = new HashMap<Integer, Node>();
        private int capacity;
        private int size;

        public DoubleList(int capacity){
            this.capacity = capacity;
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        // 在链表尾部添加节点 x，时间 O(1)
        public void addLast(Node x) {
            x.prev = tail.prev;
            x.next = tail;
            tail.prev.next = x;
            tail.prev = x;
            size++;
            cache.put(x.key, x);
        }

        // 删除链表中的 x 节点（x 一定存在）
        // 由于是双链表且给的是目标 Node 节点，时间 O(1)
        public void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
            cache.remove(x.key);
        }

        // 删除链表中第一个节点，并返回该节点，时间 O(1)
        public Node removeFirst() {
            if (head.next == tail)
                return null;
            Node first = head.next;
            remove(first);
            cache.remove(first.key);
            return first;
        }

        public void makeRecently(int key){
            if (!cache.containsKey(key)){
                return;
            } else{
                Node current = cache.get(key);
                remove(current);
                addLast(current);
            }
        }

        public void put(int key, int val){
            Node x = new Node(key, val);
            if (cache.containsKey(key)){
                x = cache.get(key);
                x.val = val;
                makeRecently(x.key);
                return;
            }
            if (size >= capacity){
                removeFirst();
            }
            addLast(x);
        }

        public int getVal(int key){
            if (!cache.containsKey(key)){
                return -1;
            } else{
                makeRecently(key);
                return cache.get(key).val;
            }
        }

    }
}
