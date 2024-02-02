#Problem 7.2 Chemical elements in a dictionary

elements_10 = {1: '-', 2: 'Helium', 3: 'Lithium',
         4: 'Beryllium', 5: 'Boron', 6: 'Carbon',
         7: 'Nitrogen', 8: '-',9: 'Fluorine',
         10: 'Neon'}

elements_10[1] = 'Hydrogen'
elements_10[8] = 'Oxygen'

elements_10_copy = elements_10.copy() # creates a copy of elements_10 that are not linked
elements_10_copy.update({11: 'Sodium'}) # updates the copied dictionary with a new value

print(elements_10) # prints the original elements_10 dictionary
print('\n') # seperates with a line break

elements_11 = elements_10 # the new dictionary elements_11 is now a reference to elements_10
elements_11.update({11: 'Sodium'}) # because elements_11 and elements_10 are now reference dictionaries,
                                   # any changes made to elements_11 will also updated elements_10

print(elements_10) # prints the 'new' elements_10 that has a reference from elements_11


"""
kvale@Aleksanders-MBP Oppgaver % python3 chemical_elements_dict.py
{1: 'Hydrogen', 2: 'Helium', 3: 'Lithium', 4: 'Beryllium', 5: 'Boron',
6: 'Carbon', 7: 'Nitrogen', 8: 'Oxygen', 9: 'Fluorine', 10: 'Neon'}


{1: 'Hydrogen', 2: 'Helium', 3: 'Lithium', 4: 'Beryllium', 5: 'Boron',
6: 'Carbon', 7: 'Nitrogen', 8: 'Oxygen', 9: 'Fluorine', 10: 'Neon', 11: 'Sodium'}
"""
