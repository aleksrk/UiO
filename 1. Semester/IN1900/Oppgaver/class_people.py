# Problem 8.1 Saving information in a class

class Person:
    def __init__(self, name, age, gender):
        self.name = name
        self.age = age
        self.gender = gender

    def nameChange(self, name):
        self.name = name

    def ageChange(self, age):
        self.age = age

    def genderChange(self, gender):
        self.gender = gender

    def __str__(self):
        return f'Name: {self.name}, Age: {self.age}, Gender: {self.gender}'

John = Person('John', 55, 'Male')
print(John)

John.nameChange('Petronella')
John.genderChange('Undefined')
print(John)

"""
kvale@Aleksanders-MBP Oppgaver % python3 class_people.py
Name: John, Age: 55, Gender: Male
Name: Petronella, Age: 55, Gender: Undefined
"""
