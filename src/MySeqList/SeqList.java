package MySeqList;

import java.util.Iterator;

public class SeqList<T> extends AbsList implements Iterable {

    private int incrementSize;
    protected T[] data;

    public SeqList(){this(16);}
    public SeqList(int capacity){

    }
    public SeqList(T[] elem){

    }
    public void setInc(int inc){

    }
    public void setCapacity(int newsize){

    }
    public int getCapacity(){
        return data.length;
    }
    public int sizse(){
        return length;
    }
    @Override
    public T get(int i) {

    }

    @Override
    public boolean set(int i, Object x) {

    }

    private int compare(T a,T b){

    }

    @Override
    public int indexOf(int begin, int end, Object o) {

    }

    private void grow(){

    }

    @Override
    public void add(int i, Object x) {

    }

    public void addSort(T x){

    }

    public void sort(){

    }

    protected void insertOrder(int end, T x){

    }

    @Override
    public void clear() {

    }

    @Override
    public Object remove(int i) {

    }

    @Override
    public String toString() {

    }

    public Object[] toArray(){

    }

    public T[] toArray(T[] a){

    }

    @Override
    public Iterable iterable() {
        return null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    class MyIterator implements Iterable<T>{
        private int index = 0;
        public boolean hasNext(){
            return index != length();
        }
        public T next(){
            return get(index++);
        }
        @Override
        public Iterator<T> iterator() {
            return null;
        }
    }
}
