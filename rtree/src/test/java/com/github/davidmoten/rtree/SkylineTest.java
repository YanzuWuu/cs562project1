package com.github.davidmoten.rtree;

import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Geometry;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SkylineTest {
  @Test
  public void testSkylineBasic() {
    RTree<Character, Geometry> testTree = RTree.create();
    testTree = testTree.add('a', Geometries.point(1, 9));
    testTree = testTree.add('b', Geometries.point(2, 10));
    testTree = testTree.add('c', Geometries.point(4, 8));
    testTree = testTree.add('d', Geometries.point(6, 7));
    testTree = testTree.add('e', Geometries.point(9, 10));
    testTree = testTree.add('f', Geometries.point(7, 5));
    testTree = testTree.add('g', Geometries.point(5, 6));
    testTree = testTree.add('h', Geometries.point(4, 3));
    testTree = testTree.add('i', Geometries.point(3, 2));
    testTree = testTree.add('k', Geometries.point(9, 1));
    testTree = testTree.add('l', Geometries.point(10, 4));
    testTree = testTree.add('m', Geometries.point(6, 2));
    testTree = testTree.add('n', Geometries.point(8, 3));

    HashSet<Entry<? extends Character, ? extends Geometry>> skyline = testTree.getSkyline();
    HashSet<Entry<? extends Character, ? extends Geometry>> expected = new HashSet<>(Arrays.asList(
        testTree.context().factory().createEntry('a', Geometries.point(1, 9)),
        testTree.context().factory().createEntry('i', Geometries.point(3, 2)),
        testTree.context().factory().createEntry('k', Geometries.point(9, 1))
    ));

    assertEquals(skyline, expected);
  }

  @Test
  public void testSkylineAdd() {
    RTree<Character, Geometry> testTree = RTree.create();
    testTree = testTree.add('a', Geometries.point(1, 9));
    testTree = testTree.add('b', Geometries.point(2, 10));
    testTree = testTree.add('c', Geometries.point(4, 8));

    HashSet<Entry<? extends Character, ? extends Geometry>> skyline = testTree.getSkyline();
    HashSet<Entry<? extends Character, ? extends Geometry>> expected = new HashSet<>(Arrays.asList(
        testTree.context().factory().createEntry('a', Geometries.point(1, 9)),
        testTree.context().factory().createEntry('c', Geometries.point(4, 8))
    ));
    assertEquals(skyline, expected);

    testTree = testTree.add('i', Geometries.point(3, 2));
    skyline = testTree.getSkyline();
    expected.remove(testTree.context().factory().createEntry('c', Geometries.point(4, 8)));
    expected.add(testTree.context().factory().createEntry('i', Geometries.point(3, 2)));
    assertEquals(skyline, expected);

    testTree = testTree.add('k', Geometries.point(9, 1));
    skyline = testTree.getSkyline();
    expected.add(testTree.context().factory().createEntry('k', Geometries.point(9, 1)));
    assertEquals(skyline, expected);

    testTree = testTree.add('d', Geometries.point(6, 7));
    testTree = testTree.add('e', Geometries.point(9, 10));
    testTree = testTree.add('f', Geometries.point(7, 5));
    testTree = testTree.add('g', Geometries.point(5, 6));
    testTree = testTree.add('h', Geometries.point(4, 3));
    testTree = testTree.add('l', Geometries.point(10, 4));
    testTree = testTree.add('m', Geometries.point(6, 2));
    testTree = testTree.add('n', Geometries.point(8, 3));

    skyline = testTree.getSkyline();
    assertEquals(skyline, expected);
  }

  @Test
  public void testSkylineDelete() {
    RTree<Character, Geometry> testTree = RTree.create();
    testTree = testTree.add('f', Geometries.point(7, 5));
    testTree = testTree.add('h', Geometries.point(4, 3));
    testTree = testTree.add('i', Geometries.point(3, 2));
    HashSet<Entry<? extends Character, ? extends Geometry>> skyline = testTree.getSkyline();
    HashSet<Entry<? extends Character, ? extends Geometry>> expected = new HashSet<>(Collections.singleton(
        testTree.context().factory().createEntry('i', Geometries.point(3, 2))
    ));
    assertEquals(skyline, expected);

    testTree = testTree.delete('i', Geometries.point(3, 2));
    expected.remove(testTree.context().factory().createEntry('i', Geometries.point(3, 2)));
    expected.add(testTree.context().factory().createEntry('h', Geometries.point(4, 3)));
    skyline = testTree.getSkyline();
    assertEquals(skyline, expected);

    testTree = testTree.delete('f', Geometries.point(7, 5));
    skyline = testTree.getSkyline();
    assertEquals(skyline, expected);

    testTree = testTree.add('a', Geometries.point(1, 9));
    testTree = testTree.add('b', Geometries.point(2, 10));
    testTree = testTree.add('c', Geometries.point(4, 8));
    testTree = testTree.add('d', Geometries.point(6, 7));
    testTree = testTree.add('e', Geometries.point(9, 10));
    testTree = testTree.add('g', Geometries.point(5, 6));
    testTree = testTree.add('h', Geometries.point(4, 3));
    testTree = testTree.add('i', Geometries.point(3, 2));
    testTree = testTree.add('k', Geometries.point(9, 1));
    testTree = testTree.add('l', Geometries.point(10, 4));
    testTree = testTree.add('m', Geometries.point(6, 2));
    testTree = testTree.add('n', Geometries.point(8, 3));
    expected = new HashSet<>(Arrays.asList(
        testTree.context().factory().createEntry('a', Geometries.point(1, 9)),
        testTree.context().factory().createEntry('i', Geometries.point(3, 2)),
        testTree.context().factory().createEntry('k', Geometries.point(9, 1))
    ));
    assertEquals(skyline, expected);
  }

  @Test
  public void testSkylineEmpty() {
    RTree<Character, Geometry> testTree = RTree.create();
    HashSet<Entry<? extends Character, ? extends Geometry>> skyline = testTree.getSkyline();
    assertTrue(skyline.isEmpty());

    testTree = testTree.add('a', Geometries.point(1, 9));
    HashSet<Entry<? extends Character, ? extends Geometry>> expected = new HashSet<>(Collections.singleton(
        testTree.context().factory().createEntry('a', Geometries.point(1, 9))
    ));
    skyline = testTree.getSkyline();
    assertEquals(skyline, expected);

    testTree = testTree.delete('a', Geometries.point(1, 9));
    skyline = testTree.getSkyline();
    assertTrue(skyline.isEmpty());
  }
}
