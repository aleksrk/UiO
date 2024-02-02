import fileinput
import heapq

class Balance:
    def __init__(self):
        """
        """
    def balance(self, heap):
        size = int(len(heap) / 2)
        h_list = []

        if size == 0:
            if len(heap) > 0:
                print(heapq.heappop(heap))
            return
        else:
            for value in range(size):
                heapq.heappush(h_list, heapq.heappop(heap))
            print(heapq.heappop(heap))
            self.balance(heap)
            self.balance(h_list)

if __name__ == '__main__':
    heap = []
    for line in fileinput.input():
        heapq.heappush(heap, int(line.rstrip('\n')))

    bal = Balance()
    bal.balance(heap)
