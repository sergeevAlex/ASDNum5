package com.lehich;

public class Stack {
        private int mSize;
        private int[] stackArray;
        private int top;

        public Stack(int m) {
            this.mSize = m;
            stackArray = new int[mSize];
            top = -1;
        }

        public void push(int element) {
            stackArray[++top] = element;
        }

        public int pop() {
            return stackArray[top--];
        }

        public int readTop() {
            return stackArray[top];

        }
        public boolean isEmpty() {
            return (top == -1);
        }

        public boolean isFull() {
            return (top == mSize - 1);
        }
    }
