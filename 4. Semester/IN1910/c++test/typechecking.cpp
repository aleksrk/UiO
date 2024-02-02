string city = "Oslo";  // må også bruke #include <string>
char a = 'a';   // Merk ' for char mens " for strenger
int year = 2018;
double temp = 42.3;

// works, but not optimal. we'd rather use:
string city{"Oslo"};
char a{'a'};
int year{2018};
double temp{42.3};
//this does two things,declares the variable and does a typecheck. is {x} of equal type to the assertion
