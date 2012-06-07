'''
Created on June 5, 2012

@author: Jason Huang
'''

'''
Question 1 / 1.
There are N objects kept in a row. The ith object is at position x_i. You want to partition them into K groups. You want to move all objects belonging to the same group to the same position. Objects in two different groups may be placed at the same position. What is the minimum total amount by which you need to move the objects to accomplish this?

Input:
The first line contains the number of test cases T. T test cases follow. The first line contains N and K. The next line contains N space seperated integers, denoting the original positions x_i of the objects.

Output:
Output T lines, containing the total minimum amount by which the objects should be moved.

Constraints:
1 <= T <= 1000
1 <= K <= N <= 200
0 <= x_i <= 1000

Sample Input:
3
3 3
1 1 3
3 2
1 2 4
4 2
1 2 5 7

Sample Output:
0
1
3

Explanation:

For the first case, there is no need to move any object.
For the second case, group objects 1 and 2 together by moving the first object to position 2.
For the third case, group objects 1 and 2 together by moving the first object to position 2 and group objects 3 and 4 together by moving object 3 to position 7. Thus the answer is 1 + 2 = 3.

'''
    
    
if __name__ == '__main__':
    T = int(input())
    
    for t in range(T):
        N,K = [int(x) for x in input().split(" ")]
        arr = [int(x) for x in input().split(" ")]
        arr.sort()
        
        spaceArr = []
        for i in range(len(arr) - 1):
            spaceArr.append((arr[i+1] - arr[i], i))

        spaceArr.sort(reverse=True)
        
        splitArr = [spaceArr[i][1] for i in range(K-1)]
        
        splitArr.sort()
        
        start = 0
        
        cost = 0
        for end in splitArr:
            target = arr[(start + end) // 2]
            for i in range(start, end + 1):
                cost += (arr[i] - target)
            start = end + 1
        
        end = len(arr) - 1
        target = arr[(start + end) // 2]
        for i in range(start, end + 1):
            cost += (arr[i] - target)
            
        print(cost)
