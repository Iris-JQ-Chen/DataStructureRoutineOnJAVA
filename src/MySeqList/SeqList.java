package MySeqList;

import java.util.Arrays;
import java.util.Iterator;

public class SeqList<T> extends AbsList implements Iterable {

    private int incrementSize;
    protected T[] data;

    public SeqList() {
        this(16);
    }
    public SeqList(int capacity){
        if (capacity <= 0){
            capacity = 16;
        }
        length = 0;
        incrementSize = 0;
        data = (T[]) new Object[capacity];
    }
    public SeqList(T[] elem){
        length = elem.length;
        incrementSize = 0;
        data = Arrays.copyOf(elem, length);
    }
    public void setInc(int inc){
        incrementSize = inc;
    }
    public void setCapacity(int newsize){
        data = Arrays.copyOf(data,newsize);
    }
    public int getCapacity(){
        return data.length;
    }
    public int sizse(){
        return length;
    }
    @Override
    public T get(int i) {
        if (i < 0 || i > length - 1)
            return null;
        return (T)data[i];
    }
    @Override
    public boolean set(int i, Object x) {
        if (i < 0 || i > length - 1)
            return false;
        else {
            data[i] = (T)x;
            return true;
        }
    }
    private int compare(T a,T b){
        if (a instanceof  Comparable && b instanceof Comparable){
            return ((Comparable)a).compareTo((Comparable)b);
        }else {
            return ((String)a).compareTo((String)b);
        }
    }
    @Override
    public int indexOf(int begin, int end, Object o) {
        if (o == null){
            for (int i = begin; i < end; i++){
                if (data[i] == null){
                    return i;
                }
            }
        }else {
            for (int i = begin; i <end; i++){
                if (compare((T)o, data[i]) == 0){
                    return i;
                }
            }
        }
        return -1;
    }
    @Override
    public int indexOf(Object x) {
        return indexOf(0,length,x);
    }
    private void grow(){
        int newsize = data.length + incrementSize;
        data = Arrays.copyOf(data, newsize);
    }
    @Override
    public void add(int i, Object x) {
        if (length == data.length)
            grow();
        if (i < 0)
            i = 0;
        if (i > length)
            i = length;
        for (int j = length - 1; j >= i; j--){
            data[j + 1] = data[j];
        }
        data[i] = (T) x;
        length ++;
    }
    @Override
    public void add(Object x) {
        if (length == data.length)
            grow();
        data[length] = (T) x;
        length ++;
    }
    @Override
    public void append(Object x) {
        add(x);
    }
    public void addSort(T x){
        insertOrder(length,x);
        length ++;
    }
    public void sort(){
        for (int i = 1;i <= length - 1; i++){
            insertOrder(i,data[i]);
        }
    }
    protected void insertOrder(int end, T x){
        if (length == data.length)
            grow();
        int k;
        for (k = end - 1; k >= 0; k--){
            if (compare(x, data[k]) < 0){
                data[k + 1] = data[k];
            }else {
                break;
            }
        }
        data[k+1] = x;
    }
    @Override
    public void clear() {
        for (int i = 0; i <length; i++){
            data[i] = null;
            length = 0;
        }
    }
    @Override
    public Object remove(int i) {
        if (i < 0 || i > length - 1){
            throw new IndexOutOfBoundsException("下标越界 i = "+i);
        }
        T olddata = (T) data[i];
        for (int j = i; j < length - 1; j++){
            data[j] = data[j + 1];
        }
        data[--length] = null;
        return olddata;
    }
    @Override
    public Object remove(Object x) {
        int i = indexOf(x);
        return remove(i);
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder = builder.append("(");
        for (int i = 0; i <length - 1; i++){
            builder = builder.append(data[i].toString()+",");
        }
        builder = builder.append(")");
        return builder.toString();
    }
    public Object[] toArray(){
        return Arrays.copyOf(this.data, this.length);
    }
    public T[] toArray(T[] a){
        if (a.length < length){
            return (T[]) Arrays.copyOf(this.data, this.length,a.getClass());
        }
        System.arraycopy(this.data, 0, a, 0, this.length);
        if (a.length > this.length){
            a[length] = null;
        }
        return a;
    }
    ////////////////////////////////////////////////////////////////有问题
    @Override
    public Iterable iterable() {
        return null;
    }
    ///////////////////////////////////////////////////////////////有问题
    @Override
    public Iterator iterator() {
       return new MyIterator();
    }
    class MyIterator implements Iterator<T>{
        private int index = 0;
        @Override
        public boolean hasNext() {
            return index != length();
        }
        @Override
        public T next() {
            return get(length++);
        }
        @Override
        public void remove() {
            //
        }
    }
}
