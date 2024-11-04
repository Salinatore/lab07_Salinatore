package it.unibo.inner.impl;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    T[] array;

    public IterableWithPolicyImpl (T[] array_input) {
        array = Arrays.copyOf(array_input, array_input.length);
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {

    }

    @Override
    public Iterator<T> iterator() {
        return new arrayIterator();
    }

    private class arrayIterator implements Iterator<T> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return (this.current < IterableWithPolicyImpl.this.array.length);
        }

        @Override
        public T next() {
            if(current < IterableWithPolicyImpl.this.array.length) {
                return IterableWithPolicyImpl.this.array[this.current++];
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
