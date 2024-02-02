class teque:

    def __init__(self):
        """
        """
        self.arraylist = []
    def push_back(self, x):
        """
        ALGORITHM: Push object x to the back of the queue
        Input: a value x to push back
        Output: None
        Procedure push_back(self, x)
            Remove(x)
            Append(x)
        """
        #self.remove(x)
        self.arraylist.append(x)
    def push_front(self, x):
        """
        ALGORITHM: Push object x to the front of the queue
        Input: a value x to push front
        Output: None
        Procedure push_front(self, x)
            Remove(x)
            Insert(0, x)
        """
        #self.remove(x)
        self.arraylist.insert(0, x)
    def push_middle(self, x):
        """
        ALGORITHM: Push object x to the middle of the queue
        Input: a value x to push middle
        Output: None
        Procedure push_middle(self, x)
            Remove(x)
            Insert(length of array / 2, x)
        """
        #self.remove(x)
        #print("length of list" + str(len(self.arraylist)))
        if (len(self.arraylist) % 2) == 0:
            length = int(len(self.arraylist) / 2)
        else:
            length = int(len(self.arraylist) / 2) + 1
        self.arraylist.insert(length, x)
        #print(f"inserte {x} at position {length}")
    def get(self, i):
        """
        ALGORITHM: Find object i in the queue
        Input: a value i to pull out
        Output: value at position i
        Procedure get(self, i)
            Return array[i]
        """
        #print("NOW GETTING ITEM, CURRENT ARRAYLIST:")
        #print(i)
        #print(self.arraylist)
        return self.arraylist[i]

import fileinput
if __name__ == '__main__':
    nyqueue = teque()
    for line in fileinput.input():
        if len(line) == 1:
            inputs = int(line)
        else:
            newline = line.split(' ')
            if newline[0] == 'push_back':
                nyqueue.push_back(int(newline[1]))
            if newline[0] == 'push_front':
                nyqueue.push_front(int(newline[1]))
            if newline[0] == 'push_middle':
                nyqueue.push_middle(int(newline[1]))
            if newline[0] == 'get':
                print(nyqueue.get(int(newline[1])))
