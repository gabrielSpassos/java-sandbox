package com.gabrielspassos.poc.utils;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollectionsUtilsTest {

    private static CollectionsUtils collectionsUtils;

    @BeforeAll
    public static void setup() {
        collectionsUtils = new CollectionsUtils();
    }

    @Test
    void shouldJoinStrings() {
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");

        String joined = collectionsUtils.joinStrings(names);

        assertEquals("John;Jane;Adam;Tom", joined);
    }

    @Test
    void shouldSplitStrings() {
        List<String> expected = Lists.newArrayList("apple", "banana", "orange");

        List<String> splits = collectionsUtils.split();

        assertEquals(expected, splits);
    }

    @Test
    void shouldRevertList() {
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        List<String> expected = Lists.newArrayList("Tom", "Adam", "Jane", "John");

        List<String> reverse = Lists.reverse(names);

        assertEquals(expected, reverse);
    }

    @Test
    public void whenCreateCharacterListFromString_thenCreated() {
        List<Character> chars = Lists.charactersOf("John");

        assertEquals(4, chars.size());
        assertEquals(Lists.newArrayList('J', 'o', 'h', 'n'), chars);
    }

    @Test
    public void whenPartitionList_thenPartitioned(){
        List<String> names = Lists.newArrayList("John","Jane","Adam","Tom","Viki");

        List<List<String>> result = Lists.partition(names, 2);

        assertEquals(3, result.size());
        assertEquals(Lists.newArrayList("John", "Jane"), result.get(0));
        assertEquals(Lists.newArrayList("Adam", "Tom"), result.get(1));
        assertEquals(Lists.newArrayList("Viki"), result.get(2));
    }

    @Test
    public void whenRemoveDuplicatesFromList_thenRemoved() {
        List<Character> chars = Lists.newArrayList('h','e','l','l','o');
        assertEquals(5, chars.size());

        List<Character> result = ImmutableSet.copyOf(chars).asList();

        assertEquals(4, result.size());
        assertEquals(Lists.newArrayList('h', 'e', 'l', 'o'), result);
    }

    @Test
    public void whenRemoveNullFromList_thenRemoved() {
        List<String> names = Lists.newArrayList("John", null, "Adam", null, "Jane");
        Iterables.removeIf(names, Objects::isNull);

        assertEquals(3, names.size());
        assertEquals(Lists.newArrayList("John", "Adam", "Jane"), names);
    }

    @Test
    public void whenCreateImmutableList_thenCreated() {
        List<String> names = Lists.newArrayList("John", "Adam", "Jane");

        names.add("Tom");
        assertEquals(4, names.size());

        ImmutableList<String> immutable = ImmutableList.copyOf(names);
        assertEquals(Lists.newArrayList("John", "Adam", "Jane", "Tom"), immutable);
    }

    @Test
    public void whenCalculatingUnionOfSets_thenCorrect() {
        Set<Character> first = ImmutableSet.of('a', 'b', 'c');
        Set<Character> second = ImmutableSet.of('c', 'd', 'e');
        Set<Character> expected = ImmutableSet.of('a', 'b', 'c', 'd', 'e');

        Set<Character> union = Sets.union(first, second);

        assertEquals(expected, union);
    }

    @Test
    public void whenCalculatingCartesianProductOfSets_thenCorrect() {
        Set<Character> first = ImmutableSet.of('a', 'b');
        Set<Character> second = ImmutableSet.of('c', 'd');
        Set<List<Character>> result =
                Sets.cartesianProduct(ImmutableList.of(first, second));

        Function<List<Character>, String> func =
                new Function<List<Character>, String>() {
                    public String apply(List<Character> input) {
                        return Joiner.on(" ").join(input);
                    }
                };

        Iterable<String> joined = Iterables.transform(result, func);
        assertTrue(Iterables.contains(joined, "a c"));
        assertTrue(Iterables.contains(joined, "a d"));
        assertTrue(Iterables.contains(joined, "b c"));
        assertTrue(Iterables.contains(joined, "b d"));
    }

}