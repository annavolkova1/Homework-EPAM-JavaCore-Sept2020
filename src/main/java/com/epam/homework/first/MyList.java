package com.epam.homework.first;

import java.lang.reflect.Array;

public class MyList<T> {

  private final T[] myList;
  private int size = 0;
  private int index = 0;
  private static final int DEFAULT_CAPACITY = 16;

  @SuppressWarnings("unchecked")
  public MyList(Class<T> t) {

    myList = (T[]) Array.newInstance(t, DEFAULT_CAPACITY);
  }

  /**
   * Adds an element
   *
   * @return TRUE if the item has been added
   */
  public boolean add(T element) {

    if (myList.length > DEFAULT_CAPACITY) {
      throw new IndexOutOfBoundsException("Size: " + size + ". Your array is full.");
    }

    myList[index] = element;
    size++;
    index++;

    return true;
  }

  /**
   * Removes the item at index
   *
   * @return removed item
   */
  public T remove(int index) {

    if (index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    T oldValue = myList[index];
    int value = size - index - 1;

    if (value > 0) {
      System.arraycopy(myList, index + 1, myList, index, value);
    }
    System.out.println("one " + size);
    myList[--size] = null;
    System.out.println("two " + size);

    return oldValue;
  }

  /**
   * Removes the first occurrence of an element
   *
   * @return index of the removed item
   */
  public int remove(T element) {

    int index = -1;

    for (int i = 0; i < myList.length; i++) {
      if (myList[i].equals(element)) {
        index = i;
        break;
      }
    }

    if (index >= 0) {
      this.remove(index);
    }

    return index;
  }

  /**
   * Copies the FROM collection to the TO collection
   */
  @SuppressWarnings("All")
  public <T> void copy(MyList<? super T> to, MyList<? extends T> from) {

    System.out.println("from size " + from.size);
    for (int i = 0; i < from.getSize(); i++) {
      to.add(from.getByIndex(i));
    }
  }

  /**
   * Retrieving a collection item by index
   */
  public T getByIndex(int index) {

    return this.myList[index];
  }

  /**
   * Retrieving the number of items in a collection
   */
  public int getSize() {

    return size;
  }

  /**
   * Retrieving all elements of a collection
   */
  public T[] getMyList() {

    return myList;
  }
}
