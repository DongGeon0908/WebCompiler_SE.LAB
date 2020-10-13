#include<stdlib.h>
int main(void){
system("cp cTest.txt cTest.c");
system("gcc -o cTest cTest.c 2> cError.txt");
}