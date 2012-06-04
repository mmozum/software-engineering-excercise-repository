'''
Created on May 23, 2012

@author: Jason Huang
'''


def solveNQueens_iteratively(N):
    board = [0] * (N + 1) # first element is not used
    
    rowSet = [False] * (N + 1) 
    fsSet = [False] * (2 * N + 1) 
    bsSet = [False] * (2 * N + 1)
    
    index = 1
    
    while(index > 0):
        
        if(index > N):
            print(board[1:])
            index -= 1
        else:
            j = board[index]
            
            if(0 < j and j <= N):
                rowSet[j] = False
                bsSet[index + j] = False
                fsSet[N+1 - index + j] = False
            
            j += 1
            while(j <= N and
                  (rowSet[j] or 
                   bsSet[index + j] or
                   fsSet[N + 1 - index + j])):
                j += 1
            
            if(j > N):
                board[index] = 0
                index -= 1
            else:
                rowSet[j] = True
                bsSet[index + j] = True
                fsSet[N+1 - index + j] = True
                board[index] = j
                index += 1
                
def solveNQueens_recursively(N):
    
    board = [0] * N
    rowSet = [False] * N
    bsSet = [False] * 2 * N
    fsSet = [False] * 2 * N
    
    def dfs(n):
        if(n == N):
            print(board)
            return
        for i in range(N):
            if(not rowSet[i] and not bsSet[i + n] and not fsSet[N - 1 -n + i]):
                rowSet[i] = True
                bsSet[i + n] = True
                fsSet[N - 1 - n + i] = True
                board[n] = i
                dfs(n + 1)
                rowSet[i] = False
                bsSet[i + n] = False
                fsSet[N - 1 - n + i] = False

    dfs(0)

if __name__=='__main__':
    solveNQueens_recursively(8)