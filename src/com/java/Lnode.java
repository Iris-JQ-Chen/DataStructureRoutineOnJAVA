package com.java;

public class Lnode<T> implements Comparable<Lnode<T>> {

    public T data;
    public Lnode<T> next;
    public Lnode(T key){
        data = key;
        next = null;
    }
    public Lnode(T key, Lnode<T> next){
        data = key;
        this.next = next;
    }

    @Override
    public boolean equals(Object obj) {
        Lnode<T> node = (Lnode<T>)obj;
        return data.equals(node.data);
    }

    @Override
    public int compareTo(Lnode<T> o) {
        Comparable<T> x;
        if (data instanceof Comparable){
            x = (Comparable<T>)data;
            return (int)x.compareTo(o.data);
        }else {
            throw new ClassCastException("类型无法比较!");
        }
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
