#Problem 7.6 Saving information in a nested dictionary

def read_person_data(filename):
    infile = open(filename, 'r')
    d = {}
    for line in infile:
        words = line.split(',')
        d[words[0]] = {'Age': words[1].strip(), 'Gender': words[2].replace('\n', '').strip()}
    return d

read_person_data('people.txt')

"""
kvale@Aleksanders-MBP Oppgaver % python3 people_dict.py
"""
