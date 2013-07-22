package com.qsoft;

/**
 * User: quynhtq
 * Date: 7/22/13
 * Time: 1:44 PM
 */
public class LinkedListNode
{
    private Object value;
    private LinkedListNode previousNode;
    private LinkedListNode nextNode;

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public LinkedListNode getPreviousNode()
    {
        return previousNode;
    }

    public void setPreviousNode(LinkedListNode previousNode)
    {
        this.previousNode = previousNode;
    }

    public LinkedListNode getNextNode()
    {
        return nextNode;
    }

    public void setNextNode(LinkedListNode nextNode)
    {
        this.nextNode = nextNode;
    }
}
