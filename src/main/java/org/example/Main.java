package org.example;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = generateRandomArray(100000);
        int[] arr2 = new int[100000];
        int[] arr3 = new int[100000];
        System.arraycopy(arr1, 0, arr2, 0, 100000);
        System.arraycopy(arr1, 0, arr3, 0, 100000);
        long start = System.currentTimeMillis();
        bubbleSort(arr1);
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        selectionSort(arr2);
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        insertionSort(arr3);
        System.out.println(System.currentTimeMillis() - start);
    }

    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(2000000);
        }
        return arr;
    }

    public static void swapElements(int[] arr, int idxA, int idxB) {
        int temp = arr[idxA];
        arr[idxA] = arr[idxB];
        arr[idxB] = temp;
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minind = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minind]) {
                    minind = j;
                }
            }
            swapElements(arr, i, minind);
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
