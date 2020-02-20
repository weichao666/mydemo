package servicecomb.springmvcserverc.java.training.designpattern.proxy.cglibproxy;

import java.util.Arrays;

public class ArraySort2 {
    public void quickSort(int[] arr) {
        Arrays.sort(arr);
        System.out.println("快速排序执行完毕");
    }
    public void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            //找出最小值的元素下标
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
        System.out.println("选择排序执行完毕");
    }
    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                int temp = 0;
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println("冒泡排序执行完毕");
    }
 }
