package com.dayi.tree;

/**
 * 线索化二叉树
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-26 8:52
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        // 测试一把中序线索二叉树，先创建节点
        Node root = new Node(1, "tom");
        Node node2 = new Node(3, "jack");
        Node node3 = new Node(6, "smith");
        Node node4 = new Node(8, "mary");
        Node node5 = new Node(10, "king");
        Node node6 = new Node(14, "dim");
        // 收到创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        // 中序线索化
        threadedBinaryTree.threadedNodes();
        // 测试中序线索化，
        Node leftNode = node5.getLeft();
        Node rightNode = node5.getRight();
        System.out.println("10号节点的前驱节点是：" + leftNode);
        System.out.println("10号节点的后继节点是：" + rightNode);
    }
}

/**
 * 定义线索化二叉树
 */
class ThreadedBinaryTree {
    /** 根节点 */
    private Node root;

    /**
     * 为了实现线索化，需要创建指向当前节点的前驱节点的指针
     * 在递归进行线索化时，pre总是保留前一个节点的引用
     */
    private Node pre;

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * 重载中序线索化二叉树
     */
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    /**
     * 中序线索化二叉树
     * @param node 先前需要线索化的节点
     */
    public void threadedNodes(Node node) {
        // 节点为空，则不能进行线索化
        if (null == node) {
            return;
        }

        //（一）先线索化左子树
        threadedNodes(node.getLeft());

        //（二）线索化当前节点（只考虑单前节点与pre前驱接待的关系）
        // 1.处理当前节点的前驱节点（我只想知道我的上一个节点是谁）
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        // 2.处理上一个节点的后继节点（我只想知道上一个节点的下一个节点是我）
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        // 3.pre指向当前节点，即每处理完一个节点后，让当前节点称为下一个节点的前驱节点
        pre = node;

        //（三）再线索化右子树
        threadedNodes(node.getRight());
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 前序遍历查找
     * @param id
     * @return
     */
    public Node preOrderSearch(Integer id) {
        if (this.root != null) {
            return this.root.preOrderSearch(id);
        }
        return null;
    }

    /**
     * 中序遍历查找
     * @param id
     * @return
     */
    public Node infixOrderSearch(Integer id) {
        if (this.root != null) {
            return this.root.infixOrderSearch(id);
        }
        return null;
    }

    /**
     * 后续遍历查找
     * @param id
     * @return
     */
    public Node postOrderSearch(Integer id) {
        if (this.root != null) {
            return this.root.postOrderSearch(id);
        }
        return null;
    }

    /**
     * 删除节点
     * @param id
     */
    public void delNode(Integer id) {
        if (this.root != null) {
            if (id.equals(this.root.getId())) {
                this.root = null;
                return;
            }
            this.root.delNode(id);
        } else {
            System.out.println("树为空，不能删除");
        }
    }

}

/**
 * 创建节点
 */
class Node {
    /** 编号 */
    private Integer id;
    /** 名字 */
    private String name;
    /** 左子树 */
    private Node left;
    /** 右子树 */
    private Node right;

    /** 左指针类型，0：指向左子树；1：指向前驱节点 */
    private Integer leftType;
    /** 右指针类型，0：指向右子树；1：指向后继节点 */
    private Integer rightType;

    public Node(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Integer getLeftType() {
        return leftType;
    }

    public void setLeftType(Integer leftType) {
        this.leftType = leftType;
    }

    public Integer getRightType() {
        return rightType;
    }

    public void setRightType(Integer rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
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
     * 后序遍历
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     * @param id
     * @return 找到就返回该Node，否则返回null
     */
    public Node preOrderSearch(Integer id) {
        // 判断当前节点是不是要查找的节点
        //（真正的比较在这里，其他的只是比较左右子树是否为空）
        if (this.id.equals(id)) {
            return this;
        }

        // 判断当前节点的左子树是否为空，如果不为空，则继续遍历
        // 如果左递归前序遍历找到节点，则返回
        Node resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }

        // 左递归查找不到节点，且右节点不为空，则继续右递归查找节点
        if (this.right != null) {
            resNode = this.right.preOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }

        // 找不到该节点，直接返回null
        return null;
    }

    /**
     * 中序遍历查找
     * @param id
     * @return 找到就返回该Node，否则返回null
     */
    public Node infixOrderSearch(Integer id) {
        // 判断当前节点的左节点是否为空，不为空则继续递归查找，找到则直接返回
        Node resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }

        // 左递归没有找到节点，继续判断当前节点是否为要查找的节点
        // 如果是，则直接返回
        //（真正的比较在这里，其他的只是比较左右子树是否为空）
        if (this.id.equals(id)) {
            return this;
        }

        // 左递归左子树和当前节点均没有找到，则继续右递归右子树
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }

        // 找不到节点，直接返回null
        return null;
    }

    /**
     * 后续遍历查找
     * @param id
     * @return
     */
    public Node postOrderSearch(Integer id) {
        // 判断当前节点的左节点是否为空，不为空则继续递归查找，找到则直接返回
        Node resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }

        // 左子树没有找到节点，则向右子树递归查找，找到则返回
        if (this.right != null) {
            resNode = this.right.postOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }

        // 如果左右子树均没有找到，则判断当前节点是不是该节点，不是则返回null
        //（真正的比较在这里，其他的只是比较左右子树是否为空）
        if (this.id.equals(id)) {
            return this;
        }

        // 找不到节点，直接返回null
        return null;
    }

    /**
     * 删除节点
     * 说明：
     *  1）如果删除的节点是叶子节点，则删除该节点
     *  2）如果删除的节点是非叶子节点，则删除该子树
     * 思路：
     *  1）因为我们的二叉树是单向的，所以我们是判断当前节点的子节点是否是需要删除节点，而不能去判断当前这个节点是不是需要删除的节点
     *  2）如果当前节点的左子节点不为空，并且左子节点就是要删除的节点，就将this.left = null;并且返回（结束递归删除）
     *  3）如果当前节点的右子节点不为空，并且右子节点就是要删除的节点，就将this.right = null;并且返回（结束递归）
     *  4）如果第2和第3步没有删除节点，那么我们就需要向左子树递归删除
     *  5）如果第4步也没有删除节点，则应当向右子树进行递归删除
     * @param id
     */
    public void delNode(Integer id) {
        // 如果当前节点的左子节点不为空，并且左子节点就是要删除的节点，就将this.left = null;并且返回
        if (this.left != null && id.equals(this.left.id)) {
            this.left = null;
            return;
        }
        // 如果当前节点的右子节点不为空，并且右子节点就是要删除的节点，就将this.right = null;并且返回
        if (this.right != null && id.equals(this.right.id)) {
            this.right = null;
            return;
        }
        // 向左子树递归删除
        if (this.left != null) {
            this.left.delNode(id);
        }
        // 向右子树递归删除
        if (this.right != null) {
            this.right.delNode(id);
        }
    }

}