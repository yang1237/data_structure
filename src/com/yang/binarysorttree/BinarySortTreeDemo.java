package com.yang.binarysorttree;

import java.util.Objects;

/*
 * 二叉排序树又叫二叉搜索树
 *
 * 添加方式：在某结点的基础上添加
 * 判断要添加的结点的值是否小于该节点
 * 1.如果小于，向左添加
 * 如果该结点已经有左子节点，则在左子节点的基础上向左添加
 *2.否则，向右添加
 *
 * 遍历方式：中序遍历
 * */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int j : arr) {
            binarySortTree.add(new Node(j));
        }
        binarySortTree.delete(7);
        binarySortTree.infixOrder();
        System.out.println(binarySortTree.getRoot());
    }
}

class BinarySortTree {

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void delete(int val) {
        if (root == null) {
            System.out.println("空树");
            return;
        }
        Node targetNode = search(val);
        if (targetNode == null) {
            return;
        }
        /*
            找到要删除的节点后，
            先判断二叉树是不是只有一个根节点
         */
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        /*
            二叉树存在子节点
         */
        Node parent = searchParent(val); //parent为要删除节点的父节点

        if (targetNode.left == null && targetNode.right == null) {
            /*
                要删除的节点是叶子节点（没有子节点），其parent不为空
             */
            if (parent.left != null && parent.left.val == val) {
                parent.left = null;
            } else if (parent.right != null && parent.right.val == val) {
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
            /*
                要删除的节点存在两个子节点
                操作方法：该节点是一颗子树,且该节点值比其左子树上的任一节点都大，比其
                右子树上任一节点都小，那么只需找到其右子树上的最小值，让其替换该节点的值，
                之后删除该节点右子树上值最小的节点（删除方式相当其没有子节点，或其只有一个右子节点）即可
             */
            targetNode.val = delRightTreeMin(targetNode.right);
        } else {//要删除的节点只有一个子节点
            /*
               这种情况其实和删除没有子节点时候的情况类似，都需要判断要删除的节点位于其父节点的左边还是右边，不同的是，
               删除没有子节点的时候，判断到其为父节点的那一边，就把父节点的那一边置为null
               而碰到删除的节点只有一个子节点时，还要找到待删除节点的子节点，把待删除节点（parent.left||parent.right）替换成该节点的子节点
            */
            if (parent == null) {//该节点可能为根节点，考虑其没有父节点的情况
                //此语法相当于三目运算符
                root = Objects.requireNonNullElseGet(targetNode.left, () -> targetNode.right);
            } else {
                if (parent.left != null && parent.left.val == val) {
                    parent.left = (targetNode.left != null ? targetNode.left : targetNode.right);
                } else if (parent.right != null && parent.right.val == val) {
                    parent.right = Objects.requireNonNullElseGet(targetNode.left, () -> targetNode.right);
                }
            }
        }
    }

    /**
     * 找到要删除节点右子节点树上的最小值节点，并将其从树上删除
     *
     * @param rightTree 要删除的节点的右树
     * @return 被用来替换要删除节点的最小值节点的值
     */
    private int delRightTreeMin(Node rightTree) {
        while (rightTree.left != null) {
            rightTree = rightTree.left;
        }
        delete(rightTree.val);
        return rightTree.val;
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public Node search(int val) {
        if (root != null) {
            return root.search(val);
        } else {
            System.out.println("空树");
            return null;
        }
    }

    public Node searchParent(int val) {
        if (root != null) {
            return root.searchParent(val);
        } else {
            System.out.println("空树");
            return null;
        }
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("空树");
        } else {
            root.infixOrder();
        }
    }

}

class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.val < this.val) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    /**
     * 根据值查找结点
     *
     * @param val 要找的节点的值
     * @return 值为val的节点
     */
    public Node search(int val) {
        if (val == this.val) {
            return this;
        } else if (val < this.val) {
            if (this.left == null) {
                return null;
            } else {
                return this.left.search(val);
            }
        } else {
            if (this.right == null) {
                return null;
            } else {
                return this.right.search(val);
            }
        }
    }

    public Node searchParent(int val) {
        if ((this.left != null && this.left.val == val) || (this.right != null && this.right.val == val)) {
            return this;
        } else {
            if (val < this.val && this.left != null) {
                return this.left.searchParent(val);
            } else if (val >= this.val && this.right != null) {
                return this.right.searchParent(val);
            } else {
                return null;
            }
        }
    }

    /**
     * 打印结点，先打印该节点的左子节点，然后打印该节点，最后打印该节点的右子节点
     */
    public void infixOrder() {
        if (this.left != null) {
            //打印左子节点
            this.left.infixOrder();
        }
        //打印该结点
        System.out.println(this);
        //打印右子节点
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}
