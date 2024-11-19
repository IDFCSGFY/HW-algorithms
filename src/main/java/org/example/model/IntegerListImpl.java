package org.example.model;

import org.example.exception.ElementNotFoundException;
import org.example.exception.NullArgumentGivenException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    Integer[] array;
    int actualSize;

    public IntegerListImpl() {
        this.array = new Integer[10];
        this.actualSize = 0;
    }

    public IntegerListImpl(int initialSize) {
        this.array = new Integer[initialSize];
        this.actualSize = 0;
    }

    private void assureExistence() {
        if (this.array == null) {
            this.array = new Integer[10];
            this.actualSize = 0;
        }
    }

    private void grow() {
        Integer[] newarr = new Integer[(int) (actualSize * 1.5)];
        if (actualSize >= 0) System.arraycopy(array, 0, newarr, 0, actualSize);
        this.array = newarr;
    }

    @Override
    public Integer add(Integer item) {
        assureExistence();
        if (item == null) {
            throw new NullArgumentGivenException();
        }
        if (array.length == actualSize) {
            grow();
        }
        array[actualSize] = item;
        actualSize++;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        assureExistence();
        if (item == null) {
            throw new NullArgumentGivenException();
        }
        if (array.length == actualSize) {
            grow();
        }
        if (index > actualSize) {
            throw new IndexOutOfBoundsException();
        } else if (index == actualSize) {
            array[actualSize] = item;
            actualSize++;
            return item;
        }

        for (int i = actualSize; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = item;
        actualSize++;

        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        assureExistence();
        if (item == null) {
            throw new NullArgumentGivenException();
        }
        if (index >= actualSize) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        assureExistence();
        if (item == null) {
            throw new NullArgumentGivenException();
        }
        for (int i = 0; i < actualSize; i++) {
            if (array[i].equals(item)) {
                for (int j = i; j < actualSize - 1; j++) {
                    array[j] = array[j + 1];
                }
                actualSize--;
                return item;
            }
        }
        throw new ElementNotFoundException();
    }

    @Override
    public Integer remove(int index) {
        assureExistence();
        if (index >= actualSize) {
            throw new IndexOutOfBoundsException();
        }
        Integer target = array[index];
        for (int i = index; i < actualSize - 1; i++) {
            array[i] = array[i + 1];
        }
        actualSize--;
        return target;
    }

    @Override
    public boolean contains(Integer item) {
        assureExistence();
//        if (item == null) {
//            throw new NullArgumentGivenException();
//        }
//        for (int i = 0; i < actualSize; i++) {
//            if (array[i].equals(item)) {
//                return true;
//            }
//        }
//        return false;
        quickSort(array,0, actualSize - 1);
        return binarySearch(array, 0, actualSize - 1, item) != -1;
    }

    @Override
    public int indexOf(Integer item) {
        assureExistence();
//        if (item == null) {
//            throw new NullArgumentGivenException();
//        }
//        for (int i = 0; i < actualSize; i++) {
//            if (array[i].equals(item)) {
//                return i;
//            }
//        }
//        return -1;
        quickSort(array, 0, actualSize - 1);
        return binarySearch(toArray(), 0, actualSize - 1, item);
    }

    @Override
    public int lastIndexOf(Integer item) {
        assureExistence();
        if (item == null) {
            throw new NullArgumentGivenException();
        }
        for (int i = actualSize - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        assureExistence();
        if (index >= actualSize) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new NullArgumentGivenException();
        }
        return Arrays.equals(toArray(), otherList.toArray());
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
        array = new Integer[10];
        actualSize = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] targetArr = new Integer[actualSize];
        System.arraycopy(array, 0, targetArr, 0, actualSize);
        return targetArr;
    }

    private void insertionSort() {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1] >= temp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
    }

    private int binarySearch(Integer[] arr, int iStart, int iEnd, int value) {
        if (iStart > iEnd) {
            return -1;
        }
        int observedIndex = (iStart + iEnd) / 2;
        if (arr[observedIndex] == value) {
            return observedIndex;
        } else if (value < arr[observedIndex]) {
            return binarySearch(arr, iStart, observedIndex - 1, value);
        } else {
            return binarySearch(arr, observedIndex + 1, iEnd, value);
        }
    }

    public static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
