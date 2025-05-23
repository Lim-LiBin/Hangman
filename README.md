# 🎮 행맨 게임
> Java Swing을 활용하여 제작한 GUI 기반의 행맨 게임

## 📌 주요 기능
- 난이도 선택 지원: Easy (10회), Medium (8회), Hard (6회)
- 알파벳 버튼 클릭을 통한 입력 방식
- 무작위 단어 선택 (중복 없이 200개 이상의 단어)
- 힌트 기능: 특정 패턴(예: -on) 포함 시 힌트 제공
- 게임 종료 후 승/패 결과 및 누적 승률 표시
- 플레이어의 난이도 및 승률 기록 유지

## 🧩 규칙
- 알파벳 버튼을 눌러 정답 단어를 유추
- 틀릴 때마다 행맨 그림이 점차 사라짐
- 시도 횟수 내에 단어를 완성하면 승리, 실패 시 패배
- 난이도에 따라 허용 추측 횟수가 다름

## 🎨 스크린샷
1. **초기 화면:** 난이도 선택
<img width="624" alt="초기 화면" src="https://github.com/user-attachments/assets/04dcafd2-f474-4130-85aa-240984541f6a" />
<img width="625" alt="초기화면_미디움" src="https://github.com/user-attachments/assets/87925b66-8cb7-48b6-9a3f-293c25673ab3" />
<img width="624" alt="초기화면_하드" src="https://github.com/user-attachments/assets/8c446d25-09ee-44d1-8316-8251184571af" />

2. **게임 시작:** 난이도별 추측 가능 횟수 설정
<img width="623" alt="시작_이지" src="https://github.com/user-attachments/assets/d62c643d-5aba-4869-bdc4-bb0fdd70ccb8" />
<img width="625" alt="시작_미디움" src="https://github.com/user-attachments/assets/a6d3c9f0-35a7-4f34-b495-0f1b6c4c4c38" />
<img width="623" alt="시작_하드" src="https://github.com/user-attachments/assets/8708b826-e3b6-4139-bd39-ae5fc8605e35" />

3. **게임 진행:** 틀릴 때마다 행맨 캐릭터가 점점 사라짐
<img width="624" alt="중간 과정" src="https://github.com/user-attachments/assets/d309a10c-caf3-4546-8ab0-cb26871d2f25" />

4. **게임 종료:** 승/패 횟수 증가 및 누적 승률 계산
<img width="626" alt="게임 끝" src="https://github.com/user-attachments/assets/c5d374b1-7be9-4704-932f-ae3a1deb43ed" />

## 📁 파일 구조
- 'Hangman.java': 메인 게임 로직 및 GUI 구현 파일

## 🧠 배운 점
- Java Swing 기반 GUI 개발 경험
- Graphics2D를 활용한 동적 행맨 그림 구현
- 상태 관리 로직
- 이벤트 기반 UI 처리 및 난이도 연동 로직 구성

## ✅ 향후 개선점
- 단어 리스트를 외부 파일로 관리하여 유연성 확보
- 배경음 및 효과음 추가
- 시각적 완성도 향상
