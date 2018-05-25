package sp;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static sp.SecondPartTasks.*;

public class SecondPartTasksTest {
    @Test
    public void testFindQuotes() {
        assertEquals(
                Arrays.asList("sfknfasjknfabcsklfadsnkl", "abc"),
                findQuotes(Arrays.asList("sfknfasjknfabcsklfadsnkl", "ab c", "abc", "fadsfsadfsfdsa", "adsafsabfc"), "abc")
        );
    }

    @Test
    public void testPiDividedBy4() {
        assertTrue(abs(piDividedBy4() - PI / 4) < 0.1);
    }

    @Test
    public void testFindPrinter() {
        Map<String, List<String>> compositions = new TreeMap();
        compositions.put(
                "SecondA",
                Arrays.asList("1", "2", "3")
        );
        compositions.put(
                "FirstA",
                Arrays.asList("1", "2", "3", "4", "5")
        );
        assertEquals(findPrinter(compositions), "FirstA");
//pridumat' chto to notmal'noe
        Map<String, List<String>> emptyMap = new TreeMap();
        assertEquals(findPrinter(emptyMap), "");
    }

    @Test
    public void testCalculateGlobalOrder() {
        assertEquals(
        ImmutableMap.of(
                "Apple", 150,
                "Pear", 95,
                "Orange", 75,
                "Strawberry", 10),

        calculateGlobalOrder(
        Arrays.asList(ImmutableMap.of(
                "Apple", 100,
                "Orange", 75,
                "Pear", 75),
        ImmutableMap.of(
                "Apple", 50,
                "Pear", 20,
                "Strawberry", 10))));
    }
}