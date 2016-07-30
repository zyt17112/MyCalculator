package bupt.ss.mycalculator;

import java.util.EmptyStackException;

/**
 * Created by Administrator on 2016/7/29.
 */
public class MyArrayStack implements MyStack {
    int top;            // current top of stack
    Object[] stack;        // element array

    /**
     * create a stack with the given initial capacity
     *
     * @throws IllegalArgumentException when initialCapacity < 1
     */
    public MyArrayStack(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("initialCapacity must be >= 1");
        }
        stack = new Object[initialCapacity];
        top = -1;
    }

    /**
     * create a stack with initial capacity  10
     */
    public MyArrayStack() {
        this(10);
    }

    /**
     * @return true iff stack is empty
     */
    public boolean empty() {
        return top == -1;
    }

    /**
     * @return top element of stack
     * @ throws EmptyStackException when the stack is empty
     */
    public Object peek() {
        if (empty())
            throw new EmptyStackException();
        return stack[top];
    }

    /**
     * add theElement to the top of the stack
     */
    public void push(Object theElement) {
        // increase array size if necessary
//		if (top == stack.length - 1) {
//
//		}
        stack[++top] = theElement;
    }

    /**
     * remove top element of stack and return it
     *
     * @throws EmptyStackException when the stack is empty
     */
    public Object pop() {
        if (empty())
            throw new EmptyStackException();
        Object topElement = stack[top];
        stack[top--] = null;    //enable garbage collection
        return topElement;
    }
}
