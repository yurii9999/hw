package group144.kuzmin;

import java.lang.reflect.*;
import java.util.Arrays;

public class ClassLister {
    /**
     * Method generate string of class's describing
     *
     * @param clazz class you want to describe
     * @return string of class's describing
     */
    public static String listing(Class clazz) {
        StringBuilder result = new StringBuilder();
        result.append(getModifiers(clazz.getModifiers()))
                .append("class ")
                .append(clazz.getSimpleName())
                .append(" {\n");

        for (Field m : clazz.getDeclaredFields())
            result.append(getModifiers(m.getModifiers()))
                    .append(m.getType().getSimpleName())
                    .append(" ")
                    .append(m.getName())
                    .append(";\n");

        if (clazz.getDeclaredFields().length > 0)
            result.append('\n');

        for (Constructor m : clazz.getDeclaredConstructors()) {
            result.append(getModifiers(m.getModifiers()))
                    .append(clazz.getSimpleName())
                    .append("(")
                    .append(getTypes(m))
                    .append(")");

            if (m.getExceptionTypes().length > 0)
                result.append(" throws ")
                        .append(getExceptionsTypes(m))
                        .append(";\n");
            else
                result.append(";\n");
        }

        if (clazz.getDeclaredConstructors().length > 0)
            result.append('\n');

        for (Method m : clazz.getDeclaredMethods()) {
            result.append(getModifiers(m.getModifiers()))
                    .append(m.getReturnType().getSimpleName())
                    .append(" ")
                    .append(m.getName())
                    .append("(")
                    .append(getTypes(m))
                    .append(")");

            if (m.getExceptionTypes().length > 0)
                result.append(" throws ")
                        .append(getExceptionsTypes(m))
                        .append(";\n");
            else
                result.append(";\n");
        }

        if (clazz.getDeclaredMethods().length > 0 && clazz.getDeclaredClasses().length > 0)
            result.append('\n');

        for (Class subClass : clazz.getDeclaredClasses())
            result.append(listing(subClass));

        result.append("}\n");
        return result.toString();
    }

    /**
     * Method generate string of all modifiers
     *
     * @param mods member.getModifiers or class.getModifiers
     * @return string with all member's (class) modifiers
     */
    private static String getModifiers(int mods) {
        StringBuffer result = new StringBuffer();

        if (Modifier.isPublic(mods))
            result.append("public ");
        if (Modifier.isPrivate(mods))
            result.append("private ");
        if (Modifier.isProtected(mods))
            result.append("protected ");
        if (Modifier.isFinal(mods))
            result.append("final ");
        if (Modifier.isStatic(mods))
            result.append("static ");
        if (Modifier.isAbstract(mods))
            result.append("abstract ");
        if (Modifier.isInterface(mods))
            result.append("interface ");
        if (Modifier.isSynchronized(mods))
            result.append("synchronized");
        if (Modifier.isNative(mods))
            result.append("native ");
        if (Modifier.isStrict(mods))
            result.append("strict ");
        if (Modifier.isTransient(mods))
            result.append("transient ");
        if (Modifier.isVolatile(mods))
            result.append("volatile ");

        return result.toString();
    }

    /**
     * Method generate string of all param's types of constructor or method
     *
     * @param executable constructor or method
     * @return string with all param's types of constructor or method
     */
    private static String getTypes(Executable executable) {
        return Arrays.stream(executable.getParameterTypes())
                .map(clazz -> clazz.getSimpleName())
                .reduce((s1, s2) -> s1 + ", " + s2)
                .orElse("");
    }

    /**
     * Method generate string of all exceptions' types of constructor or method
     *
     * @param executable constructor or method
     * @return string with all exceptions' types of constructor or method
     */
    private static String getExceptionsTypes(Executable executable) {
        return Arrays.stream(executable.getExceptionTypes())
                .map(clazz -> clazz.getSimpleName())
                .reduce((s1, s2) -> s1 + ", " + s2)
                .orElse("");
    }
}
