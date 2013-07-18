package com.qsoft;

import java.util.List;

/**
 * User: quynhtq
 * Date: 7/18/13
 * Time: 1:44 PM
 */
public class SingleLinkedList
{
    private LinkedListElement firstElement;
    private int size;

    public SingleLinkedList(List<Object> objectList)
    {
        size = objectList.size();
        for (Object obj : objectList)
        {
            LinkedListElement anElement = new LinkedListElement();
            anElement.setValue(obj);
            anElement.setNextElement(firstElement);
            if (firstElement != null)
            {
                firstElement.previousElement = anElement;
            }
            firstElement = anElement;
        }
    }

    public SingleLinkedList()
    {

    }

    public int size()
    {
        return size;
    }

    public LinkedListElement getFirstElement()
    {
        return firstElement;
    }

    public LinkedListElement find(Object o)
    {
        return null;
    }
}
