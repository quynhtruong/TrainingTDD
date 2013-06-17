package com.qsoft;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/17/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class StackTest {
    @Test
    public void testSize(){
        Stack stack= new Stack();
        assertEquals(0,stack.getSize());
    }
    @Test
    public void testSizeAfterPushOneElement(){
        Stack stack = new Stack();
        stack.push("abc");
        assertEquals(1,stack.getSize());
    }
    @Test
    public void testWithExistingKElementAndAfterPush(){
        String[] strings = {"a","b","c"};
        Stack stack = new Stack(strings);
        stack.push("d");
        assertEquals(4,stack.getSize());
    }
    @Test
    public void  testTopNullWithEmptyStack(){
        Stack stack = new Stack();
        assertEquals(null,stack.getTop());
    }
    @Test
    public void testTopAfterPushOneElement(){
        Stack stack = new Stack();
        stack.push("abc");
        assertEquals("abc",stack.getTop());
    }
    @Test
    public void testPopEmptyStack(){
        Stack stack =  new Stack();
        try {
            stack.pop();
            fail();
        }
        catch (StackEmptyException e){
        }
    }
    @Test
    public void testWithPopAfterKElementsExisting() throws StackEmptyException {
        String[] strings = {"A","B","C"};
        Stack stack = new Stack(strings);
        stack.pop();
        assertEquals(2,stack.getSize());
    }
    @Test
    public void testWithStackHasOneElementAndPop() throws StackEmptyException {
        String strings[] = {"a"};
        Stack stack = new Stack(strings);
        stack.pop();
        assertEquals(0,stack.getSize());
    }
    @Test
    public void testTopAndPop() throws StackEmptyException {
        String strings[] = {"a","b","c","d"};
        Stack stack = new Stack(strings);
        assertEquals("d",stack.getTop());
        assertEquals("d",stack.pop());
    }
    @Test
    public void testWithPushKElementAndPop() throws StackEmptyException {
        Stack stack = new Stack();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        assertEquals("d",stack.pop());
        assertEquals("c",stack.pop());
        assertEquals("b",stack.pop());
        assertEquals("a",stack.pop());
        assertEquals(0,stack.getSize());
    }
}

