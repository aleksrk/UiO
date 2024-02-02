def longest(a):
    max = 0

    for listo in a:
        length = len(listo)
        if length > max:
            max = len(listo)
    return max


lista = [[1,2,3,4], [1,2,3,4,5,6],[1,2]]

print(longest(lista))
