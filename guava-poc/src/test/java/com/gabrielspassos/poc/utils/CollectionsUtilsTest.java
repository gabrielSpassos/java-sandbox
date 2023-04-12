package com.gabrielspassos.poc.utils;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multiset;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

    @Test
    public void createMultiset() {
        List<Integer> collection = Arrays.asList(84, 1, 31, 7);
        Multiset<Integer> multiset = HashMultiset.create(collection);

        List<Integer> otherCollection = Arrays.asList(31, 7, 1, 84);
        Multiset<Integer> immutableMultiset = ImmutableMultiset.copyOf(otherCollection);

        assertNotEquals(collection, otherCollection);
        assertEquals(multiset, immutableMultiset);
    }

    @Test
    public void createEmptyMultiset() {
        Multiset<Integer> multiset = HashMultiset.create();
        Multiset<Integer> immutableMultiset = new ImmutableMultiset.Builder<Integer>().build();

        assertEquals(multiset, immutableMultiset);
    }

    @Test
    public void createMultimap() {
        // creates a ListMultimap with tree keys and array list values
        ArrayList<Integer> integers = Lists.newArrayList();
        ListMultimap<String, Integer> treeListMultimap = MultimapBuilder.treeKeys().arrayListValues().build();
        treeListMultimap.put("A", 1);
        treeListMultimap.putAll("A", Lists.newArrayList(1, 2, 3));
        treeListMultimap.put("B", 4);
        treeListMultimap.putAll("C", integers);

        assertEquals(Lists.newArrayList(1, 1, 2, 3), treeListMultimap.get("A"));
        assertEquals(Lists.newArrayList(4), treeListMultimap.get("B"));
        assertFalse(treeListMultimap.containsKey("C"));

        // creates a SetMultimap with hash keys and enum set values
        SetMultimap<String, Integer> hashEnumMultimap = MultimapBuilder.hashKeys().hashSetValues().build();
        hashEnumMultimap.put("A", 1);
        hashEnumMultimap.putAll("A", Lists.newArrayList(1, 2, 3));
        hashEnumMultimap.put("B", 4);
        hashEnumMultimap.putAll("C", integers);
        assertEquals(Set.of(1, 2, 3), hashEnumMultimap.get("A"));
        assertEquals(Set.of(4), hashEnumMultimap.get("B"));
        assertFalse(hashEnumMultimap.containsKey("C"));
    }

    @Test
    public void createEmptyMultimap() {
        ListMultimap<String, Integer> build = MultimapBuilder.treeKeys().arrayListValues().build();
        ArrayListMultimap<String, Integer> arrayListMultimap = ArrayListMultimap.create();
        ImmutableListMultimap<String, Integer> immutableListMultimap = new ImmutableListMultimap.Builder<String, Integer>().build();

        SetMultimap<String, Integer> hashEnumMultimap = MultimapBuilder.hashKeys().hashSetValues().build();
        HashMultimap<String, Integer> hashMultimap = HashMultimap.create();
        ImmutableSetMultimap<String, Integer> immutableSetMultimap = new ImmutableSetMultimap.Builder<String, Integer>().build();
    }

    @Test
    public void createBiMap() {
        BiMap<String, Integer> biMap = HashBiMap.create();
        biMap.put("Bob", 42);
        BiMap<Integer, String> inverse = biMap.inverse();
        assertEquals("Bob", inverse.get(42));

        ImmutableBiMap<String, Integer> immutableBiMap = new ImmutableBiMap.Builder<String, Integer>()
                .put("Bob", 42)
                .build();
        ImmutableBiMap<Integer, String> immutableInverse = immutableBiMap.inverse();
        assertEquals("Bob", immutableInverse.get(42));
    }

    @Test
    public void createTable() {
        Table<String, Double, Boolean> table = HashBasedTable.create();
        table.put("foo", 1.6, true);
        table.put("bar", -5.9, false);

        Map<Double, Boolean> firstRow = table.row("foo");
        Map<String, Boolean> secondColumn = table.column(-5.9);

        assertTrue(firstRow.get(1.6));
        assertFalse(secondColumn.get("bar"));

        ImmutableTable<String, Double, Boolean> immutableTable = new ImmutableTable.Builder<String, Double, Boolean>().build();
    }

}