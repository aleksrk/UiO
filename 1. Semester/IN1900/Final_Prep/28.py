

def is_prime(n):
    num = 0
    for x in range(2, n):
        if (n % x) == 0:
            return print("false")
        else:
            return print("true")
