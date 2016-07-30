package bupt.ss.mycalculator;

/**
 * Created by Administrator on 2016/7/29.
 */
public interface MyStack {
    
    public boolean empty();
    public Object peek();
    public void push(Object theObject);
    public Object pop();
}
