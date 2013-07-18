package com.qsoft;

import org.junit.Test;

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
}
