package com.qsoft;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * User: quynhtq
 * Date: 7/23/13
 * Time: 1:30 PM
 */
public class SingleLinkedListTest
{
    @Test
    public void testInitializeEmptyLinkList()
    {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        assertEquals(singleLinkedList.getSize(),0);
    }

    @Test
    public void testInitializeFromAnArray()
    {
        List<Object> objectList= new ArrayList<Object>();
        objectList.add(new Object());
        objectList.add(new Object());
        objectList.add(new Object());

        SingleLinkedList singleLinkedList = new SingleLinkedList(objectList);
        assertEquals(singleLinkedList.getFirstNode().getValue(),objectList.get(2));
    }

    @Test
    public void testGetSize()
    {
        List<Object> objectList= new ArrayList<Object>();
        objectList.add(new Object());
        objectList.add(new Object());
        objectList.add(new Object());
        SingleLinkedList singleLinkedList = new SingleLinkedList(objectList);

        assertEquals(3,singleLinkedList.getSize());
    }

    @Test
    public void testFindObject()
    {
        List<Object> objectList= new ArrayList<Object>();
        objectList.add(new Object());
        objectList.add(new Object());
        objectList.add(new Object());
        SingleLinkedList singleLinkedList = new SingleLinkedList(objectList);

        assertEquals(singleLinkedList.find(objectList.get(1)),singleLinkedList.getFirstNode().getNextNode());
    }

    @Test
    public void testGetFirstNode()
    {
        List<Object> objectList= new ArrayList<Object>();
        objectList.add(new Object());
        objectList.add(new Object());
        objectList.add(new Object());
        SingleLinkedList singleLinkedList = new SingleLinkedList(objectList);

        assertEquals(singleLinkedList.getFirstNode().getValue(),objectList.get(2));
    }

    @Test
    public void testGetLastNode()
    {
        List<Object> objectList= new ArrayList<Object>();
        objectList.add(new Object());
        objectList.add(new Object());
        objectList.add(new Object());
        SingleLinkedList singleLinkedList = new SingleLinkedList(objectList);

        assertEquals(singleLinkedList.getLastNode().getValue(),objectList.get(0));
    }
}
