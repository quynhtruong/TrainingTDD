package com.qsoft;

import org.junit.Test;

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
}
