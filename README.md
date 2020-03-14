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

Added a skyline function.
- SkylineTest in '/cs562project1/rtree/src/test/java/com/github/davidmoten/rtree/SerializersTest.java'

Unit test for skyline.
- SkylineMain in '/cs562project1/rtree/src/test/java/com/github/davidmoten/rtree/SkylineMain.java'

Insert the points of the given dataset into an R-tree and create an output.txt file recording the skyline points.
