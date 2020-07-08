package me.windleafy.kity.java.collection;

import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.ObjectUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.RandomAccess;

import me.windleafy.kity.java.utils.ObjectKit;

public class MapList<KEY, VALUE> implements Cloneable {

    private HashMap<KEY, List<VALUE>> mMap;

    public MapList() {
        mMap = new HashMap<>();
    }

    private void initValue(KEY key) {
        List<VALUE> list = mMap.get(key);
        if (list == null) {
            list = new ArrayList<>();
            mMap.put(key, list);
        }
    }

    /**
     * map
     */
    public void putList(KEY key, List<VALUE> list) {
        mMap.put(key, list);
    }

    public void copyList(KEY key, List<VALUE> list) {
        List<VALUE> newList = new ArrayList<>();
        CollectionUtils.addAll(newList, (VALUE[]) new Object[list.size()]);
        Collections.copy(newList, list);//List中的对象相同
        mMap.put(key, newList);
    }

    public void copyListDeep(KEY key, List<VALUE> list) {
        List<VALUE> newList = new ArrayList<>();
        for (VALUE value : list) {
            try {
                newList.add(ObjectKit.deepClone(value));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        mMap.put(key, newList);
    }

    public List<VALUE> getList(KEY key) {
        initValue(key);//not null
        return mMap.get(key);
    }

    public List<VALUE> removeList(KEY key) {
        return mMap.remove(key);
    }

    public boolean containKey(KEY key) {
        return mMap.containsKey(key);
    }

    public boolean containList(List<VALUE> list) {
        return mMap.containsValue(list);
    }

    public void clearMap() {
        mMap.clear();
    }


    /**
     * list
     */
    public void addValue(KEY key, VALUE value) {
        List<VALUE> list = getList(key);
        list.add(value);
    }

    public void addValue(KEY key, int index, VALUE value) {
        List<VALUE> list = getList(key);
        list.add(index, value);
    }

    public VALUE getValue(KEY key, int index) {
        List<VALUE> list = getList(key);
        return list.get(index);
    }

    public VALUE getValueFirst(KEY key, VALUE value) {
        List<VALUE> list = getList(key);
        int index = list.indexOf(value);
        if (index == -1) return null;
        return list.get(index);
    }

    public VALUE getValueLast(KEY key, VALUE value) {
        List<VALUE> list = getList(key);
        int index = list.lastIndexOf(value);
        if (index == -1) return null;
        return list.get(index);
    }

    public VALUE getFirst(KEY key) {
        List<VALUE> list = getList(key);
        if (list.size() <= 0) return null;
        return list.get(0);
    }

    public VALUE getLast(KEY key) {
        List<VALUE> list = getList(key);
        if (list.size() <= 0) return null;
        return list.get(list.size() - 1);
    }

    public VALUE setValue(KEY key, int index, VALUE value) {
        List<VALUE> list = getList(key);
        return list.set(index, value);
    }

    public void removeValue(KEY key, VALUE value) {
        List<VALUE> list = getList(key);
        list.remove(value);
        if (list.size() == 0)
            removeList(key);
    }

    public void removeValue(KEY key, int index) {
        List<VALUE> list = getList(key);
        list.remove(index);
        if (list.size() == 0)
            removeList(key);
    }

    public void clearList(KEY key) {
        List<VALUE> list = getList(key);
        list.clear();
        removeList(key);
    }

    public boolean containValue(KEY key, VALUE value) {
        List<VALUE> list = getList(key);
        return list.contains(value);
    }

    public int size(KEY key) {
        List<VALUE> list = getList(key);
        return list.size();
    }

    public int indexOf(KEY key, VALUE value) {
        List<VALUE> list = getList(key);
        return list.indexOf(value);
    }

    public int lastIndexOf(KEY key, VALUE value) {
        List<VALUE> list = getList(key);
        return list.lastIndexOf(value);
    }

    public Iterator<VALUE> iterator(KEY key) {
        List<VALUE> list = getList(key);
        return list.iterator();
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
