#include <iostream>
#include <string>

std::string hello() {
  std::cout << "Hello GraalVM!" << std::endl;
  return "Hello GraalVM!";
}

int main() {
  hello();
  std::cout << "Hello, GraalVM!" << std::endl;
  return 0;
}