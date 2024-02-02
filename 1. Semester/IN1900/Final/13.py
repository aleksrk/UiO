data = {}

with open('counties.txt') as infile:
    infile.readline()
    infile.readline()

    for line in infile:
        w = line.split()
        if len(w) == 2:
            data[w[0]] = w[1]
        elif len(w) == 4:
            key_list = [w[0], w[1], w[2]]
            key = ' '.join(key_list)
            data[key] = w[3]

        #key_list = (w[0])
        #data = (w[1])
        #stars_data[key] = data

print(data)
