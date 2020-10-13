#!bin/bash

docker restart se02

docker exec se02 sh -c "cd compile; rm -r pythonTest.py;"
docker exec se02 sh -c "cd compile; rm -r pythonError.txt;"
docker exec se02 sh -c "cd compile; rm -r pythonResult.txt;"
docker exec se02 sh -c "cd compile; rm -r pythonTest.java";

docker exec se02 sh -c "cd data; mv pythonTest.txt ../compile"
docker exec se02 sh -c "cd compile; cp pythonTest.txt pythonTest.py"
docker exec se02 sh -c "cd compile; python pythonTest.py > pythonResult.txt 2> pythonError.txt"
docker exec se02 sh -c "cd compile; mv pythonResult.txt ../data"
docker exec se02 sh -c "cd compile; mv pythonError.txt ../data "

