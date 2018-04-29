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
        StringBuffer result = new StringBuffer();
        result.append(getModificators(clazz.getModifiers()) +
                "class " + clazz.getSimpleName() +
                " {\n");

        for (Field m : clazz.getDeclaredFields())
            result.append(getModificators(m.getModifiers()) +
                    m.getType().getSimpleName() + " " +
                    m.getName() + ";\n");

        if (clazz.getDeclaredFields().length > 0)
            result.append('\n');

        for (Constructor m : clazz.getDeclaredConstructors()) {
            result.append(getModificators(m.getModifiers()) +
                    clazz.getSimpleName() +
                    "(" + getTypes(m) + ")");
            if (m.getExceptionTypes().length > 0)
                result.append(" throws " + getExceptionsTypes(m) + ";\n");
            else
                result.append(";\n");
        }

        if (clazz.getDeclaredConstructors().length > 0)
            result.append('\n');

        for (Method m : clazz.getDeclaredMethods()) {
            result.append(getModificators(m.getModifiers()) +
                    m.getReturnType().getSimpleName() + " "
                    + m.getName() +
                    "(" + getTypes(m) + ")");
            if (m.getExceptionTypes().length > 0)
                result.append(" throws " + getExceptionsTypes(m) + ";\n");
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
    private static String getModificators(int mods) {
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
