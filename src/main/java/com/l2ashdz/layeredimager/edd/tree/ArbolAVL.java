package com.l2ashdz.layeredimager.edd.tree;

import com.l2ashdz.layeredimager.edd.TreeNode;
import com.l2ashdz.layeredimager.model.Objeto;

/**
 *
 * @date 6/04/2021
 * @time 13:40:48
 * @author asael
 */
public class ArbolAVL {

    private TreeNode<Objeto> root;

    /*private int getFactorEquilibrio(Nodo<Objeto> root) {
        
        
    }*/
    private TreeNode<Objeto> rightRotation(TreeNode<Objeto> oldRoot) {
        TreeNode<Objeto> newRoot = oldRoot.getLeft();
        oldRoot.setLeft(newRoot.getRight());
        newRoot.setRight(oldRoot);

        return newRoot;
    }

    private TreeNode<Objeto> leftRotation(TreeNode<Objeto> oldRoot) {
        TreeNode<Objeto> newRoot = oldRoot.getRight();
        oldRoot.setRight(newRoot.getLeft());
        newRoot.setLeft(oldRoot);

        return newRoot;
    }

    private TreeNode<Objeto> rightDoubleRotation(TreeNode<Objeto> oldRoot) {
        TreeNode<Objeto> newRoot;
        oldRoot.setLeft(leftRotation(oldRoot.getLeft()));
        newRoot = rightRotation(oldRoot);

        return newRoot;

    }

    private TreeNode<Objeto> leftDoubleRotation(TreeNode<Objeto> oldRoot) {
        TreeNode<Objeto> newRoot;
        oldRoot.setRight(rightRotation(oldRoot.getRight()));
        newRoot = leftRotation(oldRoot);

        return newRoot;
    }

    public void inOrden(TreeNode<Objeto> root) {
        if (root != null) {
            inOrden(root.getLeft());
            System.out.print(root.getDato().getId() + ", ");
            inOrden(root.getRight());
        }
    }

    public void preOrden(TreeNode<Objeto> root) {
        if (root != null) {
            System.out.print(root.getDato().getId() + ", ");
            preOrden(root.getLeft());
            preOrden(root.getRight());
        }
    }

    public void postOrden(TreeNode<Objeto> root) {
        if (root != null) {
            preOrden(root.getLeft());
            preOrden(root.getRight());
            System.out.print(root.getDato().getId() + ", ");
        }
    }

    public TreeNode<Objeto> getRaiz() {
        return root;
    }

    public int getBalanceFactor(TreeNode<Objeto> root) {
        if (root.getRight() == null & root.getLeft() != null) {
            return -getHeight(root.getLeft());
        } else if (root.getRight() != null & root.getLeft() == null) {
            return getHeight(root.getRight());
        } else if (root.getRight() != null & root.getLeft() != null) {
            return getHeight(root.getRight()) - getHeight(root.getLeft());
        } else {
            return 0;
        }
    }

    private int getHeight(TreeNode<Objeto> root) {
        int Altder = ((root.getRight() == null) ? 0 : 1 + getHeight(root.getRight()));
        int Altizq = ((root.getLeft() == null) ? 0 : 1 + getHeight(root.getLeft()));
        return Math.max(Altder, Altizq);
    }

    private TreeNode<Objeto> insert(TreeNode<Objeto> nuevo, TreeNode<Objeto> root) {
        TreeNode<Objeto> newRoot = root;

        if (nuevo.getDato().getId() < root.getDato().getId()) {
            if (root.getLeft() == null) {
                root.setLeft(nuevo);
            } else {
                root.setLeft(insert(nuevo, root.getLeft()));

                if (getBalanceFactor(root) == -2) {
                    if (nuevo.getDato().getId() < root.getLeft().getDato().getId()) {
                        newRoot = leftRotation(root);
                    } else {
                        newRoot = leftDoubleRotation(root);
                    }
                }
            }
        } else if (nuevo.getDato().getId() > root.getDato().getId()) {
            if (root.getRight() == null) {
                root.setRight(nuevo);
            } else {
                root.setRight(insert(nuevo, root.getRight()));

                if (getBalanceFactor(root) == 2) {
                    if (nuevo.getDato().getId() > root.getRight().getDato().getId()) {
                        newRoot = rightRotation(root);
                    } else {
                        newRoot = rightDoubleRotation(root);
                    }
                }
            }
        } else {
            return null;
        }

        return newRoot;
    }

    public void add(Objeto o) {
        TreeNode<Objeto> nuevo = new TreeNode(o);

        if (root == null) {
            root = nuevo;
        } else {
            root = insert(nuevo, root);
        }
    }
    
    public Objeto get(int id, TreeNode<Objeto> r) {
        
        if (root == null) {
            return null;
        } else if (r.getDato().getId() == id) {
            return r.getDato();
        } else if (id < r.getDato().getId()) {
            return get(id, r.getLeft());
        } else {
            return get(id, r.getRight());
        }
    }

}
