package org.gallant.tree.avl;

import lombok.Data;

/**
 * AVL 树
 * @author kongyong
 * @date 2019/4/5
 */
@Data
public class SimpleAvlTree {

    public static AvlNode<Integer> insert(Integer data, AvlNode<Integer> node) {
        if (node == null) {
            return new AvlNode<Integer>(data);
        }
        int compareResult = compare(data, node.getData());
        if (compareResult < 0) {
            node.setLeft(insert(data, node.getLeft()));
            if (height(node.getLeft()) - height(node.getRight()) == 2 ) {
                if (compare(data, node.getLeft().getData()) < 0) {
                    node = rotateWithLeftChild(node);
                } else {
                    node = doubleWithLeftChild(node);
                }
            }
        } else if (compareResult > 0) {
            node.setRight(insert(data, node.getRight()));
            if (height(node.getRight()) - height(node.getLeft()) == 2 ) {
                if (compare(data, node.getRight().getData()) > 0) {
                    node = rotateWithRightChild(node);
                } else {
                    node = doubleWithRightChild(node);
                }
            }
        }
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        return node;
    }

    /**
     * 根据左子节点右旋
     * @param k2 : 
     * @return org.gallant.tree.avl.AvlNode<java.lang.Integer> : 
     */
    public static AvlNode<Integer> rotateWithLeftChild(AvlNode<Integer> k2){
        AvlNode<Integer> k1 = k2.getLeft();
        k2.setLeft(k1.getRight());
        k1.setRight(k2);
        k2.setHeight(Math.max(height(k2.getLeft()), height(k2.getRight())) + 1);
        k1.setHeight(Math.max(height(k1.getLeft()), k2.getHeight()) + 1);
        return k1;
    }
    /**
     * 根据右子节点左旋
     * @param k2 : 
     * @return org.gallant.tree.avl.AvlNode<java.lang.Integer> : 
     */
    public static AvlNode<Integer> rotateWithRightChild(AvlNode<Integer> k2){
        AvlNode<Integer> k1 = k2.getRight();
        k2.setRight(k1.getLeft());
        k1.setLeft(k2);
        k2.setHeight(Math.max(height(k2.getRight()), height(k2.getLeft())) + 1);
        k1.setHeight(Math.max(height(k1.getRight()), k2.getHeight()) + 1);
        return k1;
    }
    /**
     * 先左旋再右旋
     * @param k3 : 
     * @return org.gallant.tree.avl.AvlNode<java.lang.Integer> : 
     */
    public static AvlNode<Integer> doubleWithLeftChild(AvlNode<Integer> k3){
        k3 = rotateWithRightChild(k3.getLeft());
        return rotateWithLeftChild(k3);
    }
    /**
     * 先右旋再左旋
     * @param k3 : 
     * @return org.gallant.tree.avl.AvlNode<java.lang.Integer> : 
     */
    public static AvlNode<Integer> doubleWithRightChild(AvlNode<Integer> k3){
        k3 = rotateWithLeftChild(k3.getRight());
        return rotateWithRightChild(k3);
    }

    /**
     * 比较两个节点的大小
     * @param data1 :
     * @param data2 :
     * @return java.lang.Integer :
     */
    public static Integer compare(Integer data1, Integer data2) {
        return data1 - data2;
    }
    /**
     * 获取节点高度，如果为空则为-1
     * @param node :
     * @return java.lang.Integer :
     */
    public static Integer height(AvlNode<Integer> node) {
        return node == null ? -1 : node.getHeight();
    }

    public static AvlNode<Integer> test(){
        AvlNode<Integer> node = SimpleAvlTree.insert(20, null);
        node = SimpleAvlTree.insert(25, node);
        node = SimpleAvlTree.insert(15, node);
        node = SimpleAvlTree.insert(18, node);
        node = SimpleAvlTree.insert(10, node);
        node = SimpleAvlTree.insert(13, node);
        return node;
    }

    public static AvlNode<Integer> test2(){
        AvlNode<Integer> node = SimpleAvlTree.insert(20, null);
        node = SimpleAvlTree.insert(25, node);
        node = SimpleAvlTree.insert(15, node);
        node = SimpleAvlTree.insert(18, node);
        node = SimpleAvlTree.insert(16, node);
        return node;
    }

    public static AvlNode<Integer> test3(){
        AvlNode<Integer> node = SimpleAvlTree.insert(20, null);
        node = SimpleAvlTree.insert(25, node);
        node = SimpleAvlTree.insert(30, node);
        return node;
    }

    public static void main(String[] args) {
        AvlNode<Integer> node = test();
        System.out.println(node);
        fillParent(node);
        print(node, 0, 25);
    }

    /**
     * 打印目标节点的路径
     * @param node : 
     * @param summary :
     * @param aim :
     */
    public static void print(AvlNode<Integer> node, Integer summary, Integer aim){
        if (node == null) {
            return;
        }
        summary += node.getData();
        if (summary.equals(aim)) {
            System.out.println(summary);
            printPath(node);
            return;
        }
        print(node.getLeft(), summary, aim);
        print(node.getRight(), summary, aim);
    }

    /**
     * 打印当前节点至root节点路径
     * @param node :
     */
    public static void printPath(AvlNode<Integer> node) {
        if (node.getParent() != null) {
            printPath(node.getParent());
        }
        System.out.print(node.getData()+",");
    }

    /**
     * 填充指向父的指针
     * @param node :
     */
    public static void fillParent(AvlNode<Integer> node) {
        if (node == null) {
            return;
        }
        if (node.getLeft() != null) {
            node.getLeft().setParent(node);
        }
        if (node.getRight() != null) {
            node.getRight().setParent(node);
        }
        fillParent(node.getLeft());
        fillParent(node.getRight());
    }
}
