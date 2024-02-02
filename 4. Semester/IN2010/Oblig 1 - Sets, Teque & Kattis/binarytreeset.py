class binarytreeset:

    def __init__(self):
        self.root = None

    def search(self, x):
        if self.root == None:
            return None

        return self._search(self.root, x)

    def _search(self, v, x):

        if v == None:
            return None
        if v.value == x:
            return v
        if x < v.value:
            return self._search(v.left, x)
        if x > v.value:
            return self._search(v.right,x)

    def insert(self, x):

        if self.root is None:
            self.root = Node(x)
        else:
            self._insert(self.root, x)

    def _insert(self, v, x):

        if x < v.value:
            if v.left is not None:
                self._insert(v.left, x)
            else:
                v.left = Node(x)
        if x > v.value:
            if v.right is not None:
                self._insert(v.right, x)
            else:
                v.right = Node(x)

    def _remove(self, v, x):

        if v is None:
            return None
        if x < v.value:
            v.left = self._remove(v.left, x)
            return v
        if x > v.value:
            v.right = self._remove(v.right, x)
            return v
        if v.left is None:
            return v.right
        if v.right is None:
            return v.left
        u = self.find_smallest(v.right)
        v.value = u.value
        v.right = self._remove(v.right, u.value)
        return v

    def remove(self, x):
        if self.root == None:
            return
        self.root = self._remove(self.root, x)

    def find_smallest(self, v):

        while v.left is not None:
            v = v.left
        return v

    def size(self, v):

        s = 1
        if v.left is not None:
            s += self.size(v.left)
        if v.right is not None:
            s += self.size(v.right)
        return s

    def PrintTree(self):

        if self.root is not None:
            self._PrintTree(self.root)

    def _PrintTree(self, node):
        if node is not None:
            self._PrintTree(node.left)
            print(str(node.value) + ' ')
            self._PrintTree(node.right)

class Node:
    def __init__(self, x):
        self.left = None
        self.right = None
        self.value = x
import fileinput
if __name__ == '__main__':
    tree = binarytreeset()
    for line in fileinput.input():
        newline = line.split(' ')
        for i in range(0, len(newline)):
            newline[i] = newline[i].rstrip('\n')
        if (newline[0] == 'insert'):
            tree.insert(int(newline[1]))
        elif (newline[0] == 'remove'):
            val = tree.search(int(newline[1]))
            tree.remove(int(newline[1]))
        elif (newline[0] == 'contains'):
            if tree.search(int(newline[1])) == None:
                print('false')
            else:
                print('true')
        elif (newline[0] == 'size'):
            """
            """
            print(tree.size(tree.root))
            #tree.PrintTree()
