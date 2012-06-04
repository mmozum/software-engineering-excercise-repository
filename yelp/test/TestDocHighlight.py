'''
Created on May 28, 2012

'''

import unittest
from DocHighlight import build_query_dict, highlight_doc

class TestDocHighlight(unittest.TestCase):
    '''
    Unittest class for DocHighlight.py
    '''


    def  test_build_query_dict(self):
        query = "Pizza, pizza, pizzas ..... pIZZA, "
        
        dic = build_query_dict(query)
        self.assertEqual(len(dic), 2, "query indexing returned abnormal number of element")

        self.assertEqual(dic['pizza'], 3, "incorrect number of keyword 'pizza'")
        self.assertTrue("pizza" in dic, "keyword 'pizza' not found in query index")
        self.assertTrue("pizzas" in dic, "keyword 'pizzas' not found in query index")
        self.assertTrue("pizzass" not in dic, "keyword should not be in query index")
        
    def  test_highlight_doc1(self):
        doc = """
        A testcase is created by subclassing unittest.TestCase. The three individual 
        tests are defined with methods whose names start with the letters test. This 
        naming convention informs the test runner about which methods represent tests.
        """
        query = "unit test"
        
        print(highlight_doc(doc, query))

    def  test_highlight_doc2(self):
        doc = """
        It's easy to find a good Chinese restaurant in the bay, but it's not that easy 
        to find a nice decorated & clean Chinese restaurant that my dad likes to treat
         his friends to. And Taipan is one.  I've tried both of its 
        """
        query = "chinese restaurant"
        
        print(highlight_doc(doc, query))

    def  test_highlight_doc3(self):
        doc = """
        Wang's was a Chinese-Korean restaurant.
        """
        query = "chinese restaurant"
        
        print(highlight_doc(doc, query))
        
        