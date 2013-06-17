package com.qsoft;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/17/13
 * Time: 1:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class Stack {
    String [] list = new String[100];
    int top = 0;

    public Stack(String[] strings) {
        for(int i=0;i<strings.length;i++)
            push(strings[i]);
    }

    public Stack() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public int getSize(){
        return top;
    }

    public void push(String element) {
        list[top] = element;
        top++;
    }

    public String getTop() {
        if (top==0)
            return null;
        return list[top-1];
    }

    public String pop() throws StackEmptyException {
        if (top==0)
            throw  new StackEmptyException();
        top--;
        return list[top];
    }
}
