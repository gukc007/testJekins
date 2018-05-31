package com.test.algorithm;

import java.util.*;

import static org.fusesource.jansi.Ansi.ansi;


/**
 * Created by admin on 2018/1/10.
 */
public class RBTreeAlgorithm {

    private static final String RED = "RED";
    private static final String BLACK = "BLACK";

    private class RBTree {

        RBTree leftChild;
        RBTree rightChild;
        RBTree parent;
        int value;
        String color;

        public RBTree() {
        }

        public RBTree(int value, String color) {
            this.value = value;
            this.color = color;
        }

        public RBTree(RBTree parent, int value, String color) {
            this.parent = parent;
            this.value = value;
            this.color = color;
        }
    }

    public void cal() {

        RBTree rootNode = null;

        Scanner sc = new Scanner(System.in);
        Random random = new Random();
//        while (true) {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(random.nextInt(100));
        }
        for (Integer value : list) {
            if (rootNode == null) {
                rootNode = new RBTree(value, BLACK);
            } else {
                insert(rootNode, value);
                rootNode = findRootNode(rootNode);
            }
        }

        printTree(rootNode);

        while (true) {
            int value = sc.nextInt();
            if (rootNode == null) {
                rootNode = new RBTree(value, BLACK);
            } else {
                insert(rootNode, value);
                rootNode = findRootNode(rootNode);
            }
            printTree(rootNode);
        }

//        }
//        rootNode = new RBTree(153, BLACK);
//        RBTree node = new RBTree(rootNode, 13, RED);
//        rootNode.leftChild = node;
//        node = new RBTree(rootNode, 45666, BLACK);
//        rootNode.rightChild = node;
//        node = new RBTree(rootNode.leftChild, 2, RED);
//        rootNode.leftChild.rightChild = node;

}

    private RBTree findRootNode(RBTree node) {
        if (node.parent != null) {
            return findRootNode(node.parent);
        } else {
            return node;
        }
    }

    private void insert(RBTree node, int value) {

        if (value <= node.value) {
            if (node.leftChild == null) {
                node.leftChild = new RBTree(node, value, RED);
                balance(node);
            } else {
                insert(node.leftChild, value);
            }
        } else {
            if (node.rightChild == null) {
                node.rightChild = new RBTree(node, value, RED);
                balance(node);
            } else {
                insert(node.rightChild, value);
            }
        }
    }

    private void balance(RBTree node) {
        RBTree parent = node.parent;
//        RBTree grandfather = parent.parent;
        if (parent == null) {
            //根结点
            node.color = BLACK;
            return;
        }

        if (node.leftChild != null && RED.equals(node.leftChild.color) && RED.equals(node.color)
                || node.rightChild != null && RED.equals(node.rightChild.color) && RED.equals(node.color)
                || RED.equals(node.parent.color) && RED.equals(node.color)) {

        } else {
            return;
        }

        RBTree brother = parent.leftChild == node ? parent.rightChild : parent.leftChild;

        if (brother != null && brother.color.equals(node.color)) {
            //左右颜色相同
            String color = node.color;
            //交换颜色
            node.color = brother.color = parent.color;
            parent.color = color;
            balance(parent);
        } else {
            if (Objects.equals(parent.leftChild, brother)) {
                if (node.leftChild != null && RED.equals(node.leftChild.color)) {
                    //先右旋
                    node = rightRotate(node.leftChild);
                }
                //左旋转
                leftRotate(node);
            } else {
                if (node.rightChild != null && RED.equals(node.rightChild.color)) {
                    //先左旋
                    node = leftRotate(node.rightChild);
                }
                //右旋转
                rightRotate(node);
            }
            balance(node);
        }
    }

    //左旋转
    private RBTree leftRotate(RBTree node) {
        RBTree parent = node.parent;
        RBTree grandfather = parent.parent;
        String color = parent.color;

        parent.rightChild = node.leftChild;
        parent.parent = node;
        parent.color = node.color;
        if (parent.rightChild != null) {
            parent.rightChild.parent = parent;
        }

        node.leftChild = parent;
        node.parent = grandfather;
        node.color = color;

        if (grandfather != null) {
            if (parent.equals(grandfather.rightChild)) {
                grandfather.rightChild = node;
            } else {
                grandfather.leftChild = node;
            }
        }

        return node;
    }

    //右旋转
    private RBTree rightRotate(RBTree node) {
        RBTree parent = node.parent;
        RBTree grandfather = parent.parent;
        String color = parent.color;

        parent.leftChild = node.rightChild;
        parent.parent = node;
        parent.color = node.color;
        if (parent.leftChild != null) {
            parent.leftChild.parent = parent;
        }

        node.rightChild = parent;
        node.parent = grandfather;
        node.color = color;

        if (grandfather != null) {
            if (parent.equals(grandfather.rightChild)) {
                grandfather.rightChild = node;
            } else {
                grandfather.leftChild = node;
            }
        }


        return node;
    }

    private void printTree(RBTree node) {
        int w = getNodesCount(node);
        int deep = getTreeDeep(node);
        String[][][] t = new String[2 * deep][w][2];
        coordinate(node, t, 0, 0);

        List<String> begins = new ArrayList<>();
        begins.add("       ┌");
        begins.add("───┴");
        begins.add("       └");
        List<String> ends = new ArrayList<>();
        ends.add("───┘");
        ends.add("───┐");
        //填充剩余的空
        for (int i = 2; i < t.length; i = i + 2) {
            boolean put = false;
            for (int j = 0; j < t[i].length; j++) {

                if (t[i][j][0] != null) {
                    if (begins.contains(t[i][j][0])) {
                        put = true;
                    } else if (ends.contains(t[i][j][0])) {
                        put = false;
                    }
                } else if (put) {
                    t[i][j][0] = "────";
//                    t[i][j][1] = BLACK;
                }
            }
        }

        System.out.println("开始");
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                if (t[i][j][0] == null) {
                    System.out.print(String.format("%8s", ""));
                } else if (RED.equals(t[i][j][1])) {
                    System.out.print(ansi().eraseScreen().render("@|red " + String.format("%8s", t[i][j][0]) + "|@"));
//                    System.out.print(String.format("%8s", t[i][j][0]));
                } else if (BLACK.equals(t[i][j][1])) {
                    System.out.print(String.format("%8s", t[i][j][0]));
                } else {
                    System.out.print(t[i][j][0]);
                }
            }
            System.out.println();
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束");

//      ┌┴─┐

//
//··B
//┌┴─┐
//        A····D
//····┌┘
//····C
//        设定每个节点占一列，那么树的节点数就是宽度W，树的高度H = 树的深度×2。这样每个节点就有唯一的座标了。i是左起的编号，d是深度，节点的座标就是(i, d+1)，节点头顶制表符的座标就是(i,d)。
//
//        弄个数组T[H][W]，以空格填充。从左边开始历遍整个树的节点，往数组里填。如果自己是左分枝，头顶T[d][i]的制表符就是┌，右就是┐，根节点就是│。如果自己有左分枝，脚下T[d+2][i]的制表符就是┘，右就是└，都有就是┴。
//        然后历遍所有制表符行，以┌ ┴└为开状态，以┘ ┐为闭状态，填充─。
//        最后逐行输出到文本。
    }


    //坐标化
    private int coordinate(RBTree node, String[][][] t, int w, int deep) {
        if (node == null) {
            return w;
        }

        w = coordinate(node.leftChild, t, w, deep + 1);

        if (node.parent == null) {
            t[deep * 2][w][0] = "      | ";
        } else if (node.equals(node.parent.leftChild)) {
            t[deep * 2][w][0] = "       ┌";
        } else if (node.equals(node.parent.rightChild)) {
            t[deep * 2][w][0] = "───┐";
        }
//        t[deep * 2][w][1] = BLACK;
        t[deep * 2 + 1][w][0] = String.valueOf(node.value);
        t[deep * 2 + 1][w][1] = node.color;
        if (node.leftChild != null || node.rightChild != null) {
            if (node.leftChild != null && node.rightChild != null) {
                t[(deep + 1) * 2][w][0] = "───┴";

            } else if (node.leftChild != null) {
                t[(deep + 1) * 2][w][0] = "───┘";
            } else {
                t[(deep + 1) * 2][w][0] = "       └";
            }
//            t[(deep + 1) * 2][w][1] = BLACK;
        }

        w += 1;

        w = coordinate(node.rightChild, t, w, deep + 1);

        return w;
    }

    private int getNodesCount(RBTree node) {
        if (node == null) {
            return 0;
        }
        int count = 1;
        count += getNodesCount(node.leftChild);
        count += getNodesCount(node.rightChild);

        return count;
    }

    private int getTreeDeep(RBTree node) {
        if (node == null) {
            return 0;
        }
        int leftDeep = getTreeDeep(node.leftChild);
        int rightDeep = getTreeDeep(node.rightChild);
        if (leftDeep > rightDeep) {
            return leftDeep + 1;
        } else {
            return rightDeep + 1;
        }
    }
}
