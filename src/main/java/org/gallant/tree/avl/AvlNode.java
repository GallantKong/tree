package org.gallant.tree.avl;

/**
 * @author kongyong
 * @date 2019/4/5
 */
public class AvlNode<T> {

    private T data;
    private Integer height;
    private AvlNode<T> left;
    private AvlNode<T> right;
    private AvlNode<T> parent;

    public AvlNode(T data) {
        this(data, null, null);
    }

    public AvlNode(T data, AvlNode left, AvlNode right) {
        this.height = 0;
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public AvlNode<T> getLeft() {
        return left;
    }

    public void setLeft(AvlNode<T> left) {
        this.left = left;
    }

    public AvlNode<T> getRight() {
        return right;
    }

    public void setRight(AvlNode<T> right) {
        this.right = right;
    }

    public AvlNode<T> getParent() {
        return parent;
    }

    public void setParent(AvlNode<T> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "AvlNode{" + "height=" + height + ", data=" + data + '}';
    }
}
