package ru.nikbekhter.simple.store.core.utils;

import lombok.Data;

import java.util.LinkedList;

public class MyQueue<T> {
    private LinkedList<T> list = new LinkedList<>();

    public void enqueue(T item) {
        list.addLast(item);
    }

    public T dequeue() {
        return list.pollFirst();
    }

    public T peek() {
        return list.peekFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
