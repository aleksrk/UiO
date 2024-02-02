# Problem 8.4 Extending the BankAccountP class

class BankAccountP:
    def __init__(self, first_name, last_name, number, balance):
        self._first_name = first_name
        self._last_name = last_name
        self._number = number
        self._balance = balance

    def deposit(self, amount):
        self._balance += amount

    def withdraw(self, amount):
        self._balance -= amount

    def get_balance(self):
        return self._balance

    def print_info(self):
        first = self._first_name; last = self._last_name
        number = self._number; bal = self._balance
        s = f'{first} {last}, {number}, balance: {bal}'
        print(s)
    def transfer(self, amount, account):
        self.withdraw(amount)
        account.deposit(amount)

a1 = BankAccountP('Truls', 'Pedro', 3762, 6000)
a2 = BankAccountP('Arne', 'Gravel', 3769, 9000)
a1.print_info()
a2.print_info()
a1.transfer(2000,a2)
a1.print_info()
a2.print_info()

"""
kvale@Aleksanders-MBP Oppgaver % python3 BankAccountP.py
Truls Pedro, 3762, balance: 6000
Arne Gravel, 3769, balance: 9000
Truls Pedro, 3762, balance: 4000
Arne Gravel, 3769, balance: 11000
"""

def test_BankAccountP():
    a1 = BankAccountP('Truls', 'Pedro', 3762, 500)
    a2 = BankAccountP('Arne', 'Gravel', 3769, 1000)
    a1.deposit(200)
    assert a1._balance == 700, 'bug in deposit function'
    a1.withdraw(200)
    assert a1._balance == 500, 'bug in withdraw function'
    a1.transfer(200,a2)
    assert a1._balance == 300 and a2._balance == 1200, 'bug in transfer function'
    assert a1.get_balance() == a1._balance, 'bug in get_balance'

"""
kvale@Aleksanders-MBP Oppgaver % pytest BankAccountP.py
============================= test session starts ==============================
platform darwin -- Python 3.7.3, pytest-6.0.2, py-1.9.0, pluggy-0.13.1
rootdir: /Users/kvale/Documents/UiO/IN1900/Oppgaver
collected 1 item

BankAccountP.py .                                                        [100%]

============================== 1 passed in 0.01s ===============================
"""
