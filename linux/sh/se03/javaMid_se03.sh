#!bin/bash

docker restart se03

docker exec se03 sh -c "cd compile; rm -r SELAB.class;"
docker exec se03 sh -c "cd compile; rm -r javaError.txt;"
docker exec se03 sh -c "cd compile; rm -r javaResult.txt;"
docker exec se01 sh -c "cd compile; rm -r javaTest.java";

docker exec se03 sh -c "cd data; mv javaTest.txt ../compile"
docker exec se03 sh -c "cd compile; cp javaTest.txt javaTest.java"
docker exec se03 sh -c "cd compile; javac -encoding utf-8 javaTest.java 2> javaError.txt"
docker exec se03 sh -c "cd compile; java -Dfile.encoding=utf-8 SELAB > javaResult.txt"
docker exec se03 sh -c "cd compile; mv javaResult.txt ../data"
docker exec se03 sh -c "cd compile; mv javaError.txt ../data "

