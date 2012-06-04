'''
Created on May 24, 2012

@author: Jason Huang
'''

if __name__ == '__main__':
    T = int(input())
    
    UPPER_BOUND = 1000000
    for t in range(T):
        N = int(input())
        arr = [int(x) for x in input().split(" ")]

        tree = [0] * (UPPER_BOUND + 1)
        numSwap = 0
        cnt = 0
        for n in arr:
            
            frequency = 0
            idx = n
            while(idx > 0):
                frequency += tree[idx]
                idx -= idx & -idx
            numSwap += cnt - frequency
            
            idx = n
            while(idx <= UPPER_BOUND):
                tree[idx] += 1
                idx += idx & -idx
            
            cnt += 1
            
        print(numSwap)