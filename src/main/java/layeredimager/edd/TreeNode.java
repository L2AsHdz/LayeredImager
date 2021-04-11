package layeredimager.edd;

/**
 *
 * @date 6/04/2021
 * @time 01:21:33
 * @author asael
 */
public class TreeNode<T> {

    private T dato;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode(T dato) {
        this.dato = dato;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}
