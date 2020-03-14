# cs562project1

### Team members: Fanmin Zhu, Yuncheng Zhu

## Deployment Instructions
### 1. Java Requirements

Install using Maven: Right click pom.xml and select "New Maven Project".

Maven will install all Java dependencies for this R-tree. You may need to manually configure the JDK for this project. 
The JDK has to be version 1.8+.

### 2. Run test
- Command: `mvn clean install`

This command will build the project and run all the test to make sure the the environment is right.

## Running the R-tree

- Go to the following path in our project: `/cs562project1/rtree/src/test/java/com/github/davidmoten/rtree/SkylineMain.java`

- Run the SkylineMain.java, which will insert the points of the given dataset into an R-tree and create an output.txt file
recording the skyline points.

## Main coding

The coding part is mainly distributed in three part:
- Rtree.java in '/Users/y/Courses/562database/cs562project1/rtree/src/main/java/com/github/davidmoten/rtree/RTree.java'
Added a skyline function.
- SkylineTest in '/Users/y/Courses/562database/cs562project1/rtree/src/test/java/com/github/davidmoten/rtree/SerializersTest.java'
Unit test for skyline.
- SkylineMain in '/Users/y/Courses/562database/cs562project1/rtree/src/test/java/com/github/davidmoten/rtree/SkylineMain.java'
Insert the points of the given dataset into an R-tree and create an output.txt file recording the skyline points.