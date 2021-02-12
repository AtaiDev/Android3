package com.example.unittesting;

import android.content.Context;

import com.example.unittesting.ui.UriHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class UriHelperTest {

    private UriHelper uriHelper;
    private Context mockContext;
    @Before
    public void setUp(){
        mockContext = Mockito.mock(Context.class);
        Mockito.when(mockContext.getString(R.string.url)).thenReturn("URL");
        Mockito.when(mockContext.getString(R.string.path)).thenReturn("PATH");
        Mockito.when(mockContext.getString(R.string.unknown)).thenReturn("UNKNOWN");
        uriHelper = new UriHelper(mockContext);
    }

    @Test
    public void validate(){
//        System.out.println(uriHelper.validate("https://docs.google.com"));
        System.out.println(uriHelper.validate("file://tmp/docs.google.txt"));
    }

    @After
    public void clearAll(){

    }



}
