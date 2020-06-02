import gensim

import comparingSL
from textmining import *
from collections import OrderedDict


class Synonym():

    """들어온 형태소 데이터 별 동의어 처리"""

    def mor2Syn():

        model = gensim.models.Word2Vec.load(r'/home/ubuntu/handypotter/ko/ko.bin')

        TM = Textmining.voice2Text()
        size = len(TM)
        same = []

        tags = ['NNP','NNG','NP','VV','VA','MAG']
        stoptags = ['JKS', 'SF', 'XSN', 'EC', 'EP', 'VX', 'NNB', 'EF', 'JX', 'EP+EF', 'XSV', 'XSA', 'XSN']

        print("추출된 형태소 : ", TM)
        slword = comparingSL.result_SL

        for a in range(size):

            token = TM[a]
            for b in slword:
                if token == b[1]:
                    same.append((b))
                else:
                    for i in model.wv.most_similar(token):
                        if not model.wv.most_similar(token):
                            print("명동형인데 동의어 없음")
                        else:
                            if i[1] == b[1]:
                                same.append((b))

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
