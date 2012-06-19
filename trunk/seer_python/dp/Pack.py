'''
Created on Jun 17, 2012

@author: jjhuang
'''

def howMany(arr, n):
    dp = [0] * (n + 1)
    for v in arr:
        for i in range(v, n + 1):
            if(i == v):
                dp[i] += 1
            else:
                dp[i] += dp[i - v]
                
    f = open("5.txt", "w")
    for i in dp:
        f.write(str(i) + "\n")
    f.close();
    return dp[n]

def howMany2(prev, cur, n):
    
    fin = open(str(prev) + ".txt", "r")
    fout = open(str(cur) + ".txt", "w")
    
    buff = [0] * (cur + 1)
    idx1 = 0
    
    for i in range(cur):
        j = int(fin.readline())
        buff[idx1] = j
        idx1 = (idx1 + 1) % (cur + 1)
        fout.write(str(j) + "\n")
        
    for i in range(cur, n + 1):
        
        j = int(fin.readline())
        
        if(i == cur):
            j += 1
        else:
            j += buff[(idx1 + 1) % (cur + 1)]
        buff[idx1] = j
        idx1 = (idx1 + 1) % (cur + 1)
        fout.write(str(j) + "\n")
                
    fin.close();
    fout.close();
    
def howMany3(arr, n):
    arr = arr
    M = len(arr)
    N = n
    SIZE = 10001
    dp = [[0] * SIZE for _ in range(M + 1)]
    
    
    
    for i in range(1, M + 1):
        dp[i][0] = 1
        
    for i in range(1, N+1):
        for j in range(1, M+1):
            idx = -1
            if(i >= arr[j-1]):
                idx = (i - arr[j-1]) % SIZE
            dp[j][i % SIZE] = dp[j-1][i % SIZE]
            if(idx >= 0):
                dp[j][i % SIZE] += dp[j][idx]
            
    return dp[M][N % SIZE];


if __name__ == '__main__':
    n = 100000000
    #li = [1, 5, 10, 25, 100, 500, 1000, 2000, 5000, 10000]
    li = [1]
    #print(howMany(li, n))
    #howMany2(1, 5, n)
    #howMany2(5, 10, n)
#    howMany2(10, 25, n)
#    howMany2(25, 100, n)
#    howMany2(100, 500, n)
#    howMany2(500, 1000, n)
#    howMany2(1000, 2000, n)
#    howMany2(2000, 5000, n)
#    howMany2(5000, 10000, n)
    
    li = [1, 5, 10, 25, 100, 500, 1000, 2000, 5000, 10000]
    print(howMany3(li, 100000000))