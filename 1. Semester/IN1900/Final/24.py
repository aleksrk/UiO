
stars_data = {}

with open('constants.txt') as infile:
    infile.readline()
    infile.readline()

    for line in infile:
        w = line.split()
        key_list = [w[0], w[1]]
        print(key_list)
        key = ' '.join(key_list)
        data = (w[2], w[3])
        stars_data[key] = data
