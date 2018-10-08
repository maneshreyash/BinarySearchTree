/*
 *  *
 *   Implementation of data structures and algorithms
 * Fall 2018
 * Short Project 4: Binary Search Tree
 * Date: 30th September,2018
 *
 *  Team Members:
 * Shreyash Mane - ssm170730
 * Swapna Chintapalli - sxc180048
 */


package ssm170730;
import java.util.*;


public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {
    static class Entry<T> {
        T element;
        Entry<T> left, right;

        public Entry(T x, Entry<T> left, Entry<T> right) {
            this.element = x;
            this.left = left;
            this.right = right;
        }
    }

    Entry<T> root;
    int size;
    Stack<T> s1 = new Stack<T>();


    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    /**
     *
     * @param x = the element to be found
     * @return returns the entry where x is located
     */
    public Entry<T> find(T x)
    {
        s1.push(null);
        return find(root,x);
    }

    /**
     *
     * @param t within the tree rooted at t
     * @param x the element that we find
     * @return returns the element t when found
     */
    public Entry<T> find(Entry<T> t,T x)
    {
        if(t == null || t.element == x)
            return t;
        while(true)
        {
            if(x.compareTo((T) t.element)< 0 )
            {
                if(t.left == null)
                    break;
                else
                {
                    s1.push((T) t.element);
                    t= t.left;
                }
            }
            else if(x == t.element)
            {
                break;
            }
            else
            {
                if(t.right == null)
                {
                    break;
                }
                else
                {
                    s1.push((T) t.element);
                    t = t.right;
                }
            }

        }
        return t;
    }
    /** TO DO: Is x contained in tree?
     */

    /**
     *
     * @param x check whether the tree has x
     * @return true if x is present, false if x is absent
     */
    public boolean contains(T x) {
        Entry<T> t = find(x);
        if(t == null || t.element != x)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /** TO DO: Is there an element that is equal to x in the tree?
     *  Element in tree that is equal to x is returned, null otherwise.
     */

    /**
     *
     * @param x element to get from tree
     * @return if present return the element else return false
     */
    public T get(T x)
    {
        Entry<T> t = root;
        if(t == null)
            return null;
        while(t != null)
        {
            if(x.compareTo((T)t.element) < 0)
                t = t.left;
            else if(x.compareTo((T)t.element) > 0)
                t = t.right;
            else  if(x == t.element)
                return t.element;
        }
        System.out.println("Doesn't contain "+ x);
        return null;
    }

    /** TO DO: Add x to tree.
     *  If tree contains a node with same key, replace element by x.
     *  Returns true if x is a new element added to tree.
     */

    /**
     *
     * @param x add element x to the tree
     * @return returns true if add successful
     */
    public boolean add(T x) {
        if(size == 0)
        {
            root = new Entry<T>(x,null,null);
            size = 1;
            return true;
        }
        else
        {
            Entry<T> t = find(x);
            if(t.element == x)
            {
                t.element = x;
                return true;
            }
            else if(x.compareTo((T) t.element) < 0)
            {
                t.left = new Entry<T>(x,null,null);
            }
            else
            {
                t.right = new Entry<T>(x,null,null);
            }
            size++;
            return true;
        }
    }


    /** TO DO: Remove x from tree.
     *  Return x if found, otherwise return null
     */

    /**
     *
     * @param x remove x from the tree and reorder the tree so that it does not violate the constgraints
     * @return element which has been removed or return null if element is not present
     */

    public T remove(T x) {
        Entry<T> current = root;
        Entry<T> parent = root;
        boolean isLeftChild = false;
        if(size == 0)
            return null;
        while(current.element!=x)
        {
            parent = current;
            if(x.compareTo((T)current.element) < 0)
            {
                isLeftChild = true;
                current = current.left;
            }
            else
            {
                isLeftChild = false;
                current = current.right;
            }
            if(current == null)
                return null;
        }
        //No children
        if(current.left == null && current.right == null)
        {
            if(current == root)
                root = null;
            else if(isLeftChild == true)
                parent.left = null;
            else
                parent.right = null;
            size--;
            return current.element;
        }
        else if(current.right == null)//Only one child
        {
            if(current==root)
            {
                root = current.left;
            }
            else if(isLeftChild)
            {
                parent.left = current.left;
            }
            else
            {
                parent.right = current.left;
            }
        }
        else if(current.left==null)
        {
            if(current==root)
            {
                root = current.right;
            }
            else if(isLeftChild)
            {
                parent.left = current.right;
            }
            else
            {
                parent.right = current.right;
            }
        }
        else if(current.left!=null && current.right!=null)
        {
            Entry<T> successor	 = getSucc(current);
            if(current==root)
            {
                root = successor;
            }
            else if(isLeftChild)
            {
                parent.left = successor;
            }
            else
            {
                parent.right = successor;
            }
            successor.left = current.left;
        }

        size--;
        return current.element;
    }

    /**
     *
     * @param t get successor of t
     * @return return the successor
     */
    public Entry<T> getSucc(Entry<T> t)
    {
        Entry<T> s =null;
        Entry<T> sucParent =null;
        Entry<T> current = t.right;
        while(current!=null){
            sucParent = s;
            s = current;
            current = current.left;
        }
        if(s!=t.right){
            sucParent.left = s.right;
            s.right = t.right;
        }
        return s;
    }

    /**
     *
     * @return returns the minimum element of the tree
     */
    public T min() {
        if(size == 0)
        {
            return null;
        }
        Entry<T> t = root;
        while(t.left != null)
        {
            t = t.left;
        }
        return (T) t.element;
    }


    /**
     *
     * @return returns the minimum element of the tree
     */
    public T max() {
        if(size == 0)
        {
            return null;
        }
        Entry<T> t = root;
        while(t.right != null)
        {
            t = t.right;
        }
        return (T) t.element;
    }


    // TODO: Create an array with the elements using in-order traversal of tree

    /**
     *
     * @return an array containing all the elements travelled in order
     */
    public Comparable[] toArray() {
        Comparable[] arr = new Comparable[size];
        int i = 0;
        //i++;
        toArrayhelp(root, arr, i);
        return arr;
    }

    /**
     * Helper method to implement toArray to implement iterative calls on Entry type T
     * @param node initiator for the method
     * @param array the array into which all elements are to be stored
     * @param i index counter
     * @return returns the array containing elements from the tree
     */
    public Comparable[] toArrayhelp(Entry<T> node,Comparable[] array ,int i){
        if(node == null)
            return null;
        Stack s = new Stack();
        while(true){
            if(node!=null){
                s.push(node);
                node = node.left;
            }else{
                if (s.isEmpty()) {
                    break;
                }
                node = (Entry<T>) s.pop();
                array[i++] = node.element;
                node = node.right;
            }
        }
        return array;
    }


    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            int x = in.nextInt();
            if(x > 0) {
                System.out.print("Add " + x + " : ");
                t.add(x);
                t.printTree();
            } else if(x < 0) {
                System.out.print("Remove " + x + " : ");
                t.remove(-x);
                t.printTree();
            } else {
                Comparable[] arr = t.toArray();
                System.out.print("Final: ");
                for(int i=0; i<t.size; i++) {
                    System.out.print(arr[i] + " ");
                }
                System.out.println();
                return;
            }
        }
    }


    public void printTree() {
        System.out.print("[" + size + "]");
        printTree(root);
        System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
        if(node != null) {
            printTree(node.left);
            System.out.print(" " + node.element);
            printTree(node.right);
        }
    }

}

