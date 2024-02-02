def f(x, y):
    values = []
    for val in y:
        if val < 0 or val == 0:
            values.append(3*x**2*val - 3*x*val)
        elif val > 0:
            values.append(3*x**2*val + 3*x*val)
        else:
            return 'fault'
    return values


x = 1
y = [1,0,-2]

print(f(x,y))
