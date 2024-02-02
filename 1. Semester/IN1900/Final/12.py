def is_prime(n):
    for i in range(2,int(n**0.5)+1):
        if n%i==0:
            return False

    return True


def primes(n):
    p = []

    for i in range(2, n+1):
        if is_prime(i):
            p.append(i)

    return p


x = 7

print(is_prime(7))
print(primes(63))
