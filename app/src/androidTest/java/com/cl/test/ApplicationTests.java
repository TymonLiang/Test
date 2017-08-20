package com.cl.test;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ApplicationTests {

    /**
     * Test package name
     */
    @Test
    public void testPackageName() {
        Context context = InstrumentationRegistry.getTargetContext();
        assertEquals("com.cl.test", context.getPackageName());
    }
}
