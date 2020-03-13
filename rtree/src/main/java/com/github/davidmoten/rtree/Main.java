package com.github.davidmoten.rtree;

import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Geometry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) throws Exception {
    File file = new File("rtree/src/main/resources/greek-earthquakes-1964-2000.txt");
    RTree<Integer, Geometry> tree = RTree.star().create();
    BufferedReader reader = new BufferedReader(new FileReader(file));
    int index = 0;
    String s;
    while ((s = reader.readLine()) != null) {
      String[] split = s.split(" ");
      tree = tree.add(index++, Geometries.point(Double.parseDouble(split[0]), Double.parseDouble(split[1])));
    }
    tree.visualize(600, 600).save("rtree/target/myTree.png");

    RTree<Character, Geometry> testTree = RTree.star().create();
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
    testTree.visualize(400, 400).save("rtree/target/testTree.png");

    RTree<Character, Geometry> output = RTree.create(new ArrayList<>(testTree.skyline()));
    output.visualize(400, 400).save("rtree/target/output.png");
  }
}
