'''
Created on Jun 5, 2012

@author: Jason Huang
'''
'''
Question 1 / 1.
Your task is to decode messages that were encoded with substitution ciphers. 
In a substitution cipher, all occurrences of a character are replaced by a 
different character. For example, in a cipher that replaces "a" with "d" and 
"b" with "e", the message "abb" is encoded as "dee".

The exact character mappings that are used in the substitution ciphers will 
not be known to you. However, the dictionary of words that were used will be 
given. You will be given multiple encoded messages to decode (one per line) 
and they may use different substitution ciphers. The same substitution cipher 
is used on all of the words in a particular message.

For each scrambled message in the input, your program should output a line 
with the input line, followed by the string " = " (without the quotes), 
followed by the decoded message.

NOTE: All inputs are from stdin and output to stdout. The input will be exactly 
like how it's given in the problem and

your output should exactly match the given example output

Example:

input file: 
//dict
hello
there 
yello
thorns
//secret
12334 51272
12334 514678
output:
12334 51272 = hello there
12334 514678 = hello thorns
'''

import sys

def buildMapping(secreteWordList, idx, wordsDict, translateMap):
    if(idx >= len(secreteWordList)):
        return True
    
    sct = secreteWordList[idx]
    trialSet = {}
    
    for word in wordsDict[len(sct)]:
        for dgt in trialSet:
            del translateMap[dgt]
        trialSet = set()

        j = 0
        while(j < len(sct)):
            dgt = sct[j]
            letter = word[j]
            if(dgt not in translateMap and letter not in translateMap.values()):
                translateMap[dgt] = letter
                trialSet.add(dgt)

            if(dgt not in translateMap or translateMap[dgt] != letter):
                break
            
            j += 1
        
        if(j < len(sct)):
            continue
        
        if(buildMapping(secreteWordList, idx + 1, wordsDict, translateMap)):
            return True
        
    return False
            
            
    

if __name__ == '__main__':
    
    sys.stdin.readline()
    line = sys.stdin.readline().strip()
    
    wordsDict = {}
    while(line != "//secret"):
        l = len(line)
        if(l in wordsDict):
            wordsDict[l].append(line)
        else:
            wordsDict[l] = [line]
        line = sys.stdin.readline().strip()
    
    secretList = []
    line = sys.stdin.readline().strip()
    while(line):
        secretList.append(line)
        secreteWordList = line.split(' ')
        translateMap = {' ':' '}
        buildMapping(secreteWordList, 0, wordsDict, translateMap)
        decodedMsg = ''.join([translateMap[x] for x in line])
        print(line,"=", decodedMsg)
        
        line = sys.stdin.readline().strip()
    
