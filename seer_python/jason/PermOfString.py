'''
Created on May 17, 2012

@author: Jason Huang
'''

import itertools

def superset(st):
    '''
    Find all subset of a string.
    '''
    N = len(st)
    
    for i in range( 1 << N ):
        s = [c for j,c in enumerate(st) if (i & (1 << j) > 0)]
        print(s)


def doPerm(curr, remain):
    
    if not remain:
        print(curr)
        
    for i in range(len(remain)):
        doPerm(curr + remain[i], remain[:i] + remain[i+1:])


def perm(st):
    '''
    Find all permutations of a string.
    '''
    doPerm("", list(st))
    
        

if __name__ == '__main__':
    #perm("asdf")
    for x in itertools.permutations("asdf"):
        print(x)