__author__ = 'sharelison'
import requests
import os
from requests.exceptions import ConnectionError
from bs4 import BeautifulSoup
from py2neo import neo4j, rel, node

class PcPart:
    def __init__(self, link, name, specs, pages, nameSpecs):
        self.link = link
        self.name = name
        self.specs = specs
        self.pages = pages
        self.nameSpecs = nameSpecs

