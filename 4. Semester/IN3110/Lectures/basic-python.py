import random

# Let's generate random pairs with the idea of sorting them later
random_tuples = []
for i in range(10):
    random_tuples.append((random.random(), random.random()))
#print(random_tuples)

new = sorted(random_tuples)
#print(new)
new_l2 = sorted(random_tuples, key=lambda t: (t[0]**2 + t[1]**2)**0.5)
#print(new_l2)



# The idea is that foos[1](x) returns x+1
foos = [lambda x: x+n for n in range(5)]
# But ...
for f in foos:
    print(f(0))

# Solution [referred to as currying]
foos = [lambda x, n=n: x+n for n in range(5)]
for f in foos:
    print(f(0))

selected = filter(lambda p: p[0] < 0.5, random_tuples)
# Not the answers but ...
print(selected)

next(selected)

for item in selected:
    print(item)



 # Keep only the elements in iterable for which the function is true
selected = filter(lambda p: p[0] < 0.5, random_tuples)
# Apply sum function to all the elements in iterable
processed = map(sum, selected)

# Option 1) to comsume and turn into a list
from functools import reduce
# combine first two items of iterable to make the input
# for next round while the other argument is the next item in iterable
reduce(lambda x, y: x+y, processed)



from itertools import product

x = range(1, 5)
y = range(4, 12)
grid = product(x, y)
# Get them all
print(list(grid))



def fibs():
    '''Generate Fibonacci numbers'''
    a, b = 0, 1
    while True:
        a, b = a+b, a
        yield a   # Yield keywors makes this function a generator

numbers = fibs()



# Let get first ten
for i, num in zip(range(5), numbers):
    print(i, num)
# NOTE: zip - pairs iterables into tuples, terminating when one of them is exhaused [range(5) determines this above]
# We can run this many times.
