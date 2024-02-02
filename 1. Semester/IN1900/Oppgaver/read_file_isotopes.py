infile = open('Oxygen.txt', 'r')
w = []
Na = []
next(infile) # read through the first line as we do not need information from it
for line in infile: # loop that appends correct values from Oxygen.txt to the lists w and NA
    words = line.split()
    w.append(float(words[1]))
    Na.append(float(words[2]))

def MassCalc(w_list, Na_list): # function for calculating molar mass of all isotopes
    sum = 0
    for i in range(len(w_list)):
        sum += w_list[i] * Na_list[i]
    print(f'{sum:.4f} g/mol')
MassCalc(w, Na)

"""
kvale@Aleksanders-MBP Oppgaver % python3 read_file_isotopes.py
15.9994 g/mol
"""
