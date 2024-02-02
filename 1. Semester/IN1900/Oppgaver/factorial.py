#Problem 4.3 Implementing the factorial
from math import factorial

def myfactorial(n):
    if n==1:
        return n
    else:
        return n*myfactorial(n-1)

def test_myfactorial():
    from math import factorial
    x = 7
    computed = myfactorial(7)
    expected = factorial(7)
    success = computed == expected
    msg = "but it failed"
    assert success, msg
