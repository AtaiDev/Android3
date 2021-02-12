package com.example.unittesting;

import com.example.unittesting.domain.Math;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MathTest {

    private Math mathTest;

    @Before
    public void setUp() throws Exception {
        mathTest = new Math();
        System.out.println("Before 'setUp'");
    }

    @After
    public void tearDown() throws Exception {
        mathTest = null;
        System.out.println("After 'tearDown'");

    }

    @Test
    public void addFunction() {
        assertEquals(5,mathTest.addFunction(2,3));
        System.out.println("Test 'addFunction'");

    }

    @Test
    public void subtractFunction() {
        assertEquals(5,mathTest.subtractFunction(10,5));
        System.out.println("Test 'addFunction'");

    }

    @Test
    public void multipleFunction(){
        assertEquals(30,mathTest.multiplication(10,3));
        System.out.println("Tested multiplication");
    }


    @Test
    public void divide(){
        assertEquals(15,mathTest.divideFunction(45,3));
        System.out.println("Tested divided");
    }

}