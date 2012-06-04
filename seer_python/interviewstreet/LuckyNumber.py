'''
Created on May 25, 2012

@author: jjhuang
'''

def getAllPrimeNumber(N):
    ret = []
    
    for n in range(2, N + 1):
        
        isPrime = True
        for i in range(2, n//2 + 1):
            if(n % i == 0):
                isPrime = False
                break
        if(isPrime):
            ret.append(n)
    return ret


def buildCache(N):
    table1 = []
    table2 = []
    
    for x in range(N):
        a = 0
        b = 0
        while(x > 0):
            m = x % 10
            a += m
            b += m * m
            x //= 10
        table1.append(a)
        table2.append(b)
        
    return table1,table2


if __name__ == '__main__':
    
    #T = int(input())
    
    primeTable = set(getAllPrimeNumber(1500))

    
    
#    for t in range(T):

    #A,B = [int(x) for x in input().split(" ")]
    A,B = 1,1000000000
    
#    cnt = 0
#    n = A
#    while(n<=B):
#        a = 0
#        b = 0
#        nn = n
#        while(nn > 0):
#            d = nn % MOD
#            a += table1[d]
#            b += table2[d]
#            nn //= MOD
#        if(a in primeTable and b in primeTable):
#            cnt += 1
#        n += 1
#    print(cnt)


            
