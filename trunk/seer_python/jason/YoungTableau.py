'''
Created on May 17, 2012

@author: Jason Huang
'''

import random

def GenerateYoungTableau(m, n):
    
    buf = list(range(m * n))
    random.shuffle(buf)
    
    tab = []
    for i in range(n):
        tab.append( sorted(buf[ i * m : i * m + m]) )
    
    transposed = []
    for i in range(len(tab[0])):
        transposed.append( [l[i] for l in tab] )
    
    for l in transposed:
        l.sort()
    
    return transposed

def VerifyYoungTableau(tab):
    
    for i in range(len(tab) - 1):
        for j in range(len(tab[0]) - 1):
            if(tab[i][j] > tab[i+1][j] or tab[i][j] > tab[i][j+1]):
                return False
    return True
    

if __name__ == '__main__':
    
    tab = GenerateYoungTableau(6, 10)
    if(not VerifyYoungTableau(tab)):
            print("failed to generate")
    
    for l in tab:
        print(l)
