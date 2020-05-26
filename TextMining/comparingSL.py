#!/usr/bin/python
import pymysql
from konlpy.tag import Mecab

#####     MySQL 연결     #####
# MySQL Connection 연결
conn = pymysql.connect(host='localhost', port=3306, user='potter', password='muggles',
                       db='handypotter', charset='utf8')

# Connection 으로부터 Cursor 생성
curs = conn.cursor()

# SQL문 실행
sql = "SELECT SL_ID, SL_WORD FROM SignLanguage ORDER BY SL_ID"
curs.execute(sql)

# 데이타 Fetch
rows = curs.fetchall()



#####  Sign Language 형태소 태깅   #####
SL_list = []

for row in rows:
    SL_list.append(row[1])


mecab = Mecab()

word_tag=[]
result_SL = []

for word in SL_list:
    id = SL_list.index(word) + 1
    w = mecab.pos(word)
    for t in w:
        if t[1] == 'NNP' or t[1] == 'NNG' or t[1] == 'NP' or t[1] == 'VV' or t[1] == 'VA':
            result_SL.append([id, t[0]])

print(result_SL)

# Connection 닫기
conn.close()
