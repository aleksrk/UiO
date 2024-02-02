initial_amount = 100
r = 5.5 # interest rate
amount = initial_amount
years = 0

while amount <= 1.5*initial_amount:
    amount += r/100*amount
    years += 1

print(years)

#prints 8 years, meaning it will take 8 years for the initial amount to increase by 50%
