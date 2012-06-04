'''
Created on May 25, 2012

@author: Jason Huang
'''

if __name__ == '__main__':
    T = int(input())
    for t in range(T):
        N = int(input())
        arr = [int(x) for x in input().split(" ")]
        arr.sort()
        
        dp = 1
        k = 0
        for i in range(N):
            
            while(k < N and arr[k] <= i):
                k += 1
            
            if(k <= i):
                dp = 0
                break
            else:
                dp = dp * (k - i) % 1000000007
        
        print (dp)