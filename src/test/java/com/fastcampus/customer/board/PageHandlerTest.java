package com.fastcampus.customer.board;

import org.junit.Test;

import static org.junit.Assert.*;

public class PageHandlerTest {

    @Test
    public void test() throws Exception{
        PageHandler ph = new PageHandler(250, 1);
        assertTrue(ph.getBeginPage() == 1);
        assertTrue(ph.getEndPage() == 10);
        ph.print();
    }
    @Test
    public void test2() throws Exception{
        PageHandler ph = new PageHandler(250, 11);
        assertTrue(ph.getBeginPage() == 11);
        assertTrue(ph.getEndPage() == 20);
        ph.print();
    }
    @Test
    public void test3() throws Exception{
        PageHandler ph = new PageHandler(250, 25);
        assertTrue(ph.getBeginPage() == 21);
        assertTrue(ph.getEndPage() == 25);
        ph.print();
    }

}