from PcPart import PcPart

#Object maken van de pc onderdelen met hun link, textbestand, naam en specificaties  en in een list zetten.
pcParts = []

pcParts.append(PcPart('http://www.alternate.nl/html/configurator/builder/structure/page.html?cmd=search&componentType=required&className=powerSupply&activeClassName=powerSupply&kind=pcBuilder&size=500#listingResult',
                      'voedingAlternate.txt', [['Vermogen', 'Totaal', 0, ":"], ['Bouwvorm', 0, ":"]], ['Energy', 'Formfactor']))
pcParts.append(PcPart('http://www.alternate.nl/html/configurator/builder/structure/page.html?cmd=search&componentType=required&className=cpu&activeClassName=cpu&kind=pcBuilder&size=500#listingResult',
                      "processorsAlternate.txt", [['Socket', 0, ":"]], ['Socket']))
pcParts.append(PcPart('http://www.alternate.nl/html/configurator/builder/structure/page.html?cmd=search&componentType=required&className=ram&activeClassName=ram&kind=pcBuilder&size=500#listingResult',
                     "geheugenAlternate.txt", [['Type', 0, ":"], ['Module', 0, 1]], ['MemoryType', 'Modules']))
pcParts.append(PcPart('http://www.alternate.nl/html/configurator/builder/structure/page.html?cmd=search&componentType=required&className=harddiskSATA&activeClassName=harddisk&kind=pcBuilder&size=500#listingResult',
                      "harddiskAlternate.txt", "none", ""))
pcParts.append(PcPart('http://www.alternate.nl/html/configurator/builder/structure/page.html?cmd=search&componentType=required&className=graphicscardPCIe&activeClassName=graphicscard&kind=pcBuilder&size=500#listingResult',
                      "grafischKaartAlternate.txt", [['Geheugen', 'Type', 0, ":"]], ['MemoryType']))
pcParts.append(PcPart('http://www.alternate.nl/html/configurator/builder/structure/page.html?cmd=search&componentType=required&className=pccase&activeClassName=pccase&kind=pcBuilder&size=500#listingResult',
                      "behuizingAlternate.txt", [['PSU', 8, ":"]], ['Formfactor']))
pcParts.append(PcPart("http://www.alternate.nl/html/configurator/builder/structure/page.html?cmd=search&componentType=required&className=mainboard&activeClassName=mainboard&kind=pcBuilder&size=500#listingResult",
                      "moederboardAlternate.txt", [['Geheugen', 'Geheugen socket', 0, 1],
                                                   ['Geheugen', 'Type geheugen', 0, ":"], ['Formfactor', 0, ":"], ['Socket', 0, ":"]], ['Modules', 'MemoryType', 'Formfactor', 'Socket']))

encoding = 'utf-8'
websiteUrl = "http://www.alternate.nl"
seperatorProduct = "~~~"
seperator = '>>>'
seperatorData = '<<<'