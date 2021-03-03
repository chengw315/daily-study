package lru;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description midpoint LRU模拟
 *
 * |||||||||||||||||||||||||||||||||||||||||||||||||||||||
 * |     new区                           |     old区     |
 * ||||||||||||||||||||||||||||||||||||||||||||||||||||||
 *                                      |
 *                           new区和old区的分界点即是midpoint
 *
 * 新载入的页不会放在LRU链表头，而是放在midpoint处，这样可以避免不频繁的大范围数据块访问导致原缓冲区被置换
 * 每次被放入midpoint都会使notNewTime（未被放入new区的次数）+1，达到阈值后不会再放入midpoint，而是放入链表头（new区第一位）
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/10/10
 **/
public class LRUTest {

    class Page{

    }

    class Node<T> {
        T val;
        int indexOfMemory;
        Node prev;
        Node next;
        /**
         * 未被放入new区的次数
         */
        int notNewTime;
    }
    class List<T> {
        Node<T> head;
        Node<T> tail;
        Node<T> midPoint;
    }

    /**
     * 缓冲用内存（100页）
     */
    public Page[] memory = new Page[100];
    /**
     * 磁盘（1000块，此处为了方便，令块=页）
     */
    public Page[] disk = new Page[100];
    /**
     * LRU链表（已使用页）
     */
    public List<Page> LRUList = new List<>();
    /**
     * 空闲页链表
     */
    public Queue<Integer> freeList = new LinkedList<>();
    /**
     * 页表
     */
    public ConcurrentHashMap<Integer,Node<Page>> pageTable = new ConcurrentHashMap<>();

    /**
     * 访问指定块
     * @param index
     */
    public Page visit(int index) {
        //先查页表 todo(处理 “if读then写” 并发问题)
        if(pageTable.containsValue(index)) {
            //页表有数据，更新LRU链表
            Node node = pageTable.get(index);
            updateLRU(node);
            return (Page) node.val;
        }

        //页表无数据，将磁盘块载入内存
        return loadFromDisk(index);
    }

    /**
     * 更新LRU链表
     * @param node
     */
    private void updateLRU(Node node) {
        //notNewTime已达阈值，插入new区（head）todo()
        //未达阈值，插入midpoint（old区）todo()
    }

    /**
     * 从磁盘载入内存
     * @param index
     * @return 载入内存的页
     */
    private Page loadFromDisk(int index) {
        Page data = disk[index];
        int free;
        //先看是否有空闲页 todo(处理 “if读then写” 并发问题)
        if(!freeList.isEmpty()) {
            //有空闲页，空闲页载入数据
            free = freeList.poll();
        } else {
            //无空闲页，LRU链表释放尾部节点
            free = LRUList.tail.indexOfMemory;
            Node prev = LRUList.tail.prev;
            prev.next = null;
            LRUList.tail = prev;
        }
        //新页插入midPoint
        memory[free] = data;
        Node<Page> node = new Node<>();
        node.val = data;
        node.indexOfMemory = free;
        node.notNewTime = 0;
        Node<Page> midPoint = LRUList.midPoint;
        node.next = midPoint;
        node.prev = midPoint.prev;
        midPoint.prev = node;
        //更新页表
        pageTable.put(index,node);
        return data;
    }

    public static void main(String[] args) {

    }
}
