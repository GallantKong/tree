package org.gallant.tree.avl;

import lombok.Data;

/**
 * @author kongyong
 * @date 2019/4/5
 */
@Data
public class AvlTree {

    public static AvlNode<Integer> insert(Integer data, AvlNode<Integer> node) {
        if (node == null) {
            return new AvlNode<Integer>(data);
        }
        int compareResult = compare(data, node.getData());
        if (compareResult > 0) {
            node.setRight(insert(data, node.getRight()));
            if (height(node.getRight()) - height(node.getLeft()) == 2 ||
                    height(node.getRight()) - height(node.getLeft()) == -2) {

            }
        } else if (compareResult < 0) {
            AvlNode<Integer> left = insert(data, node.getLeft());
        }
        return node;
    }

    public static Integer compare(Integer data1, Integer data2) {
        return data1 - data2;
    }

    public static Integer height(AvlNode<Integer> node) {
        return node == null ? -1 : node.getHeight();
    }

    public static void main(String[] args) {
        AvlNode node = AvlTree.insert(5, null);
        System.out.println(node);
    }
}
