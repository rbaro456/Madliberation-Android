package com.madliberationfestival.robertbarbaro.madliberation;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented activity_artist_info, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under activity_artist_info.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.madliberationfestival.robertbarbaro.madliberation", appContext.getPackageName());
    }
}
