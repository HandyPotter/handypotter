import gensim

import comparingSL
from textmining import *

class Synonym():

    """들어온 형태소 데이터 별 동의어 처리"""

    def mor2Syn():

        model = gensim.models.Word2Vec.load(r'/home/ubuntu/handypotter/ko/ko.bin')

        TM = Textmining.voice2Text()
        size = len(TM)
        SL_word = []

        print("TM : ", TM)

        for a in range(size):
            #print("추출된 형태소 : ", TM[a])
            SL_word.append((a, TM[a]))
            for i, num in model.wv.most_similar(TM[a]):
                #print(i, num)
                SL_word.append((a, i))

        return SL_word


    def compareDB(synword, slword):

        same = []


        print(slword)
        for t in synword:
            for a in slword:
                if t[1] == a[1]:
                    same.append(a)

        return same


if __name__ == "__main__":

    synword = Synonym.mor2Syn()
    slword = comparingSL.result_SL
    finalSL = []

    result = Synonym.compareDB(synword, slword)

    print(type(result))

    for i in result:
        id = i[0]
        finalSL.append(id)
    #print("동의어 : ", result)

    print("\n\n수어비교 결과 : ", result)
    print("\n 최종 수어 ID : ", finalSL)




