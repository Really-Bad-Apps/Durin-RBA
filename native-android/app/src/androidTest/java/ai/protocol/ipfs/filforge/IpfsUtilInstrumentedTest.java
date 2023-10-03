package ai.protocol.ipfs.filforge;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import util.IpfsUtilsKt;
import util.Node;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class IpfsUtilInstrumentedTest {

    private Node node;

    @Before
    public void setUp() {
        node = new Node("dweb.link", true, true, true, null, null);
    }


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("ai.protocol.ipfs.filforge", appContext.getPackageName());
    }

    @Test
    public void testTransformCidV0() {
        String input = "QmPChd2hVbrJ6bfo3WBcTW4iZnpHm8TEzWkLHmLpXhF68A";
        String expected = "https://dweb.link/ipfs/QmPChd2hVbrJ6bfo3WBcTW4iZnpHm8TEzWkLHmLpXhF68A";
        String outputUrl = IpfsUtilsKt.transform(input, node);
        assertEquals("transformation failed", expected, outputUrl);
    }

    @Test
    public void testTransformCidV1() {
        String input = "bafybeiczsscdsbs7ffqz55asqdf3smv6klcw3gofszvwlyarci47bgf354";
        String expected = "https://bafybeiczsscdsbs7ffqz55asqdf3smv6klcw3gofszvwlyarci47bgf354.ipfs.dweb.link/";
        String outputUrl = IpfsUtilsKt.transform(input, node);
        assertEquals("transformation failed", expected, outputUrl);
    }

    @Test
    public void testTransformHttpUrl() {
        String input = "http://foobar.com";
        String expected = "http://foobar.com";
        String outputUrl = IpfsUtilsKt.transform(input, node);
        assertEquals("transformation failed", expected, outputUrl);
    }
}