__author__ = 'sharelison'
import requests
import os
import re
from bs4 import BeautifulSoup
import config as cfg
from requests.exceptions import ConnectionError


def writeToFile(textfile, itemUrl):
    textfile = open(textfile, "a")
    textfile.write(cfg.seperatorFields + "Link" + cfg.seperatorData + itemUrl + cfg.seperatorFields + "Website" + cfg.seperatorData + cfg.websiteUrl + cfg.seperatorProduct)
    textfile.close()


def writeProducToFile(textfile, label, productDescription):
    textfile = open(textfile, "a")
    textfile.write(label + cfg.seperatorData + productDescription.encode(cfg.encoding))
    textfile.close()


def crawl(url, filename):
    source_code = requests.get(url)
    text = source_code.text
    soup = BeautifulSoup(text)
    textFileName = os.path.join(filename)
    textFile = open(textFileName, 'w')
    textFile.close()
    a = soup.find_all('a', {'href': pcPart.link})
    for mya in a:
        liParent = mya.parent
        for li in liParent.find_all('li'):
            for href in li.find_all('a'):
                categorie = str(href.text)
                print categorie
                if not 'Server' in categorie and not 'Accessoires' in categorie:
                    openLink(cfg.websiteUrl + href.get('href'), textFileName)
                else:
                    break


def getListType(link, filename):
    source_code = requests.get(link)
    text = source_code.text
    soup = BeautifulSoup(text)
    exampleLink = 'http://azerty.nl/producten/zoek?GROEP_ID=6121&p_tab=8&v_uitgebreid=off&PAGINA=1&SORTERING=levertijd_asc&presentatie=miniatuur-lijst'
    page = 1
    print link[20:]
    a = soup.find_all('a', {"href": link[20:]})
    for mya in a:
        liParent = mya.parent
        for li in liParent.find_all('li'):
            for href in li.find_all('a'):
                fullHref = href.get('href')
                group_id = fullHref[3:7]
                group_id = int(re.match(r'\d+', group_id).group())
                hrefWithPages = exampleLink[:41] + str(group_id) + exampleLink[45:78] + str(page) + exampleLink[79:]
                if checkdivmenurechts(hrefWithPages):
                        openLink(cfg.websiteUrl + fullHref, filename)
                else:
                    while page < pcPart.pages:
                        openLink(hrefWithPages, filename)
                        page += 1


def checkdivmenurechts(link):
    source_code = requests.get(link)
    text = source_code.text
    soup = BeautifulSoup(text)
    div = soup.find_all('div', {'id': 'menurechts'})
    if div:
        return True
    else:
        return False


def openLink(link, filename):
    try:
        source_code = requests.get(link)
        text = source_code.text
        soup = BeautifulSoup(text)

        if checkdivmenurechts(link):
            getListType(link, filename)
        else:
            for itemLink in soup.find_all('a', {'class': 'titel'}):
                get_single_item_data(cfg.websiteUrl + str(itemLink.get('href')), filename)
    except ConnectionError, e:
        print e


def get_single_item_data(item_url, textFile):
    source_code = requests.get(item_url)
    text = source_code.text
    soup = BeautifulSoup(text)
        
    #Artikel specificatie's pakken
    def getSpecificInfo(info):
        returnInfo = []
        for information in info:
            alreadyAddedInfo = []
            for li in soup.find_all('li'):
                if li.text == information[0]:
                    ul = li.parent
                    secondLi = False
                    for li in ul.find_all('li'):
                        if secondLi and information[0] not in alreadyAddedInfo:
                            if information[2] == ":":
                                if len(li.text) == 14 and pcPart.name == 'processorAzerty.txt':
                                    returnInfo.append(li.text[3:7])
                                elif len(li.text) == 18 and pcPart.name == 'moederboardAzerty.txt':
                                    returnInfo.append(li.text[7:11])
                                else:
                                    returnInfo.append(li.text[information[1]:])
                                alreadyAddedInfo.append(information[0])
                            else:
                                returnInfo.append(li.text[information[1]:information[2]])
                            alreadyAddedInfo.append(information[0])
                        else:
                            secondLi = True
                elif pcPart.name == 'behuizingAzerty.txt':
                    if li.text == 'Ondersteunde moederborden':
                        ul = li.parent
                        secondLi = False
                        for li3 in ul.find_all('li'):
                            if secondLi and 'Ondersteunde moederborden' not in alreadyAddedInfo:
                                if information[2] == ":":
                                    if len(li3.text) > 12 and pcPart.name == 'processorAzerty.txt':
                                        returnInfo.append(li3.text[3:7])
                                    else:
                                        returnInfo.append(li3.text[information[1]:])
                                else:
                                    returnInfo.append(li3.text[information[1]:information[2]])
                                alreadyAddedInfo.append('Ondersteunde moederborden')

        return returnInfo
        
    #Artikel price pakken
    for priceDiv in soup.find_all('div', {"class": "artikel-stukprijs col-xs-12"}):
        for price in priceDiv.find_all('span', {"class": "groot"}):
            price = price.text[1:]
            writeProducToFile(textFile, "Price", price)
            
    #Artikel naam pakken
    for name in soup.find_all('h1', {"class": "artikel"}):
        name = name.text
        print name
        writeProducToFile(textFile, "Name", name)

    #Artikel omschrijving pakken
    didWriteToTextFile = False
    for description in soup.find_all('div', {"class": "omschrijving multicolumn1"}):
        if not description:
            textFile.write("-" + cfg.seperatorFields)
        else:
            description = description.string
            try:
                if not description or description == " " or description == "":
                    writeProducToFile(textFile, "Description", "-")
                else:
                    writeProducToFile(textFile,"Description", description)
            except UnicodeEncodeError or TypeError, e:
                pass

    #Artikel img pakken
    for photo in soup.find_all('img', {'class': 'artikel-afbeelding-main'}):
        photo = photo.get('src')
        writeProducToFile(textFile, "Photo", photo)

    #Artikel specificaties naar textbestand schrijven    
    if pcPart.specs == 'none':
        writeToFile(textFile, item_url)
    else:
        speceficInfo = getSpecificInfo(pcPart.specs)
        teller = 0
        while teller < len(speceficInfo):
            allInfo = speceficInfo[teller]
            nameSpec = pcPart.nameSpecs[teller]
            writeProducToFile(textFile, nameSpec, allInfo)
            teller += 1
            
        writeToFile(textFile, item_url)
            
for pcPart in cfg.pcParts:
    try:
        crawl(cfg.websiteUrl + pcPart.link, pcPart.name)
    except ConnectionError:
        print "connectionerror"
