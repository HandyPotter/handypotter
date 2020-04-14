import konlpy
import json
import sys
from konlpy.tag import Okt
from konlpy.tag import Twitter

fname = '/Users/seoralee/Projects/HandyPotter/TextMining/test.json'

data=[]

with open(fname, mode='r', buffering=-1, encoding="UTF-8") as fp:
    data = json.loads(fp.read())

print(data)

for item in data['alternatives']:
    text = item['transcript']
    print(text)

okt=Okt() #객체 생성

print(okt.pos(text, join=True)) #품사 태깅
data_morphs = okt.morphs(text) #명사

print(data_morphs)





