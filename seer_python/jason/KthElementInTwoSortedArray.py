'''
Created on May 25, 2012

@author: Jason Huang
'''

def getV(a, i):
    if(i < 0):
        return -float('inf')
    if(i >= len(a)):
        return float('inf')
    return a[i]
    
def findK(a, b, K):
    '''binary search based approach
    '''
    
    def findKImpl(a, b, k):
        lo = 0
        hi = len(a) - 1
        
        while(hi >= lo):
            mi = (hi + lo) >> 1
            j = k - mi - 1
            
            if(a[mi] >= getV(b,j-1) and a[mi] <= getV(b, j)):
                return a[mi]
            if(a[mi] >= getV(b, j)):
                hi = mi - 1
            else:
                lo = mi + 1
        return None
    
    ret = findKImpl(a, b, K)
    
    if(ret == None):
        ret = findKImpl(b, a, K)
        
    return ret


def findK2(a, b, K):
    '''another approach
    '''
    
    return findK2Impl(a, 0, len(a) - 1, b, 0, len(b) - 1, K)

def findK2Impl(a, s, e, b, s2, e2, K):
    
    if(s > e):
        return findK2Impl(b, 0, len(b)-1, a, 0, len(a)-1, K)
    
    i = (s + e) >> 1
    j = K - i - 1 + s
    
    if(a[i] >= getV(b, j-1) and a[i] <= getV(b, j)):
        return a[i]
    
    if(a[i] < getV(b, j-1)):
        return findK2Impl(a, i + 1, e, b, s2, e2, K)
    return findK2Impl(a, s, i-1, b, s2, e2, K)
    

if __name__ == '__main__':
    
    a = [1, 3, 5]
    b = [1, 3, 4]
    
    for k in range(1,4):
        print(findK2(a, b, k))
