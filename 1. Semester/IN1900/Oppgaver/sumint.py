#Problem 4.2 Sum of Integers

def sumint(n):
    sum = 0
    for i in range(1,n+1):
        sum += i
    return sum


def sumfast(n):
    return ( (n*(n+1)) / 2)

if sumfast(9) == sumint(9):
    print("yes")

def test_sumint():
    n = 3
    computed = sumint(n)
    expected = 6
    success = computed == expected
    msg = "it failed son"
    assert success, msg

def test_sumfast():
    n = 3
    computed = sumfast(n)
    expected = 6
    success = computed == expected
    msg = "it failed son"
    assert success, msg
