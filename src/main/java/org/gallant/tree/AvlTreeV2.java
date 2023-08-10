package org.gallant.tree;

public class AvlTreeV2 {

  public static void main(String[] args) {
    TreeNode<Integer> node = null;
    for (int i = 0; i < 63; i++) {
      node = insert(i, node);
    }
    System.out.println("end");
    TreeNode.printTree(node);
    delete(59, node);
    TreeNode.printTree(node);
  }

  public static TreeNode<Integer> delete(Integer data, TreeNode<Integer> node) {
    if (node == null) {
      return null;
    }
    if (data.compareTo(node.getData()) < 0) {
      node.setNextLeft(delete(data, node.getNextLeft()));
      if (node.getNextLeft() != null) {
        node.getNextLeft().setPre(node);
      }
      if (height(node.getNextLeft()) - height(node.getNextRight()) > 1) {
        if (data.compareTo(node.getNextLeft().getData()) < 0) {
          // 插入左子树外侧
          node = rotateRightWithLeftChild(node);
        } else {
          // 插入左子树内侧
          node = doubleRotateWithLeftChild(node);
        }
      }
    } else if (data.compareTo(node.getData()) > 0) {
      node.setNextRight(delete(data, node.getNextRight()));
      if (node.getNextRight() != null) {
        node.getNextRight().setPre(node);
      }
      if (height(node.getNextRight()) - height(node.getNextLeft()) > 1) {
        if (data.compareTo(node.getNextRight().getData()) > 0) {
          // 插入右子树外侧
          node = rotateLeftWithRightChild(node);
        } else {
          // 插入右子树内侧
          node = doubleRotateWithRightChild(node);
        }
      }
    } else {
      if (node.getNextLeft() != null && node.getNextRight() != null) {
        if (height(node.getNextLeft()) > height(node.getNextRight())) {
          TreeNode<Integer> maxNode = findMax(node.getNextLeft());
          node.setData(maxNode.getData());
          node.setNextLeft(delete(maxNode.getData(), node.getNextLeft()));
        } else {
          TreeNode<Integer> minNode = findMin(node.getNextRight());
          node.setData(minNode.getData());
          node.setNextRight(delete(minNode.getData(), node.getNextRight()));
        }
      } else {
        node = node.getNextLeft() == null ? node.getNextRight() : node.getNextLeft();
      }
    }
    if (node != null) {
      node.setHeight(Math.max(height(node.getNextLeft()), height(node.getNextRight())) + 1);
    }
    return node;
  }

  private static TreeNode<Integer> findMin(TreeNode<Integer> node) {
    if (node == null) {
      return null;
    }
    while (node.getNextLeft() != null) {
      node = node.getNextLeft();
    }
    return node;
  }

  private static TreeNode<Integer> findMax(TreeNode<Integer> node) {
    if (node == null) {
      return null;
    }
    while (node.getNextRight() != null) {
      node = node.getNextRight();
    }
    return node;
  }

  public static TreeNode<Integer> insert(Integer data, TreeNode<Integer> node) {
    if (node == null) {
      TreeNode<Integer> child = new TreeNode<>();
      child.setData(data);
      child.setHeight(1);
      return child;
    }
    if (data.compareTo(node.getData()) == 0) {
      return node;
    }
    if (data.compareTo(node.getData()) < 0) {
      node.setNextLeft(insert(data, node.getNextLeft()));
      node.getNextLeft().setPre(node);
      if (height(node.getNextLeft()) - height(node.getNextRight()) > 1) {
        if (data.compareTo(node.getNextLeft().getData()) < 0) {
          // 插入左子树外侧
          node = rotateRightWithLeftChild(node);
        } else {
          // 插入左子树内侧
          node = doubleRotateWithLeftChild(node);
        }
      }
    } else {
      node.setNextRight(insert(data, node.getNextRight()));
      node.getNextRight().setPre(node);
      if (height(node.getNextRight()) - height(node.getNextLeft()) > 1) {
        if (data.compareTo(node.getNextRight().getData()) > 0) {
          // 插入右子树外侧
          node = rotateLeftWithRightChild(node);
        } else {
          // 插入右子树内侧
          node = doubleRotateWithRightChild(node);
        }
      }
    }
    node.setHeight(Math.max(height(node.getNextLeft()), height(node.getNextRight())) + 1);
    return node;
  }

  private static int height(TreeNode<Integer> node) {
    return node == null ? 0 : node.getHeight();
  }

  public static TreeNode<Integer> rotateRightWithLeftChild(TreeNode<Integer> node) {
    TreeNode<Integer> leftChild = node.getNextLeft();
    TreeNode<Integer> leftChildRightChild = leftChild.getNextRight();
    leftChild.setNextRight(node);
    node.setNextLeft(leftChildRightChild);
    node.setHeight(Math.max(height(node.getNextLeft()), height(node.getNextRight())) + 1);

    leftChild.setPre(node.getPre());
    node.setPre(leftChild);
    if (leftChildRightChild != null) {
      leftChildRightChild.setPre(node);
    }
    return leftChild;
  }

  public static TreeNode<Integer> rotateLeftWithRightChild(TreeNode<Integer> node) {
    TreeNode<Integer> rightChild = node.getNextRight();
    TreeNode<Integer> rightChildLeftChild = rightChild.getNextLeft();
    rightChild.setNextLeft(node);
    node.setNextRight(rightChildLeftChild);
    node.setHeight(Math.max(height(node.getNextLeft()), height(node.getNextRight())) + 1);

    rightChild.setPre(node.getPre());
    node.setPre(rightChild);
    if (rightChildLeftChild != null) {
      rightChildLeftChild.setPre(node);
    }
    return rightChild;
  }

  public static TreeNode<Integer> doubleRotateWithLeftChild(TreeNode<Integer> node) {
    rotateLeftWithRightChild(node.getNextLeft());
    return rotateRightWithLeftChild(node);
  }

  public static TreeNode<Integer> doubleRotateWithRightChild(TreeNode<Integer> node) {
    rotateRightWithLeftChild(node.getNextRight());
    return rotateLeftWithRightChild(node);
  }

}
