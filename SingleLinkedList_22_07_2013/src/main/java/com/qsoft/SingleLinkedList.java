package com.qsoft;

import java.util.List;

/**
 * User: quynhtq
 * Date: 7/22/13
 * Time: 1:41 PM
 */
public class SingleLinkedList
{
    private LinkedListNode firstNode;
    private int size;

    //constructor
    public SingleLinkedList()
    {

    }

    public SingleLinkedList(List<Object> objectList)
    {
        //To change body of created methods use File | Settings | File Templates.
    }

    //getter and setter
    public LinkedListNode getFirstNode()
    {
        return firstNode;
    }

    public void setFirstNode(LinkedListNode firstNode)
    {
        this.firstNode = firstNode;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }
}
