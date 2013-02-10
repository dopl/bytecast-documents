#include <stdio.h>

int fun1(int param1, int param2){
  printf("fun1: [%d %d]\n", param1, param2);
}

int fun2(int param1, int param2){
  printf("fun2: [%d %d]\n", param1, param2);
}

int main(int argc, char * argv[]){

  fun1(10, 20);
  fun2(30, 40);
  fun1(50, 60);
  return 0;
}
