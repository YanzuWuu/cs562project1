# cs562project1

### Team members: Fanmin Zhu, Yuncheng Zhu

## Getting started
### 1. Environment Requirements
- JDK version: 1.8+
- Maven version: 3.3+

### 2. Build Project
```shell script
cd rtree
mvn clean install
```

### 3. Run all unit tests
```shell script
mvn test
```

## Run Skyline methods

### 1. Run unit test of Skyline
The unit test of Skyline implementation is located at `rtree/src/test/java/com/github/davidmoten/rtree/SkylineTest.java`.

Run the test by the following command
```shell script
mvn -Dtest=SkylineTest test
```

### 2. Get Skyline points from dataset
The source code is located at `rtree/src/test/java/com/github/davidmoten/rtree/SkylineMain.java`

It will read data from `greek-earthquakes-1964-2000.txt.gz` and output the skyline points to `rtree/output.txt`

Run it by the following command
```shell script
mvn exec:java -Dexec.mainClass="com.github.davidmoten.rtree.SkylineMain" -Dexec.classpathScope="test"
```

## Details about Skyline implementation

The coding part is mainly distributed in three part:
- Rtree.java in '/cs562project1/rtree/src/main/java/com/github/davidmoten/rtree/RTree.java'

    - Skyline function
    
        This function is based on the BBS(branch-and-bound skyline) algorithm in this [paper](http://delab.csd.auth.gr/courses/_c_mmdb/skyline.pdf).
        
        First we insert the root of the R-tree into a heap, and while the heap is not empty, we pop a node 'top' out from the heap.
        Inside the while loop, we determine whether to place the top into the hashset 'skyline' for storing result or put top's child node into heap.
        
        If top is dominated by some points in hashset, we can just discard it.
        
        If top is a Nonleaf node, we put child nodes that are not dominated by points in hashset into heap.
        
        If top is a leaf node, we put child nodes (entries) that are not dominated by points in hashset into heap.
        
        If top is an entry, we insert it into skyline.
        
    - Add function
        
        Every time we insert a new node, we need to judge if the node is dominated by some points in skyline. If it is, we don't need to change the skyline set.
        Otherwise, we must remove the nodes dominated by the inserted node in the skyline and add the inserted node into skyline set.
        
    - Delete function
    
        If the deleted node is not in skyline set, we don't need to change the skyline points.
        
        Otherwise, we need run the skyline function again to create a new skyline set. 
        
- SkylineTest in '/cs562project1/rtree/src/test/java/com/github/davidmoten/rtree/SerializersTest.java'

    Unit test for skyline.
- SkylineMain in '/cs562project1/rtree/src/test/java/com/github/davidmoten/rtree/SkylineMain.java'

    Insert the points of the given dataset into an R-tree and create an output.txt file recording the skyline points.
