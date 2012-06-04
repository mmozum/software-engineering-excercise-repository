'''
Created on May 17, 2012

@author: Jason Huang

Given an input array of integers of size n, and a query array 
of integers of size k, find the smallest window of input array 
that contains all the elements of query array and also in the 
same order.
'''

def search(haystack, target):
    index = { c:[] for c in target }
    
    for i, c in enumerate(haystack):
        if c in target:
            index[c].append(i)
        
    cur = [ (i, i) for i in index[target[0]] ]
    
    for i in range(1, len(target)):
        p1 = p2 = 0
        nxt = []
        ind = index[target[i]]
        
        while(p2 < len(ind)):
            
            if(ind[p2] < cur[p1][1]):
                p2 += 1
                continue
            
            if(p1 >= len(cur) - 1 or cur[p1 + 1][1] > ind[p2]):
                nxt.append((cur[p1][0], ind[p2]))
                p2 += 1
            else:
                p1 += 1
        
        cur = nxt

    low = 1000
    minRange = ()
    
    for t in cur:
        if(t[1] - t[0] < low):
            low = t[1] - t[0]
            minRange = t

    if(low == 1000):
        return []
    
    return haystack[minRange[0]:minRange[1] + 1]


if __name__ == '__main__':
    #a = list("213492304")
    #b = list("234")
    a = list("12314102312")
    b = list("12312")
    print(search(a, b))
