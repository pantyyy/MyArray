public class DynamicArray<E> {

    private E[] data;
    private int size;


    //构造函数 , 用户传入数组的容量
    public DynamicArray(int capacity){
        data = (E[]) new Object[capacity];
        //初始化的有效元素为0个
        size = 0;
    }

    //无参构造函数
    public DynamicArray(){
        this(10);
    }


    //获取数组的容量
    public int getCapacity(){
        return data.length;
    }

    //获取有效元素的个数
    public int getSize(){
        return size;
    }

    //判断数组是否为空
    public boolean isEmpty(){
        //size等于0 返回true , 否则返回false
        return size == 0;
    }

    //向index位置 , 插入元素 , 返回值为void
    public void add(int index , E e){
        //因为是动态数组 , 所以不需要判断数组为满的情况
        //index的合法范围为[0 , size]
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

        //判断是否需要扩容了 , 如果当前数组的容量capacity等于有效元素的个数 , 则需要扩容
        if(size == data.length)
            //扩容原来的两倍
            resize(2 * data.length);

        //元素后移 , 把从index开始 , 到size - 1位置的元素都后移一位
        for(int i = size - 1 ; i >= index ; i--){
            data[i+1] = data[i];
        }

        //index位置添加元素
        data[index] = e ;

        //有效元素加一
        size++;

    }


    //向数组尾部添加元素
    public void addLast(E e){
        add(size , e);
    }

    //向数组头部添加元素
    public void addFirst(E e){
        add(0 , e);
    }

    //获取index位置的元素
    public E get(int index){
        //判断index是否合法
        //index的取值范围为[0 , size)
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }


    //修改index位置的元素
    public void set(int index , E e){
        //判断index是否合法
        //index的取值范围为[0 , size)
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");

        data[index] = e;
    }



    //查找数组中是否存在元素e
    public boolean contains(E e){
        //遍历数组 , 判断是否有相同的值
        for(int i = 0 ; i < size ; i++){
            if(data[i].equals(e)){
                return true;
            }
        }

        return false;
    }


    //找到数组中的元素 , 返回索引值
    //-1表示该元素不存在数组中
    public int find(E e){
        for(int i = 0 ; i < size ; i++){
            if(data[i].equals(e))
                return i;
        }
        return -1;
    }


    //从数组中删除index位置的元素 , 返回删除的元素
    public E remove(int index){
        //判断index是否合法
        //index = [0 , size - 1]
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        //要删除的元素
        E ret = data[index];

        //元素前移 , 从index + 1开始 , 到size - 1 结束
        for (int i = index + 1 ; i < size ; i++){
            data[i - 1] = data[i];
        }

        //有效元素减一
        size--;
        data[size] = null;





        return ret;
    }

























    //数组动态扩容 , 由这个类里面的方法调用 , 所以设计为private
    private void resize(int newCapacity){
        //创建新的数组
        E[] newData = (E[])new Object[newCapacity];

        //数组扩容 , 需要拷贝原先的数据
        for(int i = 0 ; i < size ; i++){
            newData[i] = data[i];
        }

        //数组指向新创建的数组
        data = newData;
    }











































}
