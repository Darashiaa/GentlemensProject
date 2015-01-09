__author__ = 'sharelison'
import requests
import os
from requests.exceptions import ConnectionError
from bs4 import BeautifulSoup
import config as cfg


def writeToFile(file, itemUrl):
    file.write(cfg.seperator + "Link" + cfg.seperatorData + itemUrl + cfg.seperator + "Website" + cfg.seperatorData + cfg.websiteUrl + cfg.seperatorProduct)


def writeProducToFile(file, label, productDescription):
    file.write(label + cfg.seperatorData + productDescription.encode(cfg.encoding) + cfg.seperator)


def crawl(givenUrl, filename):
    completeName = os.path.join(filename)
    file = open(completeName, 'w')
    url = givenUrl
    source_code = requests.get(url)
    text = source_code.text
    soup = BeautifulSoup(text, from_encoding=cfg.encoding)

    for link in soup.findAll('a', {'class': 'productLink'}):
        href = cfg.websiteUrl + link.get('href')
        try:
            get_single_item_data(href, file)
        except ConnectionError:
            ""

def get_single_item_data(item_url, file1):
    source_code = requests.get(item_url)
    text = source_code.text
    soup = BeautifulSoup(text, from_encoding=cfg.encoding)

    #Methode om product specificaties te pakken
    def getSpecificInfo(info):
        returninfo = []

        for information in info:
            table = soup.find_all('table', {'class', 'techDataTable'})
            for table2 in table:
                for td in table2.find_all('td', {'class', 'techDataCol1'}):
                    if td.text == information[0]:
                        tr = td.parent

                        if 'Geheugen' in information[0] or 'Vermogen' in information[0]:
                            table3 = tr.find_all('table')

                            for table4 in table3:
                                for td2 in table4.find_all('td', {'class': 'techDataSubCol techDataSubColDescription'}):
                                    if td2.text == information[1]:
                                        tr2 = td2.parent
                                        counter = 0
                                        for td3 in tr2.find_all('td', {'class': 'techDataSubCol techDataSubColValue'}):
                                            if counter > 0:
                                                break
                                            else:
                                                if information[3] == ":":
                                                    returninfo.append(td3.text[information[2]:])
                                                else:
                                                    returninfo.append(td3.text[information[2]:information[3]])
                                            counter += 1
                        else:
                            for nextTd in tr.find_all('td', {'class', 'techDataCol2'}):
                                if information[2] == ":":
                                    returninfo.append(nextTd.text[information[1]:])
                                else:
                                    returninfo.append(nextTd.text[information[1]:information[2]])
        return returninfo

    #Product prijs pakken
    for link in soup.findAll('span'):
        if link.parent.name == 'p':
            try:
                writeProducToFile(file1, 'Price', link['content'])
            except Exception, e:
                print str(e)

    #Product naam pakken
    title = soup.find_all('div', {"class": "productNameContainer"})
    for title2 in title:
        realTitle = title2.find_all('h1')
        for realTitle2 in realTitle:
            productName = realTitle2.text
            print productName.encode(cfg.encoding)

            try:
                writeProducToFile(file1, "Name", productName)
            except Exception, e:
                print(str(e))

    #Product omschrijving pakken
    desc = soup.find_all('div', {'class': 'description'})
    for desc1 in desc:
        description = desc1.find_all('p')
        for desc2 in description:
            try:
                if 'Art-Nr' not in desc2.text:
                    productDesc = desc2.text
                    writeProducToFile(file1, "Description", productDesc)
                break
            except Exception, e:
                print str(e)

    #Product photo pakken.
    photoParent = soup.find_all('a', {"class": "picture loupe lightboxMediaJS"})
    for photoElement in photoParent:
        for photoSrc in photoElement.find_all("img"):
            photo = photoSrc.get('src')
            photo = cfg.websiteUrl + photo.encode(cfg.encoding)

            try:
                writeProducToFile(file1, 'Photo', photo)
            except Exception, e:
                print str(e)

    if pcPart.specs == 'none':
        writeToFile(file1, item_url)
    else:
        speceficInfo = getSpecificInfo(pcPart.specs)
        teller = 0

        while teller < len(speceficInfo):
            allInfo = speceficInfo[teller]
            nameSpec = pcPart.nameSpecs[teller]
            file1.write(cfg.seperator + nameSpec + cfg.seperatorData + allInfo.encode(cfg.encoding))
            teller = teller + 1

        writeToFile(file1, item_url)

for pcPart in cfg.pcParts:
    try:
        crawl(pcPart.link, pcPart.name)
    except ConnectionError, e:
        pass

