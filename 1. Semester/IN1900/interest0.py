# Chapter 2.1 to 2.3
print(100*(1 + 5.0/100)**7)

initial_amount = 100
interes_rate = 0.5
number_of_years = 7
final_amount = initial_amount * (1+interes_rate/initial_amount)**number_of_years
print(final_amount)

t = 0.6
t = t + 0.1
print(t) # prints the last edited version of t, in this case 0.7

a = 5
print(a == 5)

# program for computing the growth
# of money deposited into a bank
primary = 100 # initial amount in bank
r = 0.5       # interest rate in %
n = 7         # the number of years
amount = primary * (1+r/primary)**n
print(amount)

hello = "Hello World" # quotation marks defines a string
print(hello)

print(type(hello)) # to locate the type of class we can use print(type())
print(type(r))
print(type(n))
print(type(primary))

x1 = 2 # numbers are auto assigned as integers
x2 = "2" # numbers in quotation marks are still strings
print(x1+x1)
print(x2+x2)

x1 = float(x1)
x2 = float(x2) # strings can be converted to integers using float(x)
print(x1+x1)
print(x2+x2)

print(primary,final_amount)
print(f"After {n} years {primary} will grow to {final_amount}")

print(f"2+2 = {2+2}")

t = 1.234567
print(f"Default output gives {t}")
print(f"We can set the precision: t = {t:.2}.") # rounding down to 2 numbers only?
print(f"Or control the number of decimals: t = {t:.2f}") # define number of decimals we want it to prints

print(f"We may set the space used for the output: t = {t:8.2f}") # the number before the decimal definition
# will define the amount of spaces between the string and the output

r = 87
print(f"Integer set to occupy exactly 8 chars of space: r = {r:8d}")

a = 7836574272.12
b = 1.2345
print(f"Without the format specifier: a = {a}, b = {b}.")
print(f"With the format specificer: a = {a:g}, b = {b:g}.")
