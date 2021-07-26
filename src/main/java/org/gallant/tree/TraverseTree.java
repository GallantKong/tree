package org.gallant.tree;

import org.gallant.tree.avl.AvlNode;
import org.gallant.tree.avl.SimpleAvlTree;

/**
 * @author : 会灰翔的灰机
 * @date : 2021/7/25
 */
public class TraverseTree {

  /**
   * 案例数据：
   *            15
   *        /        \
   *      10          20
   *     /    \      /    \
   *    8     13    18     25
   *   /  \
   * 7     9
   * @param args :
   */
  public static void main(String[] args) {
    AvlNode<Integer> node = SimpleAvlTree.insert(20, null);
    node = SimpleAvlTree.insert(25, node);
    node = SimpleAvlTree.insert(15, node);
    node = SimpleAvlTree.insert(18, node);
    node = SimpleAvlTree.insert(10, node);
    node = SimpleAvlTree.insert(13, node);
    node = SimpleAvlTree.insert(8, node);
    node = SimpleAvlTree.insert(7, node);
    node = SimpleAvlTree.insert(9, node);
    preorderTraversal(node, null);
    System.out.println("------------------------");
    inorderTraversal(node, null);
    System.out.println("------------------------");
    postorderTraversal(node, null);
  }

  /**
   * 前序遍历（DLR）D=Degree L=left R=right
   * 或 NLR N=Node L=left R=right
   * @param root : 根节点
   */
  private static void preorderTraversal(AvlNode<Integer> root, Boolean isLeft) {
    if (root == null) {
      return;
    }
    System.out.println((isLeft != null ? (isLeft ? "left:" : "right:") : "root:") + root);
    preorderTraversal(root.getLeft(), true);
    preorderTraversal(root.getRight(), false);
  }

  /**
   * 中序遍历（LDR）D=Degree L=left R=right
   * 或 LNR N=Node L=left R=right
   * @param root : 根节点
   */
  private static void inorderTraversal(AvlNode<Integer> root, Boolean isLeft) {
    if (root == null) {
      return;
    }
    inorderTraversal(root.getLeft(), true);
    System.out.println((isLeft != null ? (isLeft ? "left:" : "right:") : "root:") + root);
    inorderTraversal(root.getRight(), false);
  }

  /**
   * 后序遍历（LRD）D=Degree L=left R=right
   * 或 LRN N=Node L=left R=right
   * @param root : 根节点
   */
  private static void postorderTraversal(AvlNode<Integer> root, Boolean isLeft) {
    if (root == null) {
      return;
    }
    postorderTraversal(root.getLeft(), true);
    postorderTraversal(root.getRight(), false);
    System.out.println((isLeft != null ? (isLeft ? "left:" : "right:") : "root:") + root);
  }
}
