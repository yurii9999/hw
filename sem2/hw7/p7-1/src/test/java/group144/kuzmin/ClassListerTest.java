package group144.kuzmin;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import static org.junit.Assert.assertEquals;

public class ClassListerTest {
    @Test
    public void listingTest() throws MalformedURLException, ClassNotFoundException {
        ClassLoader cl = new URLClassLoader(new URL[]{new URL("file://.")});
        Class c = cl.loadClass("com.group144.kuzmin.Expression");

        assertEquals(
                "public class Expression {\n" +
                        "private Node root;\n" +
                        "\n" +
                        "public Expression(String);\n" +
                        "\n" +
                        "public String toString();\n" +
                        "public int calculate();\n" +
                        "\n" +
                        "private static class Convertor {\n" +
                        "private Convertor();\n" +
                        "\n" +
                        "public static String convert(String);\n" +
                        "}\n" +
                        "}\n" ,
                ClassLister.listing(c)
        );
    }
}