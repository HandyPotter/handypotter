#!/usr/bin/python
import pymysql
from konlpy.tag import Mecab


def querySQL():#####     MySQL 연결     #####
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

    word_tag = []
    result_SL = []

    for word in SL_list:
        id = SL_list.index(word) + 1
        w = mecab.pos(word)
        stoptags = ['JKS', 'SF', 'XSN', 'EC', 'EP', 'VX', 'NNB', 'EF', 'JX', 'EP+EF', 'XSV', 'XSA', 'XSN']
        for t in w:
            if t[1] not in stoptags:
                result_SL.append([id, t[0]])

    print(result_SL)
    # Connection 닫기
    conn.close()

    return result_SL


def returnSL_Element(id_list):
    # MySQL Connection 연결
    conn = pymysql.connect(host='localhost', port=3306, user='potter', password='muggles',
                       db='handypotter', charset='utf8')

    # Connection 으로부터 Cursor 생성
    curs = conn.cursor()

    # SQL문 실행
    sql = "SELECT SL_RIGHT, SL_LEFT FROM SignLanguage ORDER BY SL_ID"
    curs.execute(sql)

    # 데이타 Fetch
    rows = curs.fetchall()

    #####  Sign Language 형태소 태깅   #####
    SL_list = []

    for row in rows:
        SL_list.append((row))

    returnSL = []
    countID = len(id_list)

    print("보내야하는 수어 요소 ID!!!")
    for i in range(countID):
        id = id_list[i] - 1
        print(SL_list[id])
        returnSL.append(SL_list[id])

    print(returnSL)

    # Connection 닫기
    conn.close()

    return returnSL


def returnEmo_Element(emo_list):
    # MySQL Connection 연결
    conn = pymysql.connect(host='localhost', port=3306, user='potter', password='muggles',
                       db='handypotter', charset='utf8')

    # Connection 으로부터 Cursor 생성
    curs = conn.cursor()

    # SQL문 실행
    sql = "SELECT EmotionID, Emotion FROM Emotion"
    curs.execute(sql)

    # 데이타 Fetch
    rows = curs.fetchall()

    #####  Sign Language 형태소 태깅   #####
    emo_all = []

    for row in rows:
        emo_all.append((row))

    returnEmoID = []
    eSize = len(emo_all)
    lSize = len(emo_list)

    print("보내야하는 감정 요소!!!")
    for i in range(eSize):
        for a in range(lSize):
            id = emo_all[i]
            if emo_list[a] == id[1]:
                returnEmoID.append(id[0])

    print(returnEmoID)
    # Connection 닫기
    conn.close()

    return returnEmoID
