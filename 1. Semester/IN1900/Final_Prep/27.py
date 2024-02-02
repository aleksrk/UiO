def min_max(a):
    max = a[0]
    min = a[0]
    for i in a:

        if i  > max:
            max = i

        if i < min:
            min = i

    return max, min


list = [0, 8, 8, -2, 9, 11]

print(min_max(list))
