package edu.gatech.seclass;

/**
 * This is a Georgia Tech provided code example for use in assigned
 * private GT repositories. Students and other users of this template
 * code are advised not to share it with other students or to make it
 * available on publicly viewable websites including repositories such
 * as Github and Gitlab.  Such sharing may be investigated as a GT
 * honor code violation. Created for CS6300.
 *
 * Template provided for the White-Box Testing Assignment. Follow the
 * assignment directions to either implement or provide comments for
 * the appropriate methods.
 */

public class ProblemClass {

    public static void exampleMethod1(int a) {
        // ...
        int x = a / 0; // Example of instruction that makes the method
                       // fail with an ArithmeticException
        // ...
    }

    public static int exampleMethod2(int a, int b) {
        // ...
        return (a + b) / 0; // Example of instruction that makes the
                            // method fail with an ArithmeticException
    }

    public static void exampleMethod3() {
        // NOT POSSIBLE: This method cannot be implemented because
        // <REPLACE WITH REASON> (this is the example format for a
        // method that is not possible) ***/
    }

    public static int problemMethod1(int a, int b, boolean invertDivisor) { // Change the signature as needed
        // Either add a comment in the format provided above or
        // implement the method.
        if(invertDivisor){
            return b/a;
        }
        return a/b;
    }

    public static int problemMethod2(int a, int b, boolean doOperations) { // Change the signature as needed
        // Either add a comment in the format provided above or
        // implement the method.
        if(doOperations){
            return (a + b)/(a - b);
        }
        return a/b;

    }

    public static int problemMethod3(int a, int b) { // Change the signature as needed
        // Either add a comment in the format provided above or
        // implement the method.
        int divisor = 0;
        if(a >= b){
            divisor = a/b;
        }
        if(divisor == 1){
            divisor = 0;
        }
        return (b/a)/divisor;
    }

    public static int problemMethod4(int a, int b, boolean invertOps) { // Change the signature as needed
        // Either add a comment in the format provided above or
        // implement the method.
        if(invertOps){
            b = b/a;
        }
        return a/b;
    }

    /*Table Results Analysis:
    Case 1: a = T, b = T
    * x = 1, y = 0
    * x = 0
    * y = 4
    * 4/(0-4) = 4/-4 = -1>=0 => false
    * Case 2: a = T, b = F
    * x = 2, y = 0
    x = 0
    y = -5
    4/(0+5)>=0 +> true
    * Case 3: a = F, b = T
    x = 2, y = 0
    x = 2*2 = 4
    y = 4
    4/(4-4)>=0 => Error
    Case 4: a = F, b = F
    x = 2, y = 0
    x = 4
    y = -5
    4/(4+5) = 4/9 = 0 = > true
    * */

    public static String[] problemMethod5() {
        String a[] = new String[7];
        /*
        public static boolean providedProblemMethod (boolean a, boolean b) {
            int x = 2;
            int y = 0;
            if(a)
                x = y;
            else
                x = 2*x;
            if(b)
                y = 4;
            else
                y -= 5;
            return ((4/(x-y))>= 0);
        }
        */
        //
        // Replace the "?" in column "output" with "T", "F", or "E":
        //
        //         | a | b |output|
        //         ================
        a[0] =  /* | T | T | <T, F, or E> (e.g., "T") */ "F";
        a[1] =  /* | T | F | <T, F, or E> (e.g., "T") */ "T";
        a[2] =  /* | F | T | <T, F, or E> (e.g., "T") */ "E";
        a[3] =  /* | F | F | <T, F, or E> (e.g., "T") */ "T";
        // ================
        //
        // Replace the "?" in the following sentences with "NEVER",
        // "SOMETIMES" or "ALWAYS":
        //
        a[4] = /* Test suites with 100% statement coverage */ "SOMETIMES";
               /*reveal the fault in this method.*/
        a[5] = /* Test suites with 100% branch coverage */ "SOMETIMES";
               /*reveal the fault in this method.*/
        a[6] =  /* Test suites with 100% path coverage */ "ALWAYS";
                /*reveal the fault in this method.*/
        // ================
        return a;
    }
}
