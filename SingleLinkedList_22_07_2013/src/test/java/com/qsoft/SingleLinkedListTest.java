package com.qsoft;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * User: quynhtq
 * Date: 7/22/13
 * Time: 1:38 PM
 */
public class SingleLinkedListTest
{
    @Test
    public void testLinkedListWithEmpty()
    {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        assertEquals(0,singleLinkedList.size());
    }
}
