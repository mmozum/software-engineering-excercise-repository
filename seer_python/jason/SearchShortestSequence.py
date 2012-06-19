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

#def search2(haystack, target):
#    ''' tried to use an index. but it make the program look messy'''
#    index = {}
#    
#    for i,c in enumerate(haystack):
#        if(c not in index):
#            index[c] = []
#        index[c].append(i)
#    
#    pointers = [-1] + [0] * (len(target) - 1)
#    minLen = len(haystack) + 1
#    minStart = -1
#    
#    while(True):
#        pointers[0] += 1
#        if(pointers[0] >= len(index[target[0]])):
#            break
#        lastPointer = index[target[0]][pointers[0]]
#        notFound = False
#        for i in range(1, len(target)):
#            currentPointer = index[target[i]][pointers[i]]
#            while(currentPointer <= lastPointer and pointers[i] < len(index[target[i]]) - 1):
#                pointers[i] += 1
#                currentPointer = index[target[i]][pointers[i]]
#            if(currentPointer <= lastPointer and pointers[i] >= len(index[target[i]])-1):
#                notFound = True
#                break
#            lastPointer = currentPointer
#
#        if(not notFound):        
#            currentLen = index[target[-1]][pointers[-1]] - index[target[0]][pointers[0]]
#            if(currentLen > 0 and currentLen < minLen):
#                minLen = currentLen
#                minStart = index[target[0]][pointers[0]]
#            
#    if(minLen == len(haystack) + 1):
#        return []
#    
#    return haystack[minStart : minLen + 1]

def search3(haystack, target):
    '''Time: O(MN)
    Space: O(N)
    '''
    M = len(haystack)
    N = len(target)
    pointers = [0] * (N + 1)
    minLen = M + 1
    start = -1
    
    while(True):
        pointers[0] = pointers[1]
        notFound = False
        
        for i in range(N):
            p = max(pointers[i+1], pointers[i] + 1)
            while(p <= pointers[i] or (p < M and haystack[p] != target[i])):
                p += 1
            if(p == M):
                notFound = True
                break
            pointers[i+1] = p
        
        if(notFound):
            break
        currentLen = pointers[-1] - pointers[1] + 1
        if(currentLen < minLen):
            minLen = currentLen
            start = pointers[1]
            
    if(minLen <= M):
        return haystack[start: start+minLen]
    
    return []


            
def search5(haystack, target):
    '''
    Original DP:
    both time and space are: O(MN)
    '''
    M = len(haystack)
    N = len(target)
    
    dp = [[0] * (N+1) for _ in range(M+1)]
    dp[0][1:] = [M+1] * N

    for j in range(1, N+1):
        for i in range(1, M+1):
            if(haystack[i-1] == target[j-1]):
                dp[i][j] = dp[i-1][j-1] + 1
            else:
                dp[i][j] = dp[i-1][j] + 1
                
    li = [dp[i][N] for i in range(1, M+1)]
    
    minLen = min(li)
    end = li.index(minLen)
    
    return haystack[end - minLen + 1: end + 1]

if __name__ == '__main__':
    a = list("992134923034")
    b = list("234")
#    a = list("12314102312")
#    b = list("12312")
    print(search(a, b))
    print(search3(a, b))
    print(search5(a, b))
