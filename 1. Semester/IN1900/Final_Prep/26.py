def poly_diff(p):
    dp = {}
    for key in p:
        dp[key-1] = p[key] * key

    return dp

p = {0:1, 2:-2, 4:3, 5:1}

print(poly_diff(p))
