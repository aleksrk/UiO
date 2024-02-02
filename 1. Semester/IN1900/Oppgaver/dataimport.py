infile = open('data.txt', 'r')

import matplotlib.pyplot as plt

year = []
meantemp = []
maxtemp = []
mintemp = []

infile.readline()

for line in infile:
    words = line.split()
    year.append(words[1])
    meantemp.append(words[2])
    maxtemp.append(words[3])
    mintemp.append(words[4])

plt.plot(year, meantemp, label='meantemp')
plt.plot(year, maxtemp, label='maxtemp')
plt.plot(year, mintemp, label='mintemp')
plt.legend()
plt.xlabel('Year')
plt.ylabel('Temperature')
plt.show()
