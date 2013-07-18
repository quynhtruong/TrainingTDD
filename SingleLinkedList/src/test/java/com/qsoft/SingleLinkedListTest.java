package com.qsoft;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * User: quynhtq
 * Date: 7/18/13
 * Time: 1:39 PM
 */
public class SingleLinkedListTest
{
    @Test
    public void testCreateASingleLinkedListWithNoElement()
    {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        assertEquals(0,singleLinkedList.size());
    }

    @Test
    public void testInitializeASingleLinkedListByAnArray()
    {
        List<Object> objectList = new ArrayList<Object>();
        objectList.add(new Object());
        objectList.add(new Object());
        objectList.add(new Object());

        SingleLinkedList singleLinkedList = new SingleLinkedList(objectList);
        assertEquals(singleLinkedList.getFirstElement().getValue(),objectList.get(2));
        assertEquals(singleLinkedList.getFirstElement().getNextElement().getValue(),objectList.get(1));
    }

    @Test
    public void testGetSize()
    {
        List<Object> objectList = new ArrayList<Object>();
        objectList.add(new Object());
        objectList.add(new Object());
        objectList.add(new Object());

        SingleLinkedList singleLinkedList = new SingleLinkedList(objectList);
        assertEquals(3,singleLinkedList.size());
    }



}
