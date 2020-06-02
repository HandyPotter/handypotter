#textmining.py

import os
import json
import konlpy.tag
import sys
import openpyxl
from konlpy.tag import Mecab
from soylemma import Lemmatizer


class Textmining():

    """들어온 텍스트 데이터 전처리와 감정 사전 데이터 형성"""

    def voice2Text():


        fname = r'/home/ubuntu/handypotter/v2t.txt'

        with open(fname, mode='r', buffering=-1, encoding="UTF-8") as fp:
            text = fp.read()
        fp.close

        mecab = Mecab()

        lemmatizer = Lemmatizer(dictionary_name='default')

        # 품사 구분하여 고유명사, 명사, 동사, 형용사 출력
        tagged_list = mecab.pos(text)

        tags = ['NNP', 'NNG', 'NP', 'VV', 'VA', 'MAG']
        stoptags = ['JKS', 'SF', 'XSN', 'EC', 'EP', 'VX', 'NNB', 'EF', 'JX', 'EP+EF', 'XSV', 'XSA', 'XSN']

        sentence_token = [t[0] for t in tagged_list if t[1] in tags]

        return sentence_token


    def emoDictionary(sentence):
        wb = openpyxl.load_workbook(r"/home/ubuntu/handypotter/EmotionDictionary.xlsx")
        sheet = wb['Sheet1']
        result_emo = []

        mecab = Mecab()
        emotions = []
        all_emo = []

        for row in sheet.iter_rows(min_row=2, max_col=3, max_row=428):
            번호 = row[0].row
            pre_emo = (row[1].value)
            pre_emo = mecab.pos(pre_emo)
            단어 = pre_emo[0][0]
            감정 = row[2].value
            emotion = (번호, 단어, 감정)
            all_emo.append(단어)
            emotions.append(emotion)

        sentence_emo = list(set(sentence).intersection(all_emo))

        print(sentence_emo)

        count_emo = len(sentence_emo)

        if not sentence_emo:
            result_emo = (0, '감정없음', '중성')
        else:
            for t in range(count_emo):
                emo_num = all_emo.index(sentence_emo[t])
                result_emo.append(emotions[emo_num])

        print(result_emo)

        returnemo = []
        extraction_Emo = len(result_emo)
        for a in range(extraction_Emo):
            emo = result_emo[a]
            returnemo.append(emo[2])


        print(returnemo)
        return returnemo


if __name__ == "__main__":
    test = Textmining
    result = test.emoDictionary(sentence = test.voice2Text())

    print("지금 기분은 : ", result)
