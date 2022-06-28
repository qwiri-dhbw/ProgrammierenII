package io.d2a.java.exercise.collections;

public class ImmutableBinaryTree<T extends Comparable<T>> extends BinaryTree<T> {

    public ImmutableBinaryTree(final T value, final BinaryTree<T> left, final BinaryTree<T> right) {
        super(value);
        super.left = left;
        super.right = right;
    }

    @Override
    public boolean add(final T value) {
        throw new IllegalArgumentException("binary tree is immutable");
    }

    @Override
    public boolean addAll(final T... values) {
        throw new IllegalArgumentException("binary tree is immutable");
    }

}
