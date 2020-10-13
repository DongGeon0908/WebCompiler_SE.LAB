#include<stdlib.h>
int main(void){
system("cp javaTest.txt javaTest.java");
system("javac javaTest.java 2> javaError.txt");
return 0;
}