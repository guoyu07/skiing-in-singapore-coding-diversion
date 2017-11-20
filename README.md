# skiing-in-singapore-coding-diversion
This is coding problem to solving the skiing in Singapore. This is one of the [Redmart's coding question](http://geeks.redmart.com/2015/01/07/skiing-in-singapore-a-coding-diversion/). This repo gives the solution in Java language for the below [Problem Statement](https://github.com/swami701/skiing-in-singapore-coding-diversion#problem-statement). The [solution execution detail](https://github.com/swami701/skiing-in-singapore-coding-diversion#running-the-solution) and [Pre-requisites](https://github.com/swami701/skiing-in-singapore-coding-diversion#pre-requisites) are also provided below.

### Problem Statement
Skiing in Singapore

Sometimes it's nice to take a break and code up a solution to a small, fun problem. Here is one some of our engineers enjoyed recently. It's called Skiing In Singapore.

Well you can’t really ski in Singapore. But let’s say you hopped on a flight to the Niseko ski resort in Japan. Being a software engineer you can’t help but value efficiency, so naturally you want to ski as long as possible and as fast as possible without having to ride back up on the ski lift. So you take a look at the map of the mountain and try to find the longest ski run down.

In digital form the map looks like the number grid below. 
```
4 4
4 8 7 3
2 5 9 3
6 3 2 5
4 4 1 6
```

The first line (4 4) indicates that this is a 4x4 map. Each number represents the elevation of that area of the mountain. From each area (i.e. box) in the grid you can go north, south, east, west - but only if the elevation of the area you are going into is less than the one you are in. I.e. you can only ski downhill. You can start anywhere on the map and you are looking for a starting point with the longest possible path down as measured by the number of boxes you visit. And if there are several paths down of the same length, you want to take the one with the steepest vertical drop, i.e. the largest difference between your starting elevation and your ending elevation.

On this particular map the longest path down is of length=5 and it’s highlighted in bold below: `9-5-3-2-1`.

There is another path that is also length five: `8-5-3-2-1`. However the tie is broken by the first path being steeper, dropping from 9 to 1, a drop of 8, rather than just 8 to 1, a drop of 7.

Your challenge is to write a program in your favorite programming language to find the longest (and then steepest) path on this [map](http://s3-ap-southeast-1.amazonaws.com/geeks.redmart.com/coding-problems/map.txt) specified in the format above. It’s 1000x1000 in size, and all the numbers on it are between 0 and 1500.

Send your code or a github link (and a resume if you like) to [?????? at redmart dot com], replacing “??????” with the concatenation of the length of the longest path with the largest drop, and the size of the drop. So in the simple example above length=5, drop=8, so the email address would be [58 at redmart dot com]. If your e-mail gets through - you got the right answer.

Good luck and have fun!

### Pre-requisites
- [Java Development Kit 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Java Runtime Environment 8](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)

### Running the Solution
```
$ javac RedMartSkiing.java
$ java RedMartSkiing <file-name-with-matrix-input>
```

Sample execution
```
$ java RedMartSkiing RedMartTC1.txt
MaxCount: 5 DropCount: 7
MaxCount: 5 DropCount: 8
Email: 58@redmart.com
```

For the final result execute the below
```
$ java RedMartSkiing map.txt
```