#!bin/bash

docker restart se03

docker exec se03 sh -c "cd compile; rm -r javascriptTest.js;"
docker exec se03 sh -c "cd compile; rm -r javascriptError.txt;"
docker exec se03 sh -c "cd compile; rm -r javascriptResult.txt;"
docker exec se03 sh -c "cd compile; rm -r javascriptTest.java";

docker exec se03 sh -c "cd data; mv javascriptTest.txt ../compile"
docker exec se03 sh -c "cd compile; cp javascriptTest.txt javascriptTest.js"
docker exec se03 sh -c "cd compile; node javascriptTest.js > javascriptResult.txt 2> javascriptError.txt"
docker exec se03 sh -c "cd compile; mv javascriptResult.txt ../data"
docker exec se03 sh -c "cd compile; mv javascriptError.txt ../data "

