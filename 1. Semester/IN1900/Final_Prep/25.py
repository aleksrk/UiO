
def poly_eval(p, x):
    eval = 0
    for key in p:
        eval += p[key]* x**key

    return eval

p = {0:1, 2:-2, 4:3, 5:1}

print(poly_eval(p, 1))
