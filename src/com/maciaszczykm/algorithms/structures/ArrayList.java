package com.maciaszczykm.algorithms.structures;

import java.util.Arrays;

public class ArrayList<T> {

  private static final int INITIAL_SIZE = 1024;
  private int size = 0;
  private T[] array = (T[]) new Object[INITIAL_SIZE];

  public boolean add(T value) {
    return add(size, value);
  }

  public boolean add(int index, T value) {
    if (size >= array.length) {
      grow();
    }

    if (index == size) {
      array[size] = value;
    } else {
      System.arraycopy(array, index, array, index + 1, size - index);
      array[index] = value;
    }

    size++;
    return true;
  }

  private void grow() {
    int newSize = size + (size << 1);
    array = Arrays.copyOf(array, newSize);
  }

  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("[");
    for (int i = 0; i < size; i++) {
      stringBuilder.append(array[i]);

      if(i != size - 1) {
        stringBuilder.append(", ");
      }
    }
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
}
