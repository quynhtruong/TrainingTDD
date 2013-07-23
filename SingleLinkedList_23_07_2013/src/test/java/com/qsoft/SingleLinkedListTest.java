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
        assertEquals(singleLinkedList.getFirstNode(),objectList.get(2));
    }
}
