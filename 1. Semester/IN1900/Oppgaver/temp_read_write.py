#Problem 5.10 Read Temperatures from Two Files
import numpy as np

def extract_data(filename):
    with open(filename, 'r') as infile:
        list1 = []
        infile.readline()
        for line in infile:
            for words in line.split():
                list1.append(float(words))
    return list1

for filename in ['temp_oct_1945.txt', 'temp_oct_2014.txt']:
    data = extract_data(filename)
    array = np.array(data)

    mean = np.mean(array)
    max = np.max(array)
    min = np.min(array)

    print(mean, max, min)

def write_formatting(filename, list1, list2):
    with open(filename, 'w') as outfile:
        outfile.write(f'1945  2014 \n \n')
        for t1, t2 in zip(list1, list2):
            outfile.write(f'{str(t1)}   {str(t2)} \n')

write_formatting('temp_formatted.txt', oct_1945, oct_2014)
