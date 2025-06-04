package com.gabrielspassos;

import sun.nio.ch.DirectBuffer;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static com.gabrielspassos.CharSequenceAccess.nativeCharSequenceAccess;
import static com.gabrielspassos.UnsafeAccess.BOOLEAN_BASE;
import static com.gabrielspassos.UnsafeAccess.BYTE_BASE;
import static com.gabrielspassos.UnsafeAccess.CHAR_BASE;
import static com.gabrielspassos.UnsafeAccess.FALSE_BYTE_VALUE;
import static com.gabrielspassos.UnsafeAccess.INT_BASE;
import static com.gabrielspassos.UnsafeAccess.LONG_BASE;
import static com.gabrielspassos.UnsafeAccess.SHORT_BASE;
import static com.gabrielspassos.UnsafeAccess.TRUE_BYTE_VALUE;
import static com.gabrielspassos.Util.VALID_STRING_HASH;
import static com.gabrielspassos.Util.checkArrayOffs;

public abstract class LongHashFunction implements Serializable {
    private static final long serialVersionUID = 0L;

    /**
     * Returns a {@code LongHashFunction} that implements the
     * <a href="https://github.com/google/cityhash/blob/8af9b8c2b889d80c22d6bc26ba0df1afb79a30db/src/city.cc">
     * CityHash64 algorithm, version 1.1</a> without seed values. This implementation produce
     * equal results for equal input on platforms with different {@link ByteOrder}, but is slower
     * on big-endian platforms than on little-endian.
     *
     * @return a {@code LongHashFunction} implementing the CityHash64 algorithm, version 1.1, without seed values
     * @see #city_1_1(long)
     * @see #city_1_1(long, long)
     */
    public static LongHashFunction city_1_1() {
        return CityAndFarmHash_1_1.asLongHashFunctionWithoutSeed();
    }

    /**
     * Returns a hash function implementing the
     * <a href="https://github.com/google/cityhash/blob/8af9b8c2b889d80c22d6bc26ba0df1afb79a30db/src/city.cc">
     * CityHash64 algorithm, version 1.1</a> using the given seed value. This implementation produce
     * equal results for equal input on platforms with different {@link ByteOrder}, but is slower
     * on big-endian platforms than on little-endian.
     *
     * @param seed the seed value to be used for hashing
     * @return a {@code LongHashFunction} implementing the CityHash64 algorithm, version 1.1, with the provided seed
     * @see #city_1_1()
     * @see #city_1_1(long, long)
     */
    public static LongHashFunction city_1_1(long seed) {
        return CityAndFarmHash_1_1.asLongHashFunctionWithSeed(seed);
    }

    /**
     * Returns a hash function implementing the
     * <a href="https://github.com/google/cityhash/blob/8af9b8c2b889d80c22d6bc26ba0df1afb79a30db/src/city.cc">
     * CityHash64 algorithm, version 1.1</a> using the two given seed values. This implementation
     * produce equal results for equal input on platforms with different {@link ByteOrder}, but
     * is slower on big-endian platforms than on little-endian.
     *
     * @param seed0 the first seed value to be used for hashing
     * @param seed1 the second seed value to be used for hashing
     * @return a {@code LongHashFunction} implementing the CityHash64 algorithm, version 1.1, with the provided seed values
     * @see #city_1_1()
     * @see #city_1_1(long)
     */
    public static LongHashFunction city_1_1(long seed0, long seed1) {
        return CityAndFarmHash_1_1.asLongHashFunctionWithTwoSeeds(seed0, seed1);
    }

    /**
     * Returns a hash function implementing so-called
     * <a href="https://github.com/google/farmhash/blob/a371645d2caa1685541d9963b94751c23b235c72/dev/farmhashna.cc">
     * farmhashna algorithm</a>, without seed values. This implementation produces equal results for
     * equal input on platforms with different {@link ByteOrder}, but is slower on big-endian
     * platforms than on little-endian.
     *
     * <p>{@code farmhashna} was introduced in FarmHash 1.0. For inputs shorter than 32 bytes it's
     * output is equivalent to {@link #city_1_1()} output.
     *
     * @return a {@code LongHashFunction} implementing the farmhashna algorithm without seed values
     * @see #farmNa(long)
     * @see #farmNa(long, long)
     */
    public static LongHashFunction farmNa() {
        return CityAndFarmHash_1_1.naWithoutSeeds();
    }

    /**
     * Returns a hash function implementing so-called
     * <a href="https://github.com/google/farmhash/blob/a371645d2caa1685541d9963b94751c23b235c72/dev/farmhashna.cc">
     * farmhashna algorithm</a>, using the given seed value. This implementation produces equal
     * results for equal input on platforms with different {@link ByteOrder}, but is slower on
     * big-endian platforms than on little-endian.
     *
     * <p>{@code farmhashna} was introduced in FarmHash 1.0. For inputs shorter than 32 bytes it's
     * output is equivalent to {@link #city_1_1(long)} output.
     *
     * @param seed the seed value to be used for hashing
     * @return a {@code LongHashFunction} implementing the farmhashna algorithm with the given seed value
     * @see #farmNa()
     * @see #farmNa(long, long)
     */
    public static LongHashFunction farmNa(long seed) {
        return CityAndFarmHash_1_1.naWithSeed(seed);
    }

    /**
     * Returns a hash function implementing the so-called
     * <a href="https://github.com/google/farmhash/blob/a371645d2caa1685541d9963b94751c23b235c72/dev/farmhashna.cc">
     * farmhashna algorithm</a> using the two given seed values. This implementation produces equal
     * results for equal input on platforms with different {@link ByteOrder}, but is slower on
     * big-endian platforms than on little-endian.
     *
     * <p>{@code farmhashna} was introduced in FarmHash 1.0. For inputs shorter than 32 bytes, the
     * output is equivalent to {@link #city_1_1(long, long)} output.
     *
     * @param seed0 the first seed value to be used for hashing
     * @param seed1 the second seed value to be used for hashing
     * @return a {@code LongHashFunction} implementing the farmhashna algorithm with the two given seed values
     * @see #farmNa()
     * @see #farmNa(long)
     */
    public static LongHashFunction farmNa(long seed0, long seed1) {
        return CityAndFarmHash_1_1.naWithSeeds(seed0, seed1);
    }

    /**
     * Returns a hash function implementing the so-called
     * <a href="https://github.com/google/farmhash/blob/34c13ddfab0e35422f4c3979f360635a8c050260/dev/farmhashuo.cc">
     * farmhashuo algorithm</a> without seed values. This implementation produces equal results for
     * equal input on platforms with different {@link ByteOrder}, but is slower on big-endian
     * platforms than on little-endian.
     *
     * <p>{@code farmhashuo} was introduced in FarmHash 1.1.
     *
     * @return a {@code LongHashFunction} implementing the farmhashuo algorithm without seed values
     * @see #farmUo(long)
     * @see #farmUo(long, long)
     */
    public static LongHashFunction farmUo() {
        return CityAndFarmHash_1_1.uoWithoutSeeds();
    }

    /**
     * Returns a hash function implementing the so-called
     * <a href="https://github.com/google/farmhash/blob/34c13ddfab0e35422f4c3979f360635a8c050260/dev/farmhashuo.cc">
     * farmhashuo algorithm</a> with the given seed value. This implementation produces equal results
     * for equal input on platforms with different {@link ByteOrder}, but is slower on big-endian
     * platforms than on little-endian.
     *
     * <p>{@code farmhashuo} was introduced in FarmHash 1.1.
     *
     * @param seed the seed value to be used for hashing
     * @return a {@code LongHashFunction} implementing the farmhashuo algorithm with the given seed value
     * @see #farmUo()
     * @see #farmUo(long, long)
     */
    public static LongHashFunction farmUo(long seed) {
        return CityAndFarmHash_1_1.uoWithSeed(seed);
    }

    /**
     * Returns a hash function implementing the so-called
     * <a href="https://github.com/google/farmhash/blob/34c13ddfab0e35422f4c3979f360635a8c050260/dev/farmhashuo.cc">
     * farmhashuo algorithm</a> with the two given seed values. This implementation produces equal
     * results for equal input on platforms with different {@link ByteOrder}, but is slower on
     * big-endian platforms than on little-endian.
     *
     * <p>{@code farmhashuo} was introduced in FarmHash 1.1.
     *
     * @param seed0 the first seed value to be used for hashing
     * @param seed1 the second seed value to be used for hashing
     * @return a {@code LongHashFunction} implementing the farmhashuo algorithm with the two given seed values
     * @see #farmUo()
     * @see #farmUo(long)
     */
    public static LongHashFunction farmUo(long seed0, long seed1) {
        return CityAndFarmHash_1_1.uoWithSeeds(seed0, seed1);
    }

    /**
     * Returns a hash function implementing the <a href="https://github.com/Cyan4973/xxHash">xxHash
     * algorithm</a> without a seed value (0 is used as default seed value). This implementation
     * produces equal results for equal input on platforms with different {@link
     * ByteOrder}, but is slower on big-endian platforms than on little-endian.
     *
     * @return a {@code LongHashFunction} implementing the xxHash algorithm without a seed value
     * @see #xx(long)
     */
    public static LongHashFunction xx() {
        return XxHash.asLongHashFunctionWithoutSeed();
    }

    /**
     * Returns a hash function implementing the <a href="https://github.com/Cyan4973/xxHash">xxHash
     * algorithm</a> with the given seed value. This implementation produces equal results for equal
     * input on platforms with different {@link ByteOrder}, but is slower on big-endian platforms
     * than on little-endian.
     *
     * @param seed the seed value to be used for hashing
     * @return a {@code LongHashFunction} implementing the xxHash algorithm with the given seed value
     * @see #xx()
     */
    public static LongHashFunction xx(long seed) {
        return XxHash.asLongHashFunctionWithSeed(seed);
    }

    /**
     * Constructor for use in subclasses.
     */
    protected LongHashFunction() {
    }

    /**
     * Returns the hash code for the given {@code long} value; this method is consistent with
     * {@code LongHashFunction} methods that accept sequences of bytes, assuming the {@code input}
     * value is interpreted in {@linkplain ByteOrder#nativeOrder() native} byte order. For example,
     * the result of {@code hashLong(v)} call is identical to the result of
     * {@code hashLongs(new long[] {v})} call for any {@code long} value.
     *
     * @param input the long value to be hashed
     * @return the hash code for the given long value
     */
    public abstract long hashLong(long input);

    /**
     * Returns the hash code for the given {@code int} value; this method is consistent with
     * {@code LongHashFunction} methods that accept sequences of bytes, assuming the {@code input}
     * value is interpreted in {@linkplain ByteOrder#nativeOrder() native} byte order. For example,
     * the result of {@code hashInt(v)} call is identical to the result of
     * {@code hashInts(new int[] {v})} call for any {@code int} value.
     *
     * @param input the int value to be hashed
     * @return the hash code for the given int value
     */
    public abstract long hashInt(int input);

    /**
     * Returns the hash code for the given {@code short} value; this method is consistent with
     * {@code LongHashFunction} methods that accept sequences of bytes, assuming the {@code input}
     * value is interpreted in {@linkplain ByteOrder#nativeOrder() native} byte order. For example,
     * the result of {@code hashShort(v)} call is identical to the result of
     * {@code hashShorts(new short[] {v})} call for any {@code short} value.
     * As a consequence, {@code hashShort(v)} call produce always the same result as {@code
     * hashChar((char) v)}.
     *
     * @param input the short value to be hashed
     * @return the hash code for the given short value
     */
    public abstract long hashShort(short input);

    /**
     * Returns the hash code for the given {@code char} value; this method is consistent with
     * {@code LongHashFunction} methods that accept sequences of bytes, assuming the {@code input}
     * value is interpreted in {@linkplain ByteOrder#nativeOrder() native} byte order. For example,
     * the result of {@code hashChar(v)} call is identical to the result of
     * {@code hashChars(new char[] {v})} call for any {@code char} value.
     * As a consequence, {@code hashChar(v)} call produce always the same result as {@code
     * hashShort((short) v)}.
     *
     * @param input the char value to be hashed
     * @return the hash code for the given char value
     */
    public abstract long hashChar(char input);

    /**
     * Returns the hash code for the given {@code byte} value. This method is consistent with
     * {@code LongHashFunction} methods that accept sequences of bytes. For example, the result of
     * {@code hashByte(v)} call is identical to the result of
     * {@code hashBytes(new byte[] {v})} call for any {@code byte} value.
     *
     * @param input the byte value to be hashed
     * @return the hash code for the given byte value
     */
    public abstract long hashByte(byte input);

    /**
     * Returns the hash code for the empty (zero-length) bytes sequence,
     * for example {@code hashBytes(new byte[0])}.
     *
     * @return the hash code for the empty bytes sequence
     */
    public abstract long hashVoid();

    /**
     * Returns the hash code for {@code len} continuous bytes of the given {@code input} object,
     * starting from the given offset. The abstraction of input as ordered byte sequence and
     * "offset within the input" is defined by the given {@code access} strategy.
     *
     * <p>This method doesn't promise to throw a {@code RuntimeException} if {@code
     * [off, off + len - 1]} subsequence exceeds the bounds of the bytes sequence, defined by {@code
     * access} strategy for the given {@code input}, so use this method with caution.
     *
     * @param input  the object to read bytes from
     * @param access access which defines the abstraction of the given input
     *               as ordered byte sequence
     * @param off    offset to the first byte of the subsequence to hash
     * @param len    length of the subsequence to hash
     * @param <T>    the type of the input
     * @return hash code for the specified bytes subsequence
     */
    public abstract <T> long hash(T input, Access<T> access, long off, long len);

    private long unsafeHash(Object input, long off, long len) {
        return hash(input, UnsafeAccess.INSTANCE, off, len);
    }

    /**
     * Shortcut for {@link #hashBooleans(boolean[]) hashBooleans(new boolean[] &#123;input&#125;)}.
     * Note that this is not necessarily equal to {@code hashByte(input ? (byte) 1 : (byte) 0)},
     * because booleans could be stored differently in this JVM.
     *
     * @param input the boolean value to be hashed
     * @return the hash code for the given boolean value
     */
    public long hashBoolean(boolean input) {
        return hashByte(input ? TRUE_BYTE_VALUE : FALSE_BYTE_VALUE);
    }

    /**
     * Shortcut for {@link #hashBooleans(boolean[], int, int) hashBooleans(input, 0, input.length)}.
     *
     * @param input the boolean array to be hashed
     * @return the hash code for the given boolean array
     */
    public long hashBooleans(boolean[] input) {
        return unsafeHash(input, BOOLEAN_BASE, input.length);
    }

    /**
     * Returns the hash code for the specified subsequence of the given {@code boolean} array.
     *
     * <p>Default implementation delegates to {@link #hash(Object, Access, long, long)} method
     * using {@linkplain Access#unsafe() unsafe} {@code Access}.
     *
     * @param input the array to read data from
     * @param off   index of the first {@code boolean} in the subsequence to hash
     * @param len   length of the subsequence to hash
     * @return hash code for the specified subsequence
     * @throws IndexOutOfBoundsException if {@code off < 0} or {@code off + len > input.length}
     *                                   or {@code len < 0}
     */
    public long hashBooleans(boolean[] input, int off, int len) {
        checkArrayOffs(input.length, off, len);
        return unsafeHash(input, BOOLEAN_BASE + off, len);
    }

    /**
     * Shortcut for {@link #hashBytes(byte[], int, int) hashBytes(input, 0, input.length)}.
     *
     * @param input the byte array to be hashed
     * @return the hash code for the given byte array
     */
    public long hashBytes(byte[] input) {
        return unsafeHash(input, BYTE_BASE, input.length);
    }

    /**
     * Returns the hash code for the specified subsequence of the given {@code byte} array.
     *
     * <p>Default implementation delegates to {@link #hash(Object, Access, long, long)} method
     * using {@linkplain Access#unsafe() unsafe} {@code Access}.
     *
     * @param input the array to read bytes from
     * @param off   index of the first {@code byte} in the subsequence to hash
     * @param len   length of the subsequence to hash
     * @return hash code for the specified subsequence
     * @throws IndexOutOfBoundsException if {@code off < 0} or {@code off + len > input.length}
     *                                   or {@code len < 0}
     */
    public long hashBytes(byte[] input, int off, int len) {
        checkArrayOffs(input.length, off, len);
        return unsafeHash(input, BYTE_BASE + off, len);
    }

    /**
     * Shortcut for {@link #hashBytes(ByteBuffer, int, int)
     * hashBytes(input, input.position(), input.remaining())}.
     *
     * @param input the ByteBuffer to be hashed
     * @return the hash code for the given ByteBuffer
     */
    public long hashBytes(ByteBuffer input) {
        return hashByteBuffer(input, input.position(), input.remaining());
    }

    /**
     * Returns the hash code for the specified subsequence of the given {@code ByteBuffer}.
     *
     * <p>This method doesn't alter the state (mark, position, limit or order) of the given
     * {@code ByteBuffer}.
     *
     * <p>Default implementation delegates to {@link #hash(Object, Access, long, long)} method
     * using {@link Access#toByteBuffer()}.
     *
     * @param input the buffer to read bytes from
     * @param off   index of the first {@code byte} in the subsequence to hash
     * @param len   length of the subsequence to hash
     * @return hash code for the specified subsequence
     * @throws IndexOutOfBoundsException if {@code off < 0} or {@code off + len > input.capacity()}
     *                                   or {@code len < 0}
     */
    public long hashBytes(ByteBuffer input, int off, int len) {
        checkArrayOffs(input.capacity(), off, len);
        return hashByteBuffer(input, off, len);
    }

    private long hashByteBuffer(ByteBuffer input, int off, int len) {
        if (input.hasArray()) {
            return unsafeHash(input.array(), BYTE_BASE + input.arrayOffset() + off, len);
        } else if (input instanceof DirectBuffer) {
            return unsafeHash(null, ((DirectBuffer) input).address() + off, len);
        } else {
            return hash(input, ByteBufferAccess.INSTANCE, off, len);
        }
    }

    /**
     * Returns the hash code of bytes of the wild memory from the given address. Use with caution.
     *
     * <p>Default implementation delegates to {@link #hash(Object, Access, long, long)} method
     * using {@linkplain Access#unsafe() unsafe} {@code Access}.
     *
     * @param address the address of the first byte to hash
     * @param len     length of the byte sequence to hash
     * @return hash code for the specified byte sequence
     */
    public long hashMemory(long address, long len) {
        return unsafeHash(null, address, len);
    }

    /**
     * Shortcut for {@link #hashChars(char[], int, int) hashChars(input, 0, input.length)}.
     *
     * @param input the char array to be hashed
     * @return the hash code for the given char array
     */
    public long hashChars(char[] input) {
        return unsafeHash(input, CHAR_BASE, input.length * 2L);
    }

    /**
     * Returns the hash code for bytes, as they lay in memory, of the specified subsequence
     * of the given {@code char} array.
     *
     * <p>Default implementation delegates to {@link #hash(Object, Access, long, long)} method
     * using {@linkplain Access#unsafe() unsafe} {@code Access}.
     *
     * @param input the array to read data from
     * @param off   index of the first {@code char} in the subsequence to hash
     * @param len   length of the subsequence to hash, in chars (i. e. the length of the bytes
     *              sequence to hash is {@code len * 2L})
     * @return hash code for the specified subsequence
     * @throws IndexOutOfBoundsException if {@code off < 0} or {@code off + len > input.length}
     *                                   or {@code len < 0}
     */
    public long hashChars(char[] input, int off, int len) {
        checkArrayOffs(input.length, off, len);
        return unsafeHash(input, CHAR_BASE + (off * 2L), len * 2L);
    }

    /**
     * Shortcut for {@link #hashChars(String, int, int) hashChars(input, 0, input.length())}.
     *
     * @param input the String to be hashed
     * @return the hash code for the given String
     */
    public long hashChars(String input) {
        return VALID_STRING_HASH.longHash(input, this, 0, input.length());
    }

    /**
     * Returns the hash code for bytes of the specified subsequence of the given {@code String}'s
     * underlying {@code char} array.
     *
     * <p>Default implementation could either delegate to {@link #hash(Object, Access, long, long)}
     * using {@link Access#toNativeCharSequence()}, or to {@link #hashChars(char[], int, int)}.
     *
     * @param input the string which bytes to hash
     * @param off   index of the first {@code char} in the subsequence to hash
     * @param len   length of the subsequence to hash, in chars (i. e. the length of the bytes
     *              sequence to hash is {@code len * 2L})
     * @return the hash code of the given {@code String}'s bytes
     * @throws IndexOutOfBoundsException if {@code off < 0} or {@code off + len > input.length()}
     *                                   or {@code len < 0}
     */
    public long hashChars(String input, int off, int len) {
        checkArrayOffs(input.length(), off, len);
        return VALID_STRING_HASH.longHash(input, this, off, len);
    }

    /**
     * Shortcut for {@link #hashChars(StringBuilder, int, int) hashChars(input, 0, input.length())}.
     *
     * @param input the StringBuilder to be hashed
     * @return the hash code for the given StringBuilder
     */
    public long hashChars(StringBuilder input) {
        return hashNativeChars(input);
    }

    /**
     * Returns the hash code for bytes of the specified subsequence of the given
     * {@code StringBuilder}'s underlying {@code char} array.
     *
     * <p>Default implementation could either delegate to {@link #hash(Object, Access, long, long)}
     * using {@link Access#toNativeCharSequence()}, or to {@link #hashChars(char[], int, int)}.
     *
     * @param input the string builder which bytes to hash
     * @param off   index of the first {@code char} in the subsequence to hash
     * @param len   length of the subsequence to hash, in chars (i. e. the length of the bytes
     *              sequence to hash is {@code len * 2L})
     * @return the hash code of the given {@code String}'s bytes
     * @throws IndexOutOfBoundsException if {@code off < 0} or {@code off + len > input.length()}
     *                                   or {@code len < 0}
     */
    public long hashChars(StringBuilder input, int off, int len) {
        checkArrayOffs(input.length(), off, len);
        return hashNativeChars(input, off, len);
    }

    /**
     * Returns the hash code for the entire CharSequence.
     *
     * @param input the CharSequence to be hashed
     * @return the hash code for the given CharSequence
     */
    long hashNativeChars(CharSequence input) {
        return hashNativeChars(input, 0, input.length());
    }

    /**
     * Returns the hash code for a subsequence of the given CharSequence.
     *
     * @param input the CharSequence to be hashed
     * @param off   the index of the first char in the subsequence
     * @param len   the length of the subsequence
     * @return the hash code for the specified subsequence of the given CharSequence
     */
    long hashNativeChars(CharSequence input, int off, int len) {
        return hash(input, nativeCharSequenceAccess(), off * 2L, len * 2L);
    }

    /**
     * Shortcut for {@link #hashShorts(short[], int, int) hashShorts(input, 0, input.length)}.
     *
     * @param input the short array to be hashed
     * @return the hash code for the given short array
     */
    public long hashShorts(short[] input) {
        return unsafeHash(input, SHORT_BASE, input.length * 2L);
    }

    /**
     * Returns the hash code for bytes, as they lay in memory, of the specified subsequence
     * of the given {@code short} array.
     *
     * <p>Default implementation delegates to {@link #hash(Object, Access, long, long)} method
     * using {@linkplain Access#unsafe() unsafe} {@code Access}.
     *
     * @param input the array to read data from
     * @param off   index of the first {@code short} in the subsequence to hash
     * @param len   length of the subsequence to hash, in shorts (i. e. the length of the bytes
     *              sequence to hash is {@code len * 2L})
     * @return hash code for the specified subsequence
     * @throws IndexOutOfBoundsException if {@code off < 0} or {@code off + len > input.length}
     *                                   or {@code len < 0}
     */
    public long hashShorts(short[] input, int off, int len) {
        checkArrayOffs(input.length, off, len);
        return unsafeHash(input, SHORT_BASE + (off * 2L), len * 2L);
    }

    /**
     * Shortcut for {@link #hashInts(int[], int, int) hashInts(input, 0, input.length)}.
     *
     * @param input the integer array to be hashed
     * @return the hash code for the given integer array
     */
    public long hashInts(int[] input) {
        return unsafeHash(input, INT_BASE, input.length * 4L);
    }

    /**
     * Returns the hash code for bytes, as they lay in memory, of the specified subsequence
     * of the given {@code int} array.
     *
     * <p>Default implementation delegates to {@link #hash(Object, Access, long, long)} method
     * using {@linkplain Access#unsafe() unsafe} {@code Access}.
     *
     * @param input the array to read data from
     * @param off   index of the first {@code int} in the subsequence to hash
     * @param len   length of the subsequence to hash, in ints (i. e. the length of the bytes
     *              sequence to hash is {@code len * 4L})
     * @return hash code for the specified subsequence
     * @throws IndexOutOfBoundsException if {@code off < 0} or {@code off + len > input.length}
     *                                   or {@code len < 0}
     */
    public long hashInts(int[] input, int off, int len) {
        checkArrayOffs(input.length, off, len);
        return unsafeHash(input, INT_BASE + (off * 4L), len * 4L);
    }

    /**
     * Shortcut for {@link #hashLongs(long[], int, int) hashLongs(input, 0, input.length)}.
     *
     * @param input the long array to be hashed
     * @return the hash code for the given long array
     */
    public long hashLongs(long[] input) {
        return unsafeHash(input, LONG_BASE, input.length * 8L);
    }

    /**
     * Returns the hash code for bytes, as they lay in memory, of the specified subsequence
     * of the given {@code long} array.
     *
     * <p>Default implementation delegates to {@link #hash(Object, Access, long, long)} method
     * using {@linkplain Access#unsafe() unsafe} {@code Access}.
     *
     * @param input the array to read data from
     * @param off   index of the first {@code long} in the subsequence to hash
     * @param len   length of the subsequence to hash, in longs (i. e. the length of the bytes
     *              sequence to hash is {@code len * 8L})
     * @return hash code for the specified subsequence
     * @throws IndexOutOfBoundsException if {@code off < 0} or {@code off + len > input.length}
     *                                   or {@code len < 0}
     */
    public long hashLongs(long[] input, int off, int len) {
        checkArrayOffs(input.length, off, len);
        return unsafeHash(input, LONG_BASE + (off * 8L), len * 8L);
    }
}