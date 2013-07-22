package com.qsoft;

import org.junit.Before;
import org.junit.Test;

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


}
