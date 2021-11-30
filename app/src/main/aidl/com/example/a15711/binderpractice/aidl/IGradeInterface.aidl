// IGradeInterface.aidl
package com.example.a15711.binderpractice.aidl;

// Declare any non-default types here with import statements

interface IGradeInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
            int getGrade(String name);
}
