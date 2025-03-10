package com.gabrielspassos;

import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;

final class Util {

    /* Known java.vm.name list:
     *
     *   HotSpot:
     *   - Java HotSpot(TM) xx-Bit Server VM
     *   - OpenJDK xx-Bit Server VM
     *
     *   J9:
     *   - Eclipse OpenJ9 VM
     *   - IBM J9 VM
     */
    static private boolean isHotSpotVM(final String name) {
        return name.contains("HotSpot") || name.contains("OpenJDK");
    }
    static private boolean isJ9VM(final String name) {
        return name.contains("Eclipse OpenJ9") || name.contains("IBM J9");
    }

    static private boolean isZing(final String name) {
        return name.startsWith("Zing");
    }

    static final StringHash VALID_STRING_HASH;
    static  {
        StringHash stringHash = null;
        try {
            final String vmName = System.getProperty("java.vm.name");
            if (isHotSpotVM(vmName) || isJ9VM(vmName) || isZing(vmName)) {
                final String javaVersion = System.getProperty("java.version");
                if (javaVersion.compareTo("1.7.0_06") >= 0) {
                    if (javaVersion.compareTo("1.9") >= 0) {
                        // JDK 9+
                        stringHash = ModernCompactStringHash.INSTANCE;
                    } else {
                        // JDK [1.7.0_06, 9)
                        stringHash = ModernHotSpotStringHash.INSTANCE;
                    }
                } else {
                    // JDK [1.7, 1.7.0_06)
                    stringHash = HotSpotPrior7u6StringHash.INSTANCE;
                }
            } else {
                // try to initialize this version anyway
                stringHash = HotSpotPrior7u6StringHash.INSTANCE;
            }
        } catch (final Throwable ignore) {
        } finally {
            if (null == stringHash) {
                VALID_STRING_HASH = UnknownJvmStringHash.INSTANCE;
            } else {
                VALID_STRING_HASH = stringHash;
            }
        }
    }

    static void checkArrayOffs(final int arrayLength, final int off, final int len) {
        if (len < 0 || off < 0 || off + len > arrayLength || off + len < 0)
            throw new IndexOutOfBoundsException();
    }

    static long getDirectBufferAddress(final ByteBuffer buff) {
        return ((DirectBuffer)buff).address();
    }
}
