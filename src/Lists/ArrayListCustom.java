package Lists;
// <------Incomplete yet ---->
public class ArrayListCustom<T> {

    private final int DEFAULT_CAPACITY = 17;
    private int size = 0;
    private Object[] arr;

    public ArrayListCustom(int initialSize) {
        arr = new Object[initialSize];
    }

    public ArrayListCustom() {
        arr = new Object[DEFAULT_CAPACITY];
    }
    private void doubleArr(){
        Object [] temp=new Object[2*arr.length];
        for(int i=0;i<arr.length;i++){
            temp[i]=arr[i];
        }
        arr=temp;
    }

    public boolean add(T val) {
        if(arr.length==size){
            doubleArr();
        }
        arr[size] = val;
        size++;
        return true;
    }

    public void add(int index, T val) {
        if (index == size) {
            add(val);
            return;
        }
        for(int i=0;i<index;i++){
            
        }
    }

    public T get(int index) {
        return (T) arr[index];
    }

}
