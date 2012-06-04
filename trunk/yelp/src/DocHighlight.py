'''
Created on May 27, 2012

'''

import heapq
import re

def build_doc_index(doc):
    """
    Given a document string, construct index.
    Args:
    doc - a string to be indexed
    Returns:
    a dictionary with key being each distinct word in the doc string 
    and value being a list of positions where this word occurs
    """
    
    doc = doc.lower()
    index = {}
    overallIndex = 0
    splitRegex = '(\W+)'
    
    for word in re.split(splitRegex, doc):
        if (len(word) >= 2 and not re.match(splitRegex, word)):
            if word in index:
                index[word].append(overallIndex)
            else:
                index[word] = [overallIndex]
        overallIndex += len(word)
            
    return index


def build_query_dict(query):
    """
    Given a query string, construct index.
    Args:
    query - a string to be indexed
    Returns:
    a dictionary with key being each distinct word in the query string 
    and value being number of occurrence in query string
    """
    
    query = query.lower()
    
    queryDict = {}
    for word in re.split('\W+', query):
        if(len(word) < 2):
            continue
        
        if word in queryDict:
            queryDict[word] += 1
        else:
            queryDict[word] = 1
            
    return queryDict


def calculate_snippet(docIndex, queryDict):
    """
    Args:
    docIndex - doc index constructed from build_doc_index
    queryDict - query dictionary constructed from build_query_dict
    Returns:
    start,end as two integers represent the postion of the snippet
    """
    
    maxIndex = 0
    pq = []
    
    queryPointers = {k:0 for k in queryDict}
    
    for word in queryDict:
        for _ in range(queryDict[word]):
            currentIndex = docIndex[word][queryPointers[word]]
            heapq.heappush(pq, (currentIndex, word))
            
            currentIndex += len(word)
            if(currentIndex > maxIndex):
                maxIndex = currentIndex
            queryPointers[word] += 1

    start = pq[0][0]
    end = maxIndex
    minLen = maxIndex - pq[0][0]
    
    word = pq[0][1]
    while(queryPointers[word] < len(docIndex[word])):
        currentIndex = docIndex[word][queryPointers[word]]
        heapq.heappop(pq)
        heapq.heappush(pq, (currentIndex, word))
        
        currentIndex += len(word)
        if(currentIndex > maxIndex):
            maxIndex = currentIndex
        
        if(maxIndex - pq[0][0] < minLen):
            minLen = maxIndex - pq[0][0]
            start = pq[0][0]
            end = maxIndex
        word = pq[0][1]
        queryPointers[word] += 1

    return start,end

def highlight_doc(doc, query):
    """
    Args:
    doc - String that is a document to be highlighted
    query - String that contains the search query
    Returns:
    The the most relevant snippet with the query terms highlighted.
    """
    
    docIndex = build_doc_index(doc)
    queryDict = build_query_dict(query)
    
    # message queryDict to fit in doc index
    for word in list(queryDict.keys()):
        if(word not in docIndex):
            del queryDict[word]
        elif(queryDict[word] > len(docIndex[word])):
            queryDict[word] = len(docIndex[word])
    
    start,end = calculate_snippet(docIndex, queryDict)
    
    snippet = doc[start:end]
    newDoc = doc[:start] + "[[HIGHLIGHT]]" + snippet + "[[ENDHIGHLIGHT]]" + doc[end:]
    
    return newDoc
    
    
if __name__ == '__main__':
    doc = "I like dish.  Little star's deep dish pizza sure is fantastic. Dogs are funny. dogs are really funny"
    query = "deep dish pizza"
    newdoc = highlight_doc(doc, query)
    print(newdoc)
