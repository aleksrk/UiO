import fileinput

class Balance:
    def __init__(self):
        self.balanced_list = []
    def balance(self, list):
        """
        ALGORITHM: Balance a sorted array to input into BST
        Input: a list to sort
        Output: a balanced list balanced_list
        Procedure Balance(self, list)
            balanced_list += list(len(list) / 2)
            if len(list) > 2 then
                right_side = list(from len(list) / 2 + 1 to end)
                balance(right_side)
                left_side = list(from start to len(list) / 2)
                self.balance(left_side)
            else do
                balanced_list += list[0]

        """
        self.balanced_list.append(list[int(len(list) / 2)])
        if len(list) > 2:
            right_side = list[(int(len(list) / 2))+1:]
            self.balance(right_side)
            left_side = list[:(int(len(list) / 2))]
            self.balance(left_side)
        else:
            self.balanced_list.append(list[0])
    def print(self):
        for val in self.balanced_list:
            print(str(val))

if __name__ == '__main__':
    thislist = []

    for line in fileinput.input():
        thislist.append(int(line.rstrip('\n')))

    bal = Balance()
    bal.balance(thislist)
    bal.print()
