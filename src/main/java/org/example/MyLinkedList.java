package org.example;

import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {

    static class Node<E>  {
        E data;

        public Node(E data) {
            this.data = data;
        }

        Node<E> next;
    }

    private Node<T> head;

    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void remove(T value) {
        if (head == null) {
            return;
        }

        if ((head.data == null && value == null) || (head.data != null && head.data.equals(value))) {
            head = head.next;
            return;
        }

        Node<T> temp = head;
        while (temp.next != null) {
            if ((temp.next.data != null && temp.next.data.equals(value)) ||
                    (temp.next.data == null && value == null)) {
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
    }

    public void print() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }
    public void bubbleSort() {
        if (head == null || head.next == null) {
            return;
        }

        boolean swapped = true;
        while (swapped) {
            swapped = false;
            Node<T> current = head;
            Node<T> prev = null;

            while (current != null && current.next != null) {
                Book currentBook = (Book) current.data;
                Book nextBook = (Book) current.next.data;

                if (currentBook.getPublicationYear() < nextBook.getPublicationYear()) {
                    if (prev == null) {
                        head = current.next;
                    } else {
                        prev.next = current.next;
                    }
                    Node<T> temp = current.next;
                    current.next = temp.next;
                    temp.next = current;

                    swapped = true;
                }

                prev = current;
                current = current.next;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
    public boolean contains(T value) {
        Node<T> current = head;
        while (current != null) {
            if (value == null) {
                if (current.data == null) {
                    return true;
                }
            } else {
                if (value.equals(current.data)) {
                    return true;
                }
            }
            current = current.next;
        }

        return false;
    }
    public int size() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative");
        }

        Node<T> current = head;
        int currentIndex = 0;

        while (current != null) {
            if (currentIndex == index) {
                return current.data;
            }
            current = current.next;
            currentIndex++;
        }

        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + currentIndex);
    }
}
