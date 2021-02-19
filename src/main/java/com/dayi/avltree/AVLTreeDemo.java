package com.dayi.avltree;

/**
 * 平衡二叉树（平衡二叉排序树/平衡二叉搜索树）
 *  平衡二叉树是对二叉排序树/二叉搜索树的一种优化
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-02-19 15:48
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        // int[] arr = {4, 3, 6, 5, 7, 8};
        int[] arr = {10, 12, 8, 9, 7, 6};
        AVLTree avlTree = new AVLTree();
        // 循环添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        // 中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树");
        avlTree.infixOrder();

        // 树的高度测试
        System.out.println("平衡处理后...");
        System.out.println("树的高度=" + avlTree.getRoot().height());
        System.out.println("树的左子树高度=" + avlTree.getRoot().left.height());
        System.out.println("树的右子树高度=" + avlTree.getRoot().right.height());

    }
}

/**
 * 创建平衡二叉树
 */
class AVLTree {
    /** 根节点 */
    private Node root;

    public Node getRoot() {
        return root;
    }

    /**
     * 添加节点
     * @param node
     */
    public void add(Node node) {
        if (null == root) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (null != root) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }

    /**
     * 查找要删除的节点
     * @param value
     * @return
     */
    public Node search(int value) {
        if (null == root) {
            return null;
        }
        return root.search(value);
    }

    /**
     * 查找父节点
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        if (null == root) {
            return null;
        }
        return root.searchParent(value);
    }

    /**
     * 返回以node为根节点的二叉排序树中的最小节点的值
     * 1.删除以node为根节点的二叉排序树中的最小节点（肯定是叶子节点）
     * 2.返回该最小节点的值
     * @param node 传入的node（当做二叉排序的根节点来查找）
     * @return
     */
    public int delRightTreeMin(Node node) {
        Node tempNode = node;
        while (tempNode.left != null) {
            tempNode = node.left;
        }
        // 1.删除节点，与删除叶子节点的思路一致
        // 找到要删除节点的父节点
        Node parent = searchParent(tempNode.value);
        // 要删除的targetNode节点是父节点的左子节点，还是右子节点
        if (parent.left != null && parent.left.value == tempNode.value) {
            // 是左子节点
            parent.left = null;
        } else if (parent.right != null && parent.right.value == tempNode.value) {
            // 是右子节点
            parent.right = null;
        }
        // 2.返回该最小节点的值
        return tempNode.value;
    }

    /**
     * 返回以node为根节点的二叉排序树中的最大节点的值
     * 1.删除以node为根节点的二叉排序树中的最大节点（肯定是叶子节点）
     * 2.返回该最大节点的值
     * @param node 传入的node（当做二叉排序的根节点来查找）
     * @return
     */
    public int delLeftTreeMax(Node node) {
        Node tempNode = node;
        while (tempNode.right != null) {
            tempNode = node.right;
        }
        // 找到要删除节点的父节点
        Node parent = searchParent(tempNode.value);
        // 要删除的targetNode节点是父节点的右子节点，因为只有一直往右边找，才能找到左子树中最大的值
        if (parent.left != null && parent.left.value == tempNode.value) {
            // 是左子节点
            parent.left = null;
        } else if (parent.right != null && parent.right.value == tempNode.value) {
            // 是右子节点
            parent.right = null;
        }
        return tempNode.value;
    }

    /**
     * 删除节点
     * @param value
     */
    public void delNode(int value) {
        if (null == root) {
            return;
        }

        // 1.找到要删除的节点
        Node targetNode = search(value);
        // 找不到要删除的目标节点，则返回
        if (null == targetNode) {
            return;
        }
        // 如果找到目标节点，且当前该二叉排序就只有一个节点（即说明要删除的目标节点就是根节点），则置空根节点即可
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }

        // 2.找到要删除节点的父节点
        Node parent = searchParent(value);

        // 情况一：要删除的节点是叶子节点（前面已过滤只有一个节点的情况）
        if (targetNode.left == null && targetNode.right == null) {
            // 要删除的targetNode节点是父节点的左子节点，还是右子节点
            if (parent.left != null && parent.left.value == targetNode.value) {
                // 是左子节点
                parent.left = null;
            } else if (parent.right != null && parent.right.value == targetNode.value) {
                // 是右子节点
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
            // 情况二：删除有两颗子树的节点（包含要删除根节点），有两种实现方式
            /*// 方式一，是找targetNode的右子树中最小的节点，删除该最小节点，并把其值替换上来
            int minVal = delRightTreeMin(targetNode.right);
            targetNode.value = minVal;*/
            // 方式二，是找targetNode的左子树中的最大的节点，删除该最大节点，并把其值替换上来
            int maxVal = delLeftTreeMax(targetNode.left);
            targetNode.value = maxVal;
        } else {
            // 情况三：删除只有一颗子树的节点(在删除只有一颗子树的时候，需要考虑父节点是否为空的情况)
            if (targetNode.left != null) {
                if (parent != null) {
                    // 如果targetNode有左子节点，且targetNode是parent的左子节点
                    if (parent.left.value == targetNode.value) {
                        parent.left = targetNode.left;
                    } else {
                        // 如果targetNode有左子节点，且targetNode是parent的右子节点
                        parent.right = targetNode.left;
                    }
                } else {
                    root = targetNode.left;
                }
            } else {
                if (parent != null) {
                    // 如果targetNode有右子节点，且targetNode是parent的左子节点
                    if (parent.left.value == targetNode.value) {
                        parent.left = targetNode.right;
                    } else {
                        // 如果targetNode有右子节点，且targetNode是parent的右子节点
                        parent.right = targetNode.right;
                    }
                } else {
                    root = targetNode.right;
                }
            }
        }
    }
}

/**
 * 创建节点
 */
class Node {
    /** 节点的值 */
    int value;
    /** 左子节点 */
    Node left;
    /** 右子节点 */
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 返回左子树的高度
     * @return
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * 返回右子树的高度
     * @return
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * 返回当前节点的高度（以该节点为根节点的树的高度）
     * @return
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 左旋转(右子树的高度高，需要左旋减低右子树的高度)
     * 右子树的高度 - 左子树的高度 > 1
     */
    private void leftRotate() {
        // 1.以当前节点为根节点的值，创建新的节点
        Node newNode = new Node(this.value);
        // 2.当前节点的左子树作为新节点的左子树
        newNode.left = this.left;
        // 3.当前节点右子树的左节点，作为新节点的右节点
        newNode.right = this.right.left;
        // 4.当前节点的右节点的值，作为当前节点的值
        this.value = this.right.value;
        // 5.当前节点的左节点，指向新节点
        this.left = newNode;
        // 6.当前节点的右节点，指向当前节点的右节点的右节点
        this.right = this.right.right;
    }

    /**
     * 右旋转（左子树的高度高，需要右旋减低左子树的高度）
     * 左子树的高度 - 右子树的高度 > 1
     */
    public void RightRotate() {
        // 1.以当前节点为根节点的值，创建新的节点
        Node newNode = new Node(this.value);
        // 2.当前节点的右子树，作为新节点的右子树
        newNode.right = this.right;
        // 3.当前节点的左子树的右节点，作为新节点的左子树
        newNode.left = this.left.right;
        // 4.当前节点的左节点的值，作为当前节点的值
        this.value = this.left.value;
        // 5.当前节点的左节点，指向当前节点的左子节点的左子节点
        this.left = this.left.left;
        // 6.当前节点右节点，指向新节点
        this.right = newNode;
    }

    /**
     * 添加节点（使用递归的方式添加节点，注意需要满足二叉排序树的要求）
     * @param node 被添加的节点
     */
    public void add(Node node) {
        if (null == node) {
            return;
        }

        // 判断传入的节点值，和当前节点值的关系
        if (node.value < this.value) {
            if (null == this.left) {
                // 如果当前节点的左子树为空，则直接加入
                this.left = node;
            } else {
                // 否则，则递归向左子树添加
                this.left.add(node);
            }
        } else {
            // 传入的节点不小于当前节点的值
            if (null == this.right) {
                // 如果当前节点的右子树为空，则直接加入
                this.right = node;
            } else {
                // 否则，则递归向右子树添加
                this.right.add(node);
            }
        }

        // 当添加完节点后，如果（右子树的高度 - 左子树的高度）> 1时，则左旋转
        if (rightHeight() - leftHeight() > 1) {
            leftRotate();
        }

        // 当添加完节点后，如果（左子树的高度 - 右子树的高度）> 1时，则右旋转
        if (leftHeight() - rightHeight() > 1) {
            RightRotate();
        }
    }

    /**
     * 前序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 查找要删除的节点
     * @param value 准备删除节点的值
     * @return
     */
    public Node search(int value) {
        if (this.value == value) {
            // 当前节点就是要删除的节点
            return this;
        } else if (value < this.value) {
            // 比当前节点小，则从左子树找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            // 不小于当前节点，则从右子树找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     * @param value 准备删除节点的值
     * @return
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            // 如果左子树不为空且为要删除的节点，或者右子树不为空且为要删除的节点，就返回该节点
            return this;
        } else if (value < this.value && this.left != null) {
            // 如果查找的值小于当前节点的值，且左子树不为空，则从左子树递归查找
            return this.left.searchParent(value);
        } else if (value >= this.value && this.right != null) {
            // 如果查找的值不小于当前节点的值，且右子树不为空，则从右子树递归查找
            return this.right.searchParent(value);
        } else {
            // 没有找到要查找值的父节点，则返回null
            return null;
        }
    }

}