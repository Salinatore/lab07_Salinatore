package it.unibo.inner.impl;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    T[] elements;
    Predicate<T> filter;

    public IterableWithPolicyImpl (T[] elements) {
        this(
                elements,
                new Predicate<T>() {
                    @Override
                    public boolean test(T elem) {
                        return true;
                    }
                }
        );
    }

    public IterableWithPolicyImpl (T[] elements, Predicate<T> filter) {
        this.elements = Arrays.copyOf(elements, elements.length);
        setIterationPolicy(filter);
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new arrayIterator();
    }

    private class arrayIterator implements Iterator<T> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            while(notTheEnd()) {
                if(IterableWithPolicyImpl.this.filter.test(IterableWithPolicyImpl.this.elements[current])){
                    return true;
                } else {
                    current++;
                }
            }

            return false;
        }

        private boolean notTheEnd(){
            return this.current < IterableWithPolicyImpl.this.elements.length;
        }

        @Override
        public T next() {
            if(hasNext()) {
                return IterableWithPolicyImpl.this.elements[this.current++];
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
