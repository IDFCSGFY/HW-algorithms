package org.example.model;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private String[] array;
    private int actualSize;

    public StringListImpl() {
        this.array = new String[10];
        this.actualSize = 0;
    }

    public StringListImpl(int initialSize) {
        this.array = new String[initialSize];
        this.actualSize = 0;
    }

    private void assureExistence() {
        if (this.array == null) {
            this.array = new String[10];
            this.actualSize = 0;
        }
    }

    private void grow() {
        String[] newarr = new String[(int) (actualSize * 1.5)];
        if (actualSize >= 0) System.arraycopy(array, 0, newarr, 0, actualSize);
        this.array = newarr;
    }

    @Override
    public String add(String item) {
        assureExistence();
        if (array.length == actualSize) {
            grow();
        }
        array[actualSize] = item;
        actualSize++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        assureExistence();
        if (array.length == actualSize) {
            grow();
        }
        if (index == actualSize) {
            array[actualSize] = item;
            actualSize++;
        } else if (index < actualSize) {
            for (int i = actualSize; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = item;
            actualSize++;
        } else {
            throw new RuntimeException();
        }
        return item;
    }

    @Override
    public String set(int index, String item) {
        assureExistence();
        if (index >= actualSize) {
            throw new RuntimeException();
        }
        array[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        assureExistence();
        for (int i = 0; i < actualSize; i++) {
            if (array[i].equals(item)) {
                for (int j = i; j < actualSize - 1; j++) {
                    array[j] = array[j + 1];
                }
                actualSize--;
                return item;
            }
        }
        throw new RuntimeException();
    }

    @Override
    public String remove(int index) {
        assureExistence();
        if (index >= actualSize) {
            throw new RuntimeException();
        }
        String target = array[index];
        for (int i = index; i < actualSize - 1; i++) {
            array[i] = array[i + 1];
        }
        actualSize--;
        return target;
    }

    @Override
    public boolean contains(String item) {
        assureExistence();
        for (String e : array) {
            if (e.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        assureExistence();
        for (int i = 0; i < actualSize; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        assureExistence();
        for (int i = actualSize - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        assureExistence();
        if (index >= actualSize) {
            throw new RuntimeException();
        }
        return array[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(this.array, otherList.toArray());
    }

    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public boolean isEmpty() {
        return actualSize == 0;
    }

    @Override
    public void clear() {
        array = new String[10];
        actualSize = 0;
    }

    @Override
    public String[] toArray() {
        String[] targetArr = new String[actualSize];
        System.arraycopy(array, 0, targetArr, 0, actualSize);
        return targetArr;
    }
}
