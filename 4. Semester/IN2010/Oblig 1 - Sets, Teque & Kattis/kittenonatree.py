import fileinput

class tree:
    def __init__(self, mylist, start):
        """
        """
        self.start = start
        self.mylist = mylist
        self.solution = [start]
    def solve(self, x):
        """
        ALGORITHM: Find the shortest path from value x to root of tree
        Input: a value x to start at
        Output: an array with the nodes to follow to the root
        Procedure Solve(self, x)
            for line ∈ list do
                for val ∈ line do
                    if val = x then
                        array <- append val
                        Solve(val)
        """
        for line in self.mylist:
            newline = line.split(" ")
            for val in newline[1:]:
                if int(val) == x:
                    self.solution.append(int(newline[0]))
                    self.solve(int(newline[0]))
if __name__ == "__main__":
    val_list = []
    for line in fileinput.input():
        if len(line) > 3:
            val_list.append(line.rstrip('\n'))
        elif int(line) == -1:
            break
        else:
            start = int(line.rstrip('\n'))

    mytree = tree(val_list, start)
    mytree.solve(start)
    print(*mytree.solution)
