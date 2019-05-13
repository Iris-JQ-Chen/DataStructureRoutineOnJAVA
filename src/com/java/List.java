package com.java;

public interface List<T> {

    boolean isEmpty();
    int length();
    T get(int i);
    boolean set(int i,T x);
    void add(T x);
    T remove(T x);
    int indexOf(T x);

}
