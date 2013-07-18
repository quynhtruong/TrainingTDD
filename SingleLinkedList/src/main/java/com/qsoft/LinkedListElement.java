package com.qsoft;

/**
 * User: quynhtq
 * Date: 7/18/13
 * Time: 1:50 PM
 */
public class LinkedListElement
{
    private Object value;
    private LinkedListElement nextElement;

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public LinkedListElement getNextElement()
    {
        return nextElement;
    }

    public void setNextElement(LinkedListElement nextElement)
    {
        this.nextElement = nextElement;
    }

    public LinkedListElement getPreviousElement()
    {
        return previousElement;
    }

    public void setPreviousElement(LinkedListElement previousElement)
    {
        this.previousElement = previousElement;
    }

    public LinkedListElement previousElement;

}
