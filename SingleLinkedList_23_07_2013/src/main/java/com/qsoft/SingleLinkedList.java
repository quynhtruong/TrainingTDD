package com.qsoft;

import java.util.List;

/**
 * User: quynhtq
 * Date: 7/23/13
 * Time: 1:32 PM
 */
public class SingleLinkedList
{
    private LinkedListNode firstNode;

    //constructor
    public SingleLinkedList()
    {

    }
    public SingleLinkedList(List<Object> objectArrayList)
    {

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

    //other methods
    public int getSize()
    {
        return 0;
    }

}
