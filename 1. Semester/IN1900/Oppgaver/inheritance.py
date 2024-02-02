# Problem 9.4 Inheritance

class Mammal:
    def info(self):
        return 'I have hair on my body'
    def identity_mammal(self):
        print('I am a mammal')

class Primate(Mammal):

    def info(self):
        return Mammal.info(self) + '. I have a large brain'
    def identity_primate(self):
        print('I am a Primate')

class Human(Primate):

    def info(self):
        return Primate.info(self) + '. I am the smartest.'
    def identity_human(self):
        print('I am a Human')

class Ape(Primate):
    def info(self):
        return Primate.info(self) + '. I evolved into Humans.'
    def identity_ape(self):
        print('I am an Ape')

John = Human()
Julius = Ape()

print(John.info())
#John.identity_ape() #this fails due to the instance John not having an identity ape
John.identity_human()
John.identity_mammal()
John.identity_primate()
print(Julius.info())
Julius.identity_ape()
#Julius.identity_human() # this fails due to the instance Julius not having an identity ape
Julius.identity_mammal()
Julius.identity_primate()

print(isinstance(John, Mammal))
print(isinstance(John, Primate))
print(isinstance(John, Human))
print(isinstance(John, Ape))
print(isinstance(Julius, Mammal))
print(isinstance(Julius, Primate))
print(isinstance(Julius, Human))
print(isinstance(Julius, Ape))

"""
kvale@Aleksanders-MBP Oppgaver % python3 inheritance.py
I have hair on my body. I have a large brain. I am the smartest.
I am a Human
I am a mammal
I am a Primate
I have hair on my body. I have a large brain. I evolved into Humans.
I am an Ape
I am a mammal
I am a Primate
True
True
True
False
True
True
False
True
"""
