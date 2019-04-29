package MySeqList;

public abstract class AbsList<T> implements Iterable, List {

    protected int length;
    abstract public T get(int i);
    abstract public int indexOf(int begin ,int end, T o);
    abstract public void add(int i, T x);
    abstract public void clear();
    abstract public T remove(int i);
    abstract public Iterable iterable();

    @Override
    public boolean isEmpty() {
       return length == 0;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public void add(Object x) {
        add(length, (T) x);
    }

    public void append(T x){
        add(length, x);
    }

    @Override
    public Object remove(Object x) {
        return remove(indexOf(x));
    }

    @Override
    public int indexOf(Object x) {
        return indexOf(0, length, (T) x);
    }

    public int indexOf(int begin, T o){
        return indexOf(begin,length,o);
    }


}
