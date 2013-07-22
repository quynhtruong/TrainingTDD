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

    //constructor
    public SingleLinkedList()
    {

    }

    public SingleLinkedList(List<Object> objectList)
    {
        for(Object obj: objectList)
        {
            LinkedListNode listNode = new LinkedListNode();
            listNode.setValue(obj);
            listNode.setNextNode(firstNode);
            firstNode = listNode;
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

    public int getSize()
    {
        int result = 0;
        for(LinkedListNode node = firstNode;node!=null;node=node.getNextNode())
            result++;
        return result;
    }
}
