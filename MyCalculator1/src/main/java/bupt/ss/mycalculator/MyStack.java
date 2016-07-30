package bupt.ss.mycalculator;

/**
 * Created by Administrator on 2016/7/29.
 */
public interface MyStack {
    
    boolean empty();
    Object peek();
    void push(Object theObject);
    Object pop();
}
