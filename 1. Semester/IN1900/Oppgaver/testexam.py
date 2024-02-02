import sys

from math import exp



try:

    h = float(sys.argv[1])

except IndexError:

    print("Missing input argument.")

    exit()

except ValueError:

    print("The argument is not a number.")

    exit()



p0 = 100

h0 = 8400



print(p0*exp(-h/h0)
