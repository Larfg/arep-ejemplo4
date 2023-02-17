package edu.escuelaing.app;

import java.lang.reflect.Method;

public class RunTests {
    public static void main(String[] args) {
        int passed = 0, failed = 0;

        try {
            for (Method m : Class.forName(args[0]).getMethods()) {
                if (m.isAnnotationPresent(Test.class)) {
                    try {
                        m.invoke(null);
                        passed++;
                    } catch (Throwable ex) {
                        System.out.printf("Test %s failed: %s %n", m, ex.getCause());
                        failed++;
                    }
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.printf("Passed: %d, Failed %d%n", passed, failed);
    }
}
