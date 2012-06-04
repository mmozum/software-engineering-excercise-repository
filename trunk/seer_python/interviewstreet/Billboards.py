'''
Created on May 25, 2012

@author: Jason Huang
'''

if __name__ == '__main__':
    rawIn = input().split(" ")
    N,K = int(rawIn[0]), int(rawIn[1])
    arr = []
    for i in range(N):
        arr.append(int(input()))
    arr.append(0)
    
    sumArr = [0] * (N + 2)
    for i in range(N+1):
        sumArr[i+1] = sumArr[i] + arr[i]
        
    dp = [0] * (N+1)
    for i in range(1, K + 1):
        dp[i] = sumArr[i]
    
    for i in range(K + 1, N+1):
        
        dp[i] = dp[i-1]
        for j in range(2, K + 2):
            dp[i] = max(dp[i], dp[i-j] + sumArr[i] - sumArr[i-j+1])
        
    
    print(dp[N])