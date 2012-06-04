'''
Created on May 23, 2012

@author: Jason Huang
'''

def countBitRange(a, b):
    
    if(a >= 0):
        rslt = countBitsMine(b)
        if(a > 0):
            rslt -= countBitsMine(a - 1)
        return rslt
    
    if(b <= 0):
        rslt = countBitsMine(a)
        if(b < 0):
            rslt -= countBitsMine(b + 1)
        return rslt
    
    return countBitsMine(a) + countBitsMine(b)

def countBitsMine(n):
    '''my own impl of couting 1s from 0 to n (n >= 0)
    or n to 0 (n < 0)
    '''

    if(n < 0):
        return 32 * (2 ** 31) - countBitsMine((2 ** 32) + n - 1)
        
    total = 0
    
    for i in range(31, -1, -1):
        mask = 1 << i
        if(n & mask):
            total += int(i * (2 ** (i-1)))
            n = (n & (~mask))
            total += n + 1
            
    return total

def countBit(n):
    
    if(n < 0):
        return 32 * (2 ** 31) - countBit((2 ** 32) + n - 1)
    
    if(n == 0):
        return 0
    
    if(n%2 == 0):
        return popcount(n) + countBit(n-1)
    
    return (n+1)//2 + 2 * countBit(n // 2)



def popcount(n):
    
    if(n < 0):
        n += 2 ** 32
    
    cnt = 0
    while(n > 0):
        cnt += 1
        n &= (n-1)
    return cnt

    
    
if __name__ == '__main__':
    T = int(input())
    
    for t in range(T):
        a,b = [int(x) for x in input().split(" ")]
        print(countBitRange(a,b))