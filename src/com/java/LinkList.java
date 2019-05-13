package com.java;

import java.lang.reflect.Array;
import java.util.Iterator;

public class LinkList<T> extends AbsList implements Iterable {
    Lnode<T> first, last;
    Iterator<T> itr;

    public LinkList(){
        first = last = null;
        length = 0;
        itr = new LinkIterator();
    }
    private int compare(Lnode<T> a, Lnode<T> b){
        return a.compareTo(b);
    }
    @Override
    public void clear() {
        first = last = null;
    }
    public void removeAll(){
        clear();
    }
    protected Lnode<T> getNode (int i){
        if (i < 0 || i > length - 1){
            return null;
        }
        if (i == 0){
            return first;
        }
        Lnode<T> p = first;
        int j = 0;
        while (p != null && j < i){
            p = p.next;
            j++;
        }
        return p;
    }
    @Override
    public T get(int i) {
        Lnode<T> p = getNode(i);
        if (p == null){
            return null;
        }else {
            return p.data;
        }
    }
    @Override
    public boolean set(int i, Object x) {
        Lnode<T> p = getNode(i);
        if (p == null){
            return false;
        }else {
            p.data = (T)x;
            return true;
        }
    }
    @Override
    public void add(int i, Object x) {
        Lnode<T> p,s;
        int j = i - 1;
        s = new Lnode<T>((T)x,null);
        if (first == null || length == 0){
            first = s;
            last = s;
        }else if (j < 0){
            s.next = first;
            first = s;
        }else if (j >= length - 1){
            last.next = s;
            last = s;
        }else {
            p = getNode(j);
            s.next = p.next;
            p.next = s;
        }
        length++;
    }
    @Override
    public void add(Object x) {
        add(length, (T)x);
    }
    public void addBack(T x){
        add(length, x);
    }
    public void addFront(T x){
        add(0, x);
    }
    public void sort(){
        LinkList<T> sl = new LinkList<T>();
        Lnode<T> p;
        p = this.removeNode(0);
        while (p != null){
            sl.addSort(p.data);
            p = this.removeNode(0);
        }
        this.first = sl.first;
        this.last = sl.last;
        this.length = sl.length;
    }
    public void addSort(T e){
        Lnode<T> s = new Lnode<T>(e,null);
        insertOrder(s);
    }
    private void insertOrder(Lnode<T> s){
        Lnode<T> p1,p2;
        length++;
        if (first == null){
            first = s;
            last = s;
            return;
        }
        if (compare(s,first) < 0){
            s.next = first;
            return;
        }
        if (compare(s,last) >= 0){
            last.next = s;
            last = s;
        }
        p2 = first;
        p1 = p2;
        while (p2 != null){
            if (compare(s,p2) > 0){
                p1 = p2;
                p2 = p2.next;
            }
            else
                break;
        }
        s.next = p2;
        p1.next = s;
        return;
    }
    @Override
    public T remove(int i) {
        Lnode<T> p = removeNode(i);
        if (p != null){
            return p.data;
        }else {
            return null;
        }
    }
    public T remove(){
        return removeNode(0).data;
    }
    public T removeFront(){
        return removeNode(0).data;
    }
    public T removeBack(){
        return removeNode(length - 1).data;
    }
    @Override
    public Object remove(Object x) {
        int i = 0;
        Lnode<T> p = first;
        while (p != null && i <= length - 1){
            if (p.data == (T)x){
                return removeNode(i);
            }
            i++;
            p = p.next;
        }
        return null;
    }
    protected Lnode<T> removeNode(int i){
        Lnode<T> p,q;
        if (first == null){
            return null;
        }
        if (i == 0){
            p = first;
            first = first.next;
            return p;
        }
        if (i >= 1 && i <= length - 1){
            p = getNode(i - 1);
            q = p.next;
            p.next = q.next;
            if (q == last){
                last = p;
            }
            length--;
            return q;
        }
        return null;
    }
    @Override
    public int indexOf(int begin, int end, Object o) {
        Lnode<T> p = getNode(begin);
        int i = begin;
        while (p != null && i <= end){
            if (p.data.equals(o))
                return i;
            p = p.next;
            i++;
        }
        return -1;
    }
    @Override
    public int indexOf(Object x) {
        return indexOf(0,length - 1, (T)x);
    }
    public T search(T key){
        Lnode<T> p = getNode(0);
        while (p != null){
            if (p.data.equals(key))
                return p.data;
            p = p.next;
        }
        return null;
    }
    public boolean contains(T key){
        if (indexOf(key) == -1)
            return false;
        else
            return true;
    }
    @Override
    public String toString() {
        String s = new String();
        Lnode<T> p;
        p = first;
        if (p == null){
            return s;
        }
        s = "(";
        s = s + p.data.toString();
        p = p.next;
        while (p != null){
            s = s+",";
            s = s+p.data.toString();
            p = p.next;
        }
        s = s+")";
        return s;
    }
    public Object[] toArray(){
        Object[] a = new Object[length];
        Lnode<T> p = first;
        for (int i = 0; i < length; i++){
            a[i] = p.data;
            p = p.next;
        }
        return a;
    }
//!!!!!!!!!!!!!!!!!!!!!!11有问题
    public <E> E[] toArray(E[] a){
        if (a.length < this.length){
            a = (E[]) Array.newInstance(a.getClass().getComponentType(), this.length);
            int i = 0;
            Object[] result = a;
            Lnode<T> x = this.first;
            for (i = 0; i < length; i++){
                result[i] = x.data;
                x = x.next;
            }
        }
        if (a.length > length){
            a[length] = null;
        }
        return a;
    }
    @Override
    public Iterator iterator() {
        return this.itr;
//        return new LinkIterator();
    }

    private class LinkIterator implements Iterator<T>{
        private int index = 0;
        private Lnode<T> current = first;
        @Override
        public boolean hasNext() {
            return (index !=length() && current != null);
        }
        @Override
        public T next() {
            T temp = current.data;
            current = current.next;
            index++;
            return temp;
        }
        public int nextIndex(){
            return index++;
        }
    }
}
