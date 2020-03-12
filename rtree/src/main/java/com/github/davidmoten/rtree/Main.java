package com.github.davidmoten.rtree;

import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Geometry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {
  public static void main(String[] args) throws Exception {
    File file = new File("rtree/src/main/resources/greek-earthquakes-1964-2000.txt");
    if (!file.exists())
      System.out.println("fuck");
    RTree<Integer, Geometry> tree = RTree.star().create();
    BufferedReader reader = new BufferedReader(new FileReader(file));
    int index = 0;
    String s;
    while ((s = reader.readLine()) != null) {
      String[] split = s.split(" ");
      tree = tree.add(index++, Geometries.point(Double.parseDouble(split[0]), Double.parseDouble(split[1])));
    }
    tree.visualize(600, 600).save("rtree/target/myTree.png");
  }
}
