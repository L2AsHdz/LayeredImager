package layeredimager.edd.tree;

import layeredimager.edd.TreeNode;
import layeredimager.model.user.Usuario;

/**
 *
 * @date 11/04/2021
 * @time 21:46:52
 * @author asael
 */
public class UserArbolAVL {
    
    private TreeNode<Usuario> root;
    
    private TreeNode<Usuario> rightRotation(TreeNode<Usuario> oldRoot) {
        TreeNode<Usuario> newRoot = oldRoot.getLeft();
        oldRoot.setLeft(newRoot.getRight());
        newRoot.setRight(oldRoot);

        return newRoot;
    }

    private TreeNode<Usuario> leftRotation(TreeNode<Usuario> oldRoot) {
        TreeNode<Usuario> newRoot = oldRoot.getRight();
        oldRoot.setRight(newRoot.getLeft());
        newRoot.setLeft(oldRoot);

        return newRoot;
    }

    private TreeNode<Usuario> rightDoubleRotation(TreeNode<Usuario> oldRoot) {
        TreeNode<Usuario> newRoot;
        oldRoot.setLeft(leftRotation(oldRoot.getLeft()));
        newRoot = rightRotation(oldRoot);

        return newRoot;

    }

    private TreeNode<Usuario> leftDoubleRotation(TreeNode<Usuario> oldRoot) {
        TreeNode<Usuario> newRoot;
        oldRoot.setRight(rightRotation(oldRoot.getRight()));
        newRoot = leftRotation(oldRoot);

        return newRoot;
    }

    public void inOrden(TreeNode<Usuario> root) {
        if (root != null) {
            inOrden(root.getLeft());
            System.out.print(root.getDato().getNombre()+ ", ");
            inOrden(root.getRight());
        }
    }

    public void preOrden(TreeNode<Usuario> root) {
        if (root != null) {
            System.out.print(root.getDato().getNombre() + ", ");
            preOrden(root.getLeft());
            preOrden(root.getRight());
        }
    }

    public void postOrden(TreeNode<Usuario> root) {
        if (root != null) {
            postOrden(root.getLeft());
            postOrden(root.getRight());
            System.out.print(root.getDato().getNombre() + ", ");
        }
    }

    public TreeNode<Usuario> getRaiz() {
        return root;
    }

    public int getBalanceFactor(TreeNode<Usuario> root) {
        if (root == null) {
            return 0;
        } else if (root.getRight() == null & root.getLeft() != null) {
            return -(getHeight(root.getLeft()) + 1);
        } else if (root.getRight() != null & root.getLeft() == null) {
            return getHeight(root.getRight()) + 1;
        } else if (root.getRight() != null & root.getLeft() != null) {
            return getHeight(root.getRight()) - getHeight(root.getLeft());
        } else {
            return 0;
        }
    }

    private int getHeight(TreeNode<Usuario> root) {
        int Altder = ((root.getRight() == null) ? 0 : 1 + getHeight(root.getRight()));
        int Altizq = ((root.getLeft() == null) ? 0 : 1 + getHeight(root.getLeft()));
        return Math.max(Altder, Altizq);
    }

    private TreeNode<Usuario> insert(TreeNode<Usuario> nuevo, TreeNode<Usuario> root) {
        TreeNode<Usuario> newRoot = root;

        if ((nuevo.getDato().getNombre().compareTo(root.getDato().getNombre())) < 0) {
            if (root.getLeft() == null) {
                root.setLeft(nuevo);
            } else {
                root.setLeft(insert(nuevo, root.getLeft()));

                if (getBalanceFactor(root) == -2) {
                    if ((nuevo.getDato().getNombre().compareTo(root.getLeft().getDato().getNombre())) < 0) {
                        newRoot = rightRotation(root);
                    } else {
                        newRoot = rightDoubleRotation(root);
                    }
                }
            }
        } else if ((nuevo.getDato().getNombre().compareTo(root.getDato().getNombre())) > 0) {
            if (root.getRight() == null) {
                root.setRight(nuevo);
            } else {
                root.setRight(insert(nuevo, root.getRight()));

                if (getBalanceFactor(root) == 2) {
                    if ((nuevo.getDato().getNombre().compareTo(root.getRight().getDato().getNombre())) > 0) {
                        newRoot = leftRotation(root);
                    } else {
                        newRoot = leftDoubleRotation(root);
                    }
                }
            }
        } else {
            return null;
        }

        return newRoot;
    }

    public void add(Usuario o) {
        TreeNode<Usuario> nuevo = new TreeNode(o);

        if (root == null) {
            root = nuevo;
        } else {
            root = insert(nuevo, root);
        }
    }
    
    private TreeNode<Usuario> get(String nombre, TreeNode<Usuario> root) {
        
        if (root == null) {
            return null;
        } else if (root.getDato().getNombre().equals(nombre)) {
            return root;
        } else if ((nombre.compareTo(root.getDato().getNombre())) < 0) {
            return get(nombre, root.getLeft());
        } else {
            return get(nombre, root.getRight());
        }
    }
    
    public Usuario get(String nombre) {
        return get(nombre, root).getDato();
    }
    
    private TreeNode<Usuario> remove(String nombre, TreeNode<Usuario> root) {
        TreeNode<Usuario> actual = root;
        
        if (actual == null) {
            return actual;
        }
        
        if ((nombre.compareTo(actual.getDato().getNombre())) < 0) {
            actual.setLeft(remove(nombre, actual.getLeft()));
        } else if ((nombre.compareTo(actual.getDato().getNombre())) > 0) {
            actual.setRight(remove(nombre, actual.getRight()));
        } else {
            
            if (actual.getLeft() == null | actual.getRight() == null) {
                TreeNode<Usuario> aux = null;
                if (aux == actual.getLeft()) {
                    aux = actual.getRight();
                } else {
                    aux = actual.getLeft();
                }
                
                if (aux == null) {
                    actual = null;
                } else {
                    actual = aux;
                }
            } else {
                TreeNode<Usuario> reemplazo = getReemplazo(actual.getLeft());
                actual.setDato(reemplazo.getDato());
                actual.setLeft(remove(reemplazo.getDato().getNombre(), actual.getLeft()));
            }
        }
        
        //eliminar este if
        if (actual == null) {
            return actual;
        }
        
        int balanceFactor = getBalanceFactor(actual);
        
        //cambiar por == 2
        if ((balanceFactor > 1) & (getBalanceFactor(actual.getLeft()) >= 0)) {
            return leftRotation(actual);
        } else if (balanceFactor < -1 & getBalanceFactor(actual.getRight()) <= 0) {
            return rightRotation(actual);
        } else if (balanceFactor > 1 & getBalanceFactor(actual.getLeft())  < 0) {
            return leftDoubleRotation(actual);
        } else if (balanceFactor < -1 & getBalanceFactor(actual.getRight()) > 0) {
            return rightDoubleRotation(actual);
        }
        
        return actual;
    }
    
    public Usuario remove(String nombre) {
        return remove(nombre, root).getDato();
    }

    private TreeNode<Usuario> getReemplazo(TreeNode<Usuario> root) {
        TreeNode<Usuario> actual = root;
        
        while (actual.getRight()!= null) {
            actual = actual.getRight();
        }
        
        return actual;
    }
}
