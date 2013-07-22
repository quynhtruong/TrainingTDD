package com.qsoft;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * User: quynhtq
 * Date: 7/22/13
 * Time: 1:38 PM
 */
public class SingleLinkedListTest
{
    public SingleLinkedList singleLinkedList;
    @Before
    public void setUp()
    {
        singleLinkedList = null;
        singleLinkedList = new SingleLinkedList();
    }

    @Test
    public void testInitializeLinkedListByNothing()
    {
        assertEquals(singleLinkedList.getSize(),0);
    }

    @Test
    public void testInitializeFromAnArrayOfObject()
    {
        List<Object> objectList = new ArrayList<Object>();
        objectList.add(new Object());
        objectList.add(new Object());
        objectList.add(new Object());
        singleLinkedList = new SingleLinkedList(objectList);

        assertEquals(3,singleLinkedList.getSize());
        assertEquals(singleLinkedList.getFirstNode().getValue(),objectList.get(2));
        assertEquals(singleLinkedList.getFirstNode().getNextNode().getValue(),objectList.get(1));
    }

    @Test
    public void testFindAndObjectWhichIsAlreadyInTheList()
    {
        List<Object> objectList = new ArrayList<Object>();
        objectList.add(new Object());
        objectList.add(new Object());
        objectList.add(new Object());
        singleLinkedList = new SingleLinkedList(objectList);

        assertEquals(singleLinkedList.find(objectList.get(2)),singleLinkedList.getFirstNode());
        assertEquals(singleLinkedList.find(objectList.get(2)),singleLinkedList.getFirstNode());
        assertEquals(singleLinkedList.find(objectList.get(2)),singleLinkedList.getFirstNode());
    }
}
