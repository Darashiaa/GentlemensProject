__author__ = 'sharelison'
from PcPart import PcPart

pcParts = []
pcParts.append(PcPart('/8-910/processoren.html', 'processorAzerty.txt',
                      [["Compatibele processorsocket", 7, ":"]], 10, ['Socket']))
pcParts.append(PcPart('/8-674/voedingen.html', 'voedingAzerty.txt',
                      [["Stroomcapaciteit", 0, ":"], ["Naleving van specificatie", 0, 3]],31, ['Energy', 'Formfactor']))
pcParts.append(PcPart('/8-842/moederborden.html', 'moederboardAzerty.txt',
                      [["Ondersteund RAM", 0, 1], ["Ondersteund RAM", 17, 22],
                    ["Producttype", 13, ":"], ["Processor socket", 11, ":"]], 10, ['Modules', 'MemoryType', 'Formfactor', 'Socket']))
pcParts.append(PcPart('/8-62/geheugen-pc-server.html', 'geheugenAzerty.txt',
                      [['Technologie', 0, ":"], ['Uitwisselbare slots', 0, 1]], 27, ['MemoryType','Modules']))
pcParts.append(PcPart('/8-853/harddisk-intern.html', 'harddiskAzerty.txt',
                      'none', 16,""))
pcParts.append(PcPart('/8-969/videokaarten.html', 'grafischKaartAzerty.txt',
                      [['Technologie', 0, ":", 'nothingHere']], 12, ['MemoryType']))
pcParts.append(PcPart('/8-1042/behuizingen.html', 'behuizingAzerty.txt',
                      [['Ondersteunde moederbords', 0, ":"]], 26, ['Formfactor']))

#seperator variabeles voor text files
seperatorFields = ">>>"
seperatorProduct = "~~~"
seperatorData = "<<<"

websiteUrl = "http://www.azerty.nl"
encoding = "utf-8"