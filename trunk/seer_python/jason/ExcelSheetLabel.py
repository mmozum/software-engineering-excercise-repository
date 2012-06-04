'''
Created on Jun 1, 2012

@author: Jason Huang

'''

def printLabel(n):
    '''
    Given the sequence S1 = {a,b,c,d, ...,x,y,z,aa,ab,ac,... } and 
    given that this sequence corresponds (term for term) to the 
    sequence S2 = {0,1,2,3,....}. Write code to convert an element 
    of S2 to the corresponding element of S1. 
    '''
    
    li = []
    li.append( chr(ord('a') + n % 26) )
    
    n //= 26
    while(n > 0):
        li.append( chr(ord('a') + (n + 25) % 26) )
        n = (n-1) // 26
    
    li.reverse()
    return("".join(li))
    

def printLabelII(n):
    '''
    Given the sequence S1 = {a,b,c,d, ...,x,y,z,aa,ab,ac,... } and 
    given that this sequence corresponds (term for term) to the 
    sequence S2 = {1,2,3,....}. Write code to convert an element 
    of S2 to the corresponding element of S1. 
    '''
    
    li = []
    
    while(n > 0):
        li.append( chr(ord('a') + (n + 25) % 26) )
        n = (n-1) // 26
    
    li.reverse()
    return("".join(li))

#def printLabel(n):
#    
#    n += 1
#    li = []
#    
#    while(n > 0):
#        a = n % 26
#        if(a == 0):
#            li.append('z')
#            n -= 1
#        else:
#            li.append( chr(ord('a') + (a + 25) % 26) )
#        n //= 26
#    
#    li.reverse()
#    print("".join(li))

    

if __name__ == '__main__':
    for i in range(1000000):
        a = printLabel(i)
        b = printLabelII(i + 1)
        if( a != b ):
            print(i," ", a, " ", b)

#    printLabel(26)
#    print()
#    printLabel(2 * 26 - 1)
#    printLabel(2 * 26)
#    print()
#    printLabel(26 * 26 - 1)
#    printLabel(26 * 26)
#    printLabel(26 * 26 + 1)
#    print()
#    printLabel(26 * 26 * 26 - 1)
#    printLabel(26 * 26 * 26)
#    printLabel(26 * 26 * 26 + 1)
    
    

