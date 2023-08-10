package org.gallant.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gallant
 */
@Getter
@Setter
public class TreeNode<T> {

  private static int count = 0;

  private T data;
  private TreeNode<T> pre;
  private TreeNode<T> nextLeft;
  private TreeNode<T> nextRight;
  private Integer height;
  private boolean isFull() {
    return nextLeft != null && nextRight != null;
  }

  public static void main(String[] args) {
    int height = 6;
    TreeNode<Integer> node = buildTree(height);
    TreeNode<Integer> root = new TreeNode<>();
    root.setHeight(6);
    TreeNode<Integer> node2 = buildTree(root);
    fillTree(node2);
    printTree(node2);
  }

  public static void printTree(TreeNode<Integer> node2) {
    int[][] graph = new int[node2.getHeight() - 1][node2.getHeight() - 1];
    fillGraph(node2, graph);
    for (int[] ints : graph) {
      for (int anInt : ints) {
        System.out.print(anInt + " ");
      }
      System.out.println();
    }
    Integer maxValue = 0;
    TreeNode<Integer> next = node2.getNextRight();
    while (next != null) {
      maxValue = node2.getNextRight().getData();
      next = next.getNextRight();
    }
    String baseStep = " ".repeat(maxValue.toString().length());
    Map<Integer, StringBuilder> sbs = new HashMap<>();
    Map<Integer, AtomicInteger> indices = new HashMap<>();
    for (Integer i = 0; i < node2.getHeight(); i++) {
      sbs.put(i, new StringBuilder());
      indices.put(i, new AtomicInteger(0));
    }
    int[] data = graph[0];
    for (Integer datum : data) {
      StringBuilder sb = sbs.get(0);
      sb.append(datum);
      for (int i = 1; i < graph.length; i++) {
        int index2 = indices.get(i).get();
        int[] data2 = graph[i];
        StringBuilder sb2 = sbs.get(i);
        if (index2 < data2.length && data2[index2] - datum == 1) {
          if (sb2.length() < sb.length()) {
            int diff = sb.length() - sb2.length();
            sb2.append(" ".repeat(diff));
          }
          sb2.append(data2[index2]);
          indices.get(i).incrementAndGet();
        }
      }
      sb.append(baseStep);
    }
    for (int i = node2.getHeight() - 1; i >= 0; i--) {
      System.out.println(sbs.get(i));
    }
  }

  private static void fillGraph(TreeNode<Integer> node, int[][] graph) {
    if (node.getNextLeft() != null) {
      fillGraph(node.getNextLeft(), graph);
    }
    for (int i = 0; i < graph.length; i++) {
      int len = graph[i].length;
      if (i == node.getHeight() - 1) {
        graph[i][len - 1]=node.getData();
      } else {
        graph[i][len - 1]= - 1;
      }
    }
    if (node.getNextRight() != null) {
      fillGraph(node.getNextRight(), graph);
    }
  }

  private static void fillTree(TreeNode<Integer> root) {
    if (root.getNextLeft() != null) {
      fillTree(root.getNextLeft());
    }
    root.setData(count++);
    if (root.getNextRight() != null) {
      fillTree(root.getNextRight());
    }
  }

  private static TreeNode<Integer> buildTree(TreeNode<Integer> node) {
    if (node.getHeight() <= 1) {
      return node;
    }
    if (node.getNextLeft() == null) {
      TreeNode<Integer> child = new TreeNode<>();
      child.setHeight(node.getHeight() - 1);
      child.setPre(node);
      node.setNextLeft(child);
      buildTree(node.getNextLeft());
    }
    if (node.getNextRight() == null) {
      TreeNode<Integer> child = new TreeNode<>();
      child.setHeight(node.getHeight() - 1);
      child.setPre(node);
      node.setNextRight(child);
      buildTree(node.getNextRight());
    }
    return node;
  }

  private static TreeNode<Integer> buildTree(int height) {
    int allCount = (1 << height) - 1;
    TreeNode<Integer> root = null;
    List<TreeNode<Integer>> lastLevelNodes = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      int count = 1 << i;
      int currentHeight = height - i;
      int lastLevelNodeIndex = 0;
      List<TreeNode<Integer>> nextLevelNodes = new ArrayList<>();
      for (int i1 = 0; i1 < count; i1++) {
        TreeNode<Integer> node = new TreeNode<>();
        node.setHeight(i * 100 + i1);
        node.setHeight(currentHeight);
        nextLevelNodes.add(node);
        if (lastLevelNodes.isEmpty()) {
          node.setData(allCount / 2);
          root = node;
          continue;
        }
        TreeNode<Integer> parent = lastLevelNodes.get(lastLevelNodeIndex);
        if (parent.isFull()) {
          parent = lastLevelNodes.get(++lastLevelNodeIndex);
        }
        if (parent.getNextLeft() == null) {
          parent.setNextLeft(node);
        } else {
          parent.setNextRight(node);
        }
      }
      lastLevelNodes = nextLevelNodes;
    }
    return root;
  }

}
