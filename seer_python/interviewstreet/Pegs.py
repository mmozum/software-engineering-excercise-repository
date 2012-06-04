'''
Created on May 23, 2012

@author: Jason Huang
'''

if __name__ == '__main__':
    
    line = input()
    N,K = [int(x) for x in line.split(" ")]
    currentConfig = input()
    finalConfig = input()
    
    currentConfig = [int(x) - 1 for x in currentConfig.split(" ") ]
    finalConfig = [int(x) - 1 for x in finalConfig.split(" ") ]
    
    minStep = 8
    maxStep = 8
    moves = []
    minMoves = []
    
    def dfs(src, tgt, level):
        
        global minStep
        global moves
        global minMoves
        
        if(src == tgt):
            if(level < minStep):
                minStep = level
                minMoves[:] = moves
                return

        if(level >= maxStep):
            return
        
        for i, srcPeg in enumerate(src):
            for tgtPeg in range(K):
                
                if(tgtPeg == srcPeg):
                    continue
                
                tmpPile = [x for x in range(i, N) if(src[x] == srcPeg and tgt[x] != srcPeg)]
                
                if(not tmpPile):
                    continue
                
                if(move(src, i, srcPeg, tgtPeg)):
                    moves.append((srcPeg, tgtPeg))
                    dfs(src, tgt, level + 1)
                    moves.pop();
                    assert move(src, i, tgtPeg, srcPeg)
    
    def move(srcConfig, disc, srcPeg, tgtPeg):
        
        srcPile = [x for x in range(0, disc+1) if (srcConfig[x] == srcPeg)]
        tgtPile = [x for x in range(N) if(srcConfig[x] == tgtPeg)]
        
        if(srcPile[0] != disc):
            return False
        
        if(tgtPile and tgtPile[0] < disc):
            return False
        
        srcConfig[disc] = tgtPeg
        
        return True

    dfs(currentConfig, finalConfig, 0)
    
    print(minStep)
    for x,y in minMoves:
        print (x+1, " ", y+1)
    
