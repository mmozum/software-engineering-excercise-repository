'''
Created on May 23, 2012

@author: Jason Huang
'''

N = 8

def bin2gray(n):
    return n ^ (n >> 1)


def gray2bin(n):
    i = 1 << (N - 2)

    while(i > 0):
        n = (n ^ (n >> 1)) & i | (n & ~i)
        i >>= 1
    
    return n


def printGray(n):
    '''
    Print a range of gray code recursively
    '''
    
    def add0(pre, i):
        if(i == 0):
            print(list(pre))
            return
        
        add0(pre + "0", i - 1)
        add1(pre + "1", i - 1)
        
    def add1(pre, i):
        if(i == 0):
            print(list(pre))
            return
        
        add0(pre + "1", i - 1)
        add1(pre + "0", i - 1)
    
    add0("0", n - 1)
    add1("1", n - 1)
    
def generateGray(n):
    '''
    Generate Gray code and store the result in a list
    '''
    
    if(n == 0):
        return []
    
    rslt = [0]
    
    for i in range(n):
        mask = 1 << i
        for j in range(len(rslt) - 1, -1, -1):
            rslt.append( rslt[j] | mask )
    
    return rslt

if __name__=='__main__':
    print([bin(i + (1 << N))[3:] for i in generateGray(4)])