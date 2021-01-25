package com.dayi.tree;

/**
 * 二叉树demo
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-21 14:20
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 先创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 创建节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        // 手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);
        binaryTree.setRoot(root);
        /*// 1.测试前序，中序和后序
        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();*/
        /*// 2.前序遍历查找节点测试
        System.out.println("前序遍历查找");
        HeroNode heroNode = binaryTree.preOrderSearch(6);
        if (null != heroNode) {
            System.out.println(heroNode);
        } else {
            System.out.println("找不到该节点");
        }*/
        // 3.删除节点测试
        System.out.println("删除节点前，前序遍历");
        binaryTree.preOrder();
        binaryTree.delNode(3);
        System.out.println("删除节点后，前序遍历");
        binaryTree.preOrder();
    }
}

/**
 * 定义二叉树
 */
class BinaryTree {
    /** 根节点 */
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
    public HeroNode preOrderSearch(Integer id) {
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
    public HeroNode infixOrderSearch(Integer id) {
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
    public HeroNode postOrderSearch(Integer id) {
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
class HeroNode {
    /** 编号 */
    private Integer id;
    /** 名字 */
    private String name;
    /** 左子树 */
    private HeroNode left;
    /** 右子树 */
    private HeroNode right;

    public HeroNode(Integer id, String name) {
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
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
     * @return 找到就返回该HeroNode，否则返回null
     */
    public HeroNode preOrderSearch(Integer id) {
        // 判断当前节点是不是要查找的节点
        //（真正的比较在这里，其他的只是比较左右子树是否为空）
        if (this.id.equals(id)) {
            return this;
        }

        // 判断当前节点的左子树是否为空，如果不为空，则继续遍历
        // 如果左递归前序遍历找到节点，则返回
        HeroNode resNode = null;
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
     * @return 找到就返回该HeroNode，否则返回null
     */
    public HeroNode infixOrderSearch(Integer id) {
        // 判断当前节点的左节点是否为空，不为空则继续递归查找，找到则直接返回
        HeroNode resNode = null;
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
    public HeroNode postOrderSearch(Integer id) {
        // 判断当前节点的左节点是否为空，不为空则继续递归查找，找到则直接返回
        HeroNode resNode = null;
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
