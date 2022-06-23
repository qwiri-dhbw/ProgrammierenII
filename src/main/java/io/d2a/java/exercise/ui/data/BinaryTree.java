package io.d2a.java.exercise.ui.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> implements Iterable<T> {

    private final T value;

    protected BinaryTree<T> left;
    protected BinaryTree<T> right;

    public BinaryTree(final T value) {
        this.value = value;
    }

    public boolean add(final T value) {
        switch (value.compareTo(this.value)) {
            case -1:
                if (this.left == null) {
                    this.left = new BinaryTree<>(value);
                    return true;
                }
                return this.left.add(value);
            case 1:
                if (this.right == null) {
                    this.right = new BinaryTree<>(value);
                    return true;
                }
                return this.right.add(value);
            default:
                return false;
        }
    }

    public T get() {
        return value;
    }

    public List<T> traverse() {
        final List<T> list = new ArrayList<>();
        this.a(list);
        return list;
    }

    /**
     * Helper function for {@link BinaryTree#traverse()} that recursive adds all values to a list
     *
     * @param list list to add values in a sorted order
     */
    private void a(final List<T> list) { // TOFU: give this a nice, descriptive name
        // add values from children
        if (this.left != null) {
            this.left.a(list);
        }
        list.add(this.value);
        if (this.right != null) {
            this.right.a(list);
        }
    }

    ///
    /// Some more additions I wanted to add:
    ///

    // Allow to create a BinaryTree given a collection
    public BinaryTree(final Collection<? extends T> collection) {
        if (collection.size() == 0) {
            throw new IllegalArgumentException("collection cannot be empty");
        }
        final List<T> list = new ArrayList<>(collection.size() - 1);
        T first = null;
        for (final T t : collection) {
            if (t == null) {
                throw new IllegalArgumentException("collection cannot contain null values");
            } else if (first == null) {
                first = t;
            } else {
                list.add(t);
            }
        }
        this.value = first;
        this.addAll(list);
    }

    // Make an immutable binary tree
    public ImmutableBinaryTree<T> immutable() {
        return new ImmutableBinaryTree<T>(this.value, this.left, this.right);
    }

    // addAll function that bulk adds multiple elements from a list/array to the binary tree
    public boolean addAll(final List<T> list) {
        boolean result = true;
        for (final T t : list) {
            if (!this.add(t)) {
                result = false;
            }
        }
        return result;
    }

    // addAll function that bulk adds multiple elements from a list/array to the binary tree
    public boolean addAll(final T... values) {
        boolean result = true;
        for (final T t : values) {
            if (!this.add(t)) {
                result = false;
            }
        }
        return result;
    }

    public int size() {
        return (this.left != null ? this.left.size() : 0) +
            (this.right != null ? this.right.size() : 0) + 1;
    }

    // toString method
    @Override
    public String toString() {
        final StringBuilder bob = new StringBuilder("(");
        if (this.left != null) {
            bob.append(this.left).append(" < ");
        }
        bob.append(this.value);
        if (this.right != null) {
            bob.append(" > ").append(this.right);
        }
        return bob.append(")").toString();
    }

    // allow a binary tree to be used in an enhanced for-loop
    @Override
    public Iterator<T> iterator() {
        return this.traverse().iterator();
    }

    ///
    ///
    ///

    public static void main(String[] args) throws Exception {
        final Integer[] values = new Integer[]{
            15, 10, 0, 1, 1, 2, 3, 3, 1, 3, 11, 41, 166, 41, 21, 4, 99, 187, 1337, 420, 69
        };
        final BinaryTree<Integer> root = new BinaryTree<>(values[0]);
        for (int i = 1; i < values.length; i++) {
            final int value = values[i];
            System.out.println("add " + value + " to tree: " + root.add(value));
        }
        System.out.println(root);
        System.out.println(root.traverse());
        System.out.println(root.size());
    }

}
