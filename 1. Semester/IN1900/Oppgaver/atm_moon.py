#Problem 7.4

def readfile(filename):
    infile = open(filename, 'r')
    d = {}
    next(infile)
    for line in infile:
        words = line.split(';')
        for i in words:
            newsplit = i.split('-')
            name = newsplit[0].strip().upper()
            value = newsplit[1].strip().strip(',')
            value = value.replace(',','')
            d[name] = value
    return d

readfile('atm_moon.txt')

"""
kvale@Aleksanders-MBP Oppgaver % python3 atm_moon.py
"""
