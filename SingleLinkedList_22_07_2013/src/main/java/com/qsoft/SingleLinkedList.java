package com.qsoft;

/**
 * User: quynhtq
 * Date: 7/22/13
 * Time: 1:41 PM
 */
public class SingleLinkedList
{
    private LinkedListNode firstNode;
    private int size;

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
