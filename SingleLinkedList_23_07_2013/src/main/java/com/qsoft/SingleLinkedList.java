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
        for (Object object : objectArrayList)
        {
            LinkedListNode newNode = new LinkedListNode();
            newNode.setValue(object);
            ;
            newNode.setNextNode(firstNode);
            firstNode = newNode;
        }
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
        int result = firstNode!=null?1:0;
        for (LinkedListNode linkedListNode = firstNode; linkedListNode.getNextNode() != null; linkedListNode = linkedListNode.getNextNode())
        {
            result++;
        }
        return result;
    }

    public LinkedListNode find(Object object)
    {
        for (LinkedListNode linkedListNode = firstNode; linkedListNode.getNextNode() != null; linkedListNode = linkedListNode.getNextNode())
        {
            if (linkedListNode.getValue().equals(object))
                return linkedListNode;
        }
        return null;
    }
}
