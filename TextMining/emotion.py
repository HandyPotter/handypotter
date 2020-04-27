from konlpy.tag import Mecab
from eunjeon import Mecab
from textblob.classifiers import NaiveBayesClassifier
from textblob import TextBlob
import nltk

tagger = Mecab()

train = [
    ('나는 이 샌드위치를 정말 좋아해.', '긍정'),
    ('정말 멋진 곳이에요!', '긍정'),
    ('나는 이 맥주들이 아주 좋다고 생각해요.', '긍정'),
    ('이것은 나의 최고의 작품입니다.', '긍정'),
    ("정말 멋진 광경이다", "긍정"),
    ('난 이 식당 싫어', '부정'),
    ('난 이게 지겨워.', '부정'),
    ("이 문제는 처리할 수 없습니다.", "부정"),
    ('그는 나의 불구대천의 원수이다.', '부정'),
    ('내 상사는 끔찍해.', '부정'),
    ('나는 내 꿈을 믿는다', '긍정'),
    ('나는 매일 최선을 다하고 있다', '긍정'),
    ('나는 있는 그대로의 나를 사랑한다', '긍정'),
    ('나는 내 삶을 100% 책임진다', '긍정'),
    ('가장 좋은 일은 아직 생기지 않았다', '긍정'),
    ('나는 매일 나의 삶에 감사한다', '긍정'),
    ('새로나온 휴대폰은 배터리 교체가 되지 않아 불편하다', '부정'),
    ('이번에 나온 영화 너무 재밌다. 주말에 또 보고 싶다.', '긍정'),
    ('나의 아버지는 이해가 안된다', '부정'),
    ('나는 어머니와 있을 때 퉁명해진다', '부정'),
    ('나는 어머니와 있을 때 불편할 때가 있다.', '부정'),
    ('나름 괜찮네요', '긍정'),
    ('정말 실망스러움','부정'),
    ('이딴걸 드라마라고 만들었음?', '부정'),
    ('돈만 있으면 내가 이것보단 잘 만들겠다', '부정'),
    ('여름이면 한결같이 생각난다', '긍정'),
    ('난 재밌던데 평점이 왜 이렇게 낮지', '긍정'),
    ('안보면 후회할듯', '긍정'),
    ('원작의 긴장감을 제대로 살려내지못했다', '부정')

]

test = [
    ('맥주가 좋았습니다.', '긍정'),
    ('난 내 일을 즐기지 않는다', '부정'),
    ('오늘은 기분이 안 좋아요.', '부정'),
    ('놀라워요!', '긍정'),
    ('네드는 나의 친구입니다.', '긍정'),
    ('제가 이렇게 하고 있다니 믿을 수가 없어요.', '부정')
]

#with open('trainingset.txt', mode='r', buffering=-1, encoding="UTF-8") as fp:
#    train = fp.read()


cl = NaiveBayesClassifier(train)
cl.show_informative_features()
cl.accuracy(test)

print(cl.accuracy(test))

train_data = [(['/'.join(token) for token in tagger.pos(sentence)], result) for [sentence, result] in train]

print(train_data)

print(cl.accuracy(train_data))
