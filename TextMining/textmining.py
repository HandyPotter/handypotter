import json
import konlpy
import sys
import knusl
import pandas as pd
from openpyxl import load_workbook
import openpyxl
from eunjeon import Mecab
from soylemma import Lemmatizer

class Textmining():

    """들어온 텍스트 데이터 전처리와 감정 사전 데이터 형성"""

    def voice2Text():
        fname = r'C:\Users\PC\PycharmProjects\HandyPotter\test.json'

        with open(fname, mode='r', buffering=-1, encoding="UTF-8") as fp:
            data = json.loads(fp.read())

        for item in data['alternatives']:
            text = item['transcript']
            print(text)

        tagger = Mecab()

        lemmatizer = Lemmatizer(dictionary_name='default')

        # 품사 구분하여 고유명사, 명사, 동사, 형용사 출력
        tagged_list = tagger.pos(text)

        sentence_token = [t[0] for t in tagged_list if
                          t[1] == 'NNP' or t[1] == 'NNG' or t[1] == 'NP' or t[1] == 'VV' or t[1] == 'VA']

        pnouns_tokens = [t[0] for t in tagged_list if t[1] == 'NNP']
        nouns_tokens = [t[0] for t in tagged_list if t[1] == 'NNG']
        pronouns_tokens = [t[0] for t in tagged_list if t[1] == 'NP']
        verb_tokens = [t[0] for t in tagged_list if t[1] == 'VV']
        adjective_tokens = [t[0] for t in tagged_list if t[1] == 'VA']

        verb = verb_tokens[0] + '다'
        adjective = verb_tokens[0] + '다'

        print(sentence_token)

        return sentence_token


    def emoDictionary(sentence):
        wb = openpyxl.load_workbook(r"C:\Users\PC\PycharmProjects\HandyPotter\EmotionDictionary.xlsx")
        sheet = wb['Sheet1']

        tagger = Mecab()
        emotions = []
        all_emo = []

        for row in sheet.iter_rows(min_row=2, max_col=3, max_row=428):
            번호 = row[0].row
            pre_emo = (row[1].value)
            pre_emo = tagger.pos(pre_emo)
            단어 = pre_emo[0][0]
            감정 = row[2].value
            emotion = (번호, 단어, 감정)
            all_emo.append(단어)
            emotions.append(emotion)

        sentence_emo = list(set(sentence).intersection(all_emo))
        print(sentence_emo)

        emo_num = all_emo.index(sentence_emo[0])

        result_emo = emotions[emo_num]

        return result_emo




if __name__ == "__main__":
    test = Textmining
    result = test.emoDictionary(sentence=test.voice2Text())

    print("지금 기분은 : ", result[2])

















#다른 감정 사전
#KnuInstance = knusl.KnuSL
#for t in sentense_token:
#    word = t
#    print(word)
#    KnuInstance.data_list(word)









