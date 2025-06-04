package com.gabrielspassos;

public final class XXHashFactory {

    private static XXHashFactory instance(String impl) {
        try {
            return new XXHashFactory();
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    private static XXHashFactory NATIVE_INSTANCE, JAVA_UNSAFE_INSTANCE, JAVA_SAFE_INSTANCE;

    public static synchronized XXHashFactory nativeInstance() {
        if (NATIVE_INSTANCE == null) {
            NATIVE_INSTANCE = instance("JNI");
        }
        return NATIVE_INSTANCE;
    }

    public static synchronized XXHashFactory safeInstance() {
        if (JAVA_SAFE_INSTANCE == null) {
            JAVA_SAFE_INSTANCE = instance("JavaSafe");
        }
        return JAVA_SAFE_INSTANCE;
    }

    public static synchronized XXHashFactory unsafeInstance() {
        if (JAVA_UNSAFE_INSTANCE == null) {
            JAVA_UNSAFE_INSTANCE = instance("JavaUnsafe");
        }
        return JAVA_UNSAFE_INSTANCE;
    }

    public static XXHashFactory fastestJavaInstance() {
        if (Utils.isUnalignedAccessAllowed()) {
            try {
                return unsafeInstance();
            } catch (Throwable t) {
                return safeInstance();
            }
        } else {
            return safeInstance();
        }
    }

}