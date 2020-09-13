import gensim

import comparingDB
from textmining import *
from collections import OrderedDict

class Synonym():

    """들어온 형태소 데이터 별 동의어 처리"""

    def mor2Syn():

        model = gensim.models.Word2Vec.load(r'/home/ubuntu/handypotter/ko/ko.bin')

        TM = Textmining.voice2Text()
        size = len(TM)
        same = []

        tags = ['NNP', 'NNG', 'NP', 'VV', 'VA', 'MAG', 'XR']
        stoptags = ['JKS', 'SF', 'XSN', 'EC', 'EP', 'VX', 'NNB', 'EF', 'JX', 'EP+EF', 'XSV', 'XSA', 'XSN']

        print("추출된 형태소 : ", TM)
        slword = comparingDB.querySQL()

        for a in range(size):
            token = TM[a]
            for b in slword:
                if token == b[1]:
                    same.append((b))
                else:
                    try:
                        for i in model.wv.most_similar(token):
                            if i[1] == b[1]:
                                same.append((b))
                    except Exception:
                        print("명동형인데 동의어 없음")

        print("DB와 비교된 후", same)

        return same

    def getresult():

        synword = Synonym.mor2Syn()
        finalSL = []

        for i in synword:
            id = i[0]
            finalSL.append(id)

        print("\n\n수어비교 결과 : ", synword)
        print("\n 최종 수어 ID : ", finalSL)

        return finalSL


if __name__ == "__main__":
    test = Synonym.mor2Syn()
    result = Synonym.getresult()
