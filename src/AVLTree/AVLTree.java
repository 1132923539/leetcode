package AVLTree;


/**
 *  *二叉平衡树简单实现
 *  
 */
public class AVLTree<T extends Comparable<? super T>> {

    private static class AvlNode<T> {//avl树节点

        AvlNode(T theElement) {
            this(theElement, null, null);
        }

        AvlNode(T theElement, AvlNode<T> lt, AvlNode<T> rt) {
            element = theElement;
            left = lt;
            right = rt;
            height = 0;
        }

        T element;      // 节点中的数据
        AvlNode<T> left;         // 左儿子
        AvlNode<T> right;        // 右儿子
        int height;       // 节点的高度
    }

    private AvlNode<T> root;//avl树根
    private int sumNode;

    public AVLTree() {
        root = null;
        sumNode = 0;
    }

    //在avl树中插入数据，重复数据复略
    public void insert(T x) {
        sumNode++;
        root = insert(x, root);
    }

    //在avl中删除数据,这里并未实现
    public void remove(T x) {
        sumNode--;
        remove(root, x);
//        System.out.println("Sorry, remove unimplemented");
    }

    //在avl树中找最小的数据
    public T findMin() {
        if (isEmpty()){

            System.out.println("树空");
            return null;
        }

        return findMin(root).element;
    }

    //在avl树中找最大的数据
    public T findMax() {
        if (isEmpty())
            System.out.println("树空");
        return findMax(root).element;
    }

    //搜索
    public boolean contains(T x) {
        return contains(x, root);
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    //排序输出avl树
    public void printTree() {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
    }


    private AvlNode<T> insert(T x, AvlNode<T> t) {
        if (t == null)
            return new AvlNode<T>(x, null, null);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = insert(x, t.left);//将x插入左子树中
            if (height(t.left) - height(t.right) == 2)//打破平衡
                if (x.compareTo(t.left.element) < 0)//LL型（左左型）
                    t = rotateWithLeftChild(t);
                else   //LR型（左右型）
                    t = doubleWithLeftChild(t);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);//将x插入右子树中
            if (height(t.right) - height(t.left) == 2)//打破平衡
                if (x.compareTo(t.right.element) > 0)//RR型（右右型）
                    t = rotateWithRightChild(t);
                else                           //RL型
                    t = doubleWithRightChild(t);
        } else
            sumNode--;  // 重复数据，什么也不做
        t.height = Math.max(height(t.left), height(t.right)) + 1;//更新高度
        return t;
    }

    private AvlNode<T> remove(AvlNode<T> t, T data) {
        if (t == null)
            return t;//没有找到
        int compareResult = data.compareTo(t.element);
        if (compareResult < 0) {//往左走
            t.left = remove(t.left, data);

            //在左子树中删除后该节点失衡，若失衡，则可以肯定的是该节点的右子树比左子树高
            if (height(t.right) - height(t.left) == 2) {
                //一字型失衡，单旋转
                if (height(t.right.right) >= height(t.right.left)) {
                    t = rotateWithRightChild(t);
                } else {//之字形失衡，双旋转
                    t = doubleWithRightChild(t);
                }

            }
        } else if (compareResult > 0) {//往右走
            t.right = remove(t.right, data);
            if (height(t.left) - height(t.right) == 2) {
                if (t.left != null) {
                    if (height(t.left.left) >= height(t.left.right)) {
                        t = rotateWithLeftChild(t);
                    } else {
                        t = doubleWithLeftChild(t);
                    }
                }
            }
            //找到了要删除的节点，该节点左右子树都不为空
        } else if (t.left != null && t.right != null) {
            //特殊情况，当右子树的最小节点就是右儿子并且右儿子的右子树为空
            boolean iooo = (t.right == findMin(t.right) && t.right.right == null);//判定结果
            t.element = findMin(t.right).element;//用右子树的最小节点替换该节点
            t.right = remove(t.right, t.element);//删除右子树的最小节点
            if (iooo) {//特殊情况成立，则节点已经失衡，左单旋转
                t = rotateWithLeftChild(t);
            }
        } else {//找到了要删除的节点，该节点不是满节点
            t = t.left != null ? t.left : t.right;
        }
        if (t != null) {//更新高度
            t.height = Math.max(height(t.left), height(t.right)) + 1;
        }
        return t;
    }

    //找最小
    private AvlNode<T> findMin(AvlNode<T> t) {
        if (t == null)
            return t;
        while (t.left != null)
            t = t.left;
        return t;
    }

    //找最大
    private AvlNode<T> findMax(AvlNode<T> t) {
        if (t == null)
            return t;
        while (t.right != null)
            t = t.right;
        return t;
    }

    //搜索（查找）
    private boolean contains(T x, AvlNode t) {
        while (t != null) {
            int compareResult = x.compareTo((T) t.element);

            if (compareResult < 0)
                t = t.left;
            else if (compareResult > 0)
                t = t.right;
            else
                return true;    // Match
        }
        return false;   // No match
    }

    //中序遍历avl树
    private void printTree(AvlNode<T> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    //求高度 
    private int height(AvlNode<T> t) {
        return t == null ? -1 : t.height;
    }

    //带左子树旋转,适用于LL型
    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    //带右子树旋转，适用于RR型
    private AvlNode<T> rotateWithRightChild(AvlNode<T> k1) {
        AvlNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2;
    }

    //双旋转，适用于LR型
    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    //双旋转,适用于RL型
    private AvlNode<T> doubleWithRightChild(AvlNode<T> k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    // Test program
    public static void main(String[] args) {
        AVLTree<Integer> t = new AVLTree<Integer>();
        final int NUMS = 200;
        final int GAP = 17;
        System.out.println("Checking... (no more output means success)");
        for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
            t.insert(i);
        t.printTree();
        System.out.println(t.height(t.root));

    }

    public int getSumNode(){
        return this.sumNode;
    }
}
