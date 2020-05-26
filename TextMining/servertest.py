from socket import *
import json

print("들어올 데이터를 기다리는 중입니다.")

serverSock = socket(AF_INET, SOCK_STREAM)
serverSock.bind(('', 9999))
serverSock.listen(1)

connectionSock, addr = serverSock.accept()

print(str(addr),'에서 접속이 확인되었습니다.')

while True:
    data = connectionSock.recv(4)
    # 최초 4바이트는 전송할 데이터의 크기이다. 그 크기는 little big 엔디언으로 byte에서 int형식으로 변환한다.
    length = int.from_bytes(data, "little")
    # 다시 데이터를 수신한다.
    data = connectionSock.recv(length)
    # 수신된 데이터를 str형식으로 decode한다.
    msg = data.decode()
    # 수신된 메시지를 콘솔에 출력한다.
    print('받은 데이터 : ', msg)

    fname = r'/home/ubuntu/handypotter/v2t.txt'
    f = open(fname, 'w')
    f.write(msg)
    f.close()

    from textmining import *
    from comparingSL import *
    from synonym import *

    id_list = Synonym.getresult()

    returnSL = " ".join(map(str, id_list))
    returnSL = returnSL.encode()
    length = len(returnSL)
    connectionSock.sendall(length.to_bytes(4, byteorder='little'))
    connectionSock.sendall(returnSL)
    print('메시지를 보냈습니다.')
