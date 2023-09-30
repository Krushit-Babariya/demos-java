package cz.aloisseckar.java.javademos.java21.j21;

import cz.aloisseckar.java.javademos.commons.IDemo;

import java.math.BigInteger;

public class J21Demo implements IDemo {

    @Override
    public void demo() {
        info("JAVA 21 FEATURES DEMO", "Examples for couple of 'hidden' features \nintroduced until Java 21");

        // 1) Math.divideExact
        // -------------------
        // introduced in Java 18 - https://bugs.openjdk.org/browse/JDK-8270378
        // unlike regular division operator, it throws an exception, when result overflows

        System.out.println("Math.divideExact - new operation for division");
        System.out.println("Simple division - 'Math.divideExact(10, 2)':");
        System.out.println(Math.divideExact(10, 2));
        System.out.println("Traditionally 'Integer.MIN_VALUE / -1' works:");
        System.out.println(Integer.MIN_VALUE / -1); // IDE may show a warning, but Java will process it
        System.out.println("'Math.divideExact(Integer.MIN_VALUE, -1)' will throw an exception:");
        try {
            System.out.println(Math.divideExact(Integer.MIN_VALUE, -1));
        } catch (ArithmeticException ex) {
            System.out.println("ArithmeticException thrown by .divideExact()");
        }
        System.out.println();

        // 2) BigInteger.parallelMultiply
        // ------------------------------
        // introduced in Java 19 - https://bugs.openjdk.org/browse/JDK-8278886
        // faster than traditional BigInteger.multiply, as it utilizes parallel threads to perform the computation
        // it has to be really large numbers to see the effect (due to multi-thread computing overhead)
        // see this article for benchmark: https://www.javaspecialists.eu/archive/Issue305-Contributing-BigInteger.parallelMultiply-to-OpenJDK.html

        System.out.println("BigInteger.parallelMultiply - faster BigInteger multiplication");

        var value = "1234567891";
        System.out.println("values to be multiplied: " + value + " x10000");

        var number1 = new BigInteger(value.repeat(10000));
        var number2 = new BigInteger(value.repeat(10000));

        var timeS1 = System.nanoTime();
        number1.multiply(number2);
        var timeE1 = System.nanoTime() - timeS1;
        System.out.println(".multiply() took " + timeE1 / 1000 + " ms");

        var timeS2 = System.nanoTime();
        number1.parallelMultiply(number2);
        var timeE2 = System.nanoTime() - timeS2;
        System.out.println(".parallelMultiply() took " + timeE2 / 1000 + " ms");
        System.out.println();

        // 3) StringBuilder.repeat
        // -----------------------
        // introduced in Java 21 - https://bugs.openjdk.org/browse/JDK-8302686
        // String.repeat already exists since Java 11
        // this is a more convenient way to use it directly in StringBuilder

        System.out.println("StringBuilder.repeat - more effective string concatenation");
        System.out.println("sb.repeat(\"abc\", 5);");
        StringBuilder sb = new StringBuilder();
        sb.repeat("abc", 5);
        System.out.println(sb);
        System.out.println();

        // 4) Character.isEmoji
        // --------------------
        // introduced in Java 21 - https://bugs.openjdk.org/browse/JDK-8303018
        // detect whether given CharSequence is an emoji or not based on its Unicode code point
        // prior to this, workarounds had to be used for detection (see https://www.baeldung.com/java-check-letter-emoji)

        System.out.println("Character.isEmoji - detecting whether a text contains an emoji");
        var codePoint1 = Character.codePointAt("😃", 0);
        System.out.println("😃 isEmoji? " + Character.isEmoji(codePoint1));
        var codePoint2 = Character.codePointAt(":)", 0);
        System.out.println(":) isEmoji? " + Character.isEmoji(codePoint2));

    }
}