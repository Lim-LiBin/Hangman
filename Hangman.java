import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hangman extends JFrame implements ActionListener{
	String[] words = {"abon", "arron", "axion", "answon", "abilion", "acronyon", "autocron", "alleviaton", "antitheson", "applicabon", 
			"braion", "balanon", "backgrouon", "busineon", "breakon", "bridgon", "botheon", "behavon", "basicalon", "borron",
			"cable", "candle", "champion", "charityon", "childhoon", "classroon", "coaston", "combination", "comforton", "comparison",
			"definition", "deadon", "decision", "describon", "differenon", "diseason", "directon", "distribution", "duton", "downtownon",
			"economon", "efficieon", "emphasion", "enhancon", "equivalenton", "estimaton", "excellenton", "existon", "enoughon", "expresson",
			"factoron", "familon", "foundation", "frequentlon", "friendshipon", "framon", "function", "funeralon", "futureon", "freedomon",
			"garagon", "governmenton", "greenon", "guardon", "groceron", "guaranteenon", "guidancon", "guidon", "gradon", "generallon",
			"habiton", "happenon", "highlighton", "hesitaton", "honon", "hurron", "humanon", "husbandon", "healthon", "houson",
			"identifon", "idealon", "immediaton", "implon", "implementon", "importanton", "incidenton", "incomon", "indicaton", "influencon",
			"jacketon", "jointon", "judgon", "juicon", "jumpon", "justifon", "juston", "junioron", "judgementon", "jokon",
			"keep", "kick", "kitchen", "knowledge", "knee", "kettles", "kidneys", "kingdom", "keyboard", "knighthoodon",
			"ladder", "language", "landscape", "leadership", "leader", "library", "literature", "location", "lunch", "onion",
			"machine", "magazine", "maintenance", "maintain", "manage", "manufacturer", "mark", "market", "marry", "maximon",
			"name", "narrow", "nasty", "native", "nature", "negotiate", "nervous", "novel", "newspaper", "numerous",
			"object", "obtain", "operation", "opportunity", "ordinary", "original", "opinion", "overcome", "owner", "opposine",
			"package", "paint", "point", "parent", "passenger", "passion", "perfect", "percentagn", "performance", "persuaden",
			"quality", "quantity", "question", "quote", "reason", "recipe", "reduce", "register", "relationship", "remote",
			"scheme", "screw", "security", "separate", "station", "temperature", "throat", "transition", "translate", "typicaol",
			"upset", "understand", "university", "union", "uncle", "vacation", "variation", "visual", "volumen", "vegetable",
			"world", "write", "weekend", "warning", "weakness", "yellow", "yesterday", "youth", "zone", "zymosis"};
	int[] checked = new int [201]; // 이미 선택된 단어 체크

	//게임 상태 관련 변수
	int word_length; // 단어의 길이 //4자~12자
	int guessNum; // 추측 횟수
	int level; // 게임 난이도 (0: 하, 1: 중, 2: 상)
	char[] word1 = new char[12]; // 정답 문자 배열
		String[] word2 = new String[12]; // 화면에 표시될 문자 배열
	String[] slevel= {"Easy","Medium","Hard"}; // 난이도
	String check_word; //단어 체크
	double wins; //승리 횟수
	double looses; //패배 횟수
	double winningPercentage; //승률
	
	//게임 UI 구성요소
	JButton[] buttons = new JButton[26];
	
	JButton begin = new JButton("BEGIN");
	JButton easy = new JButton("EASY");
	JButton medium = new JButton("MEDIUM");
	JButton hard = new JButton("HARD");
	JButton hint_btn = new JButton("Hint");
	JLabel text = new JLabel("Skill level: ", JLabel.LEFT); // 레벨 레이블로 띄우기, 왼쪽 정렬
	
	JPanel displayTOP = new JPanel(); //상단(시작, 힌트 버튼)
	JPanel display1 = new JPanel();  	//알파벳 버튼들
	JPanel display2 = new JPanel(); //난이도 선택 버튼들
	
	Font normalFont = new Font("Arial", Font.BOLD, 16);
	Font warningFont = new Font("Arial", Font.BOLD, 20);
	boolean hint_bool = false;
	
	public Hangman() {
		setTitle("행맨 게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(850,700);
		setVisible(true);
	}
	
	public void init() {
		// 알파벳 버튼 초기화
		for (char cha = 'A'; cha <= 'Z'; cha++) {
		    int index = cha - 'A';
		    buttons[index] = new JButton(String.valueOf(cha));
		    buttons[index].addActionListener(this);
		}

		//버튼 리스너 등록
		begin.addActionListener(this);
		easy.addActionListener(this);
		medium.addActionListener(this);
		hard.addActionListener(this);
		hint_btn.addActionListener(this);
		
		//레이아웃 설정
	    Container root = getContentPane();
		root.setLayout(new GridLayout(3,0));
		
		// 상단: 시작 및 힌트 버튼
		displayTOP.add(begin); //begin 버튼 추가
		displayTOP.add(hint_btn);
		displayTOP.setLayout(new FlowLayout()); //FlowLayout 설정
		displayTOP.setBackground(new Color(0x0099ccff));
		root.add(displayTOP); // TOP을 컨테이너에 등록(0행에)
		
		// 가운데: 알파벳 버튼들
		display1.setLayout(new GridLayout(3, 9, 10, 10)); //알파벳
		display1.setBackground(new Color(0x009AD756));
		for(int i = 0;i<buttons.length;i++) { //버튼 추가
			buttons[i].setBackground(new Color(0x00fffff0));
			display1.add(buttons[i]);
		}		
		root.add(display1);
		
		// 하단: 난이도 설정
		display2.setLayout(new FlowLayout(1, 10, 50));
		display2.setBackground(new Color(0x009AD756));
		display2.add(text); //"Skill level"
		display2.add(easy); //easy 버튼 등록
		display2.add(medium); //midium 버튼 등록
		display2.add(hard); //hard 버튼 등록
		root.add(display2);
		
		setContentPane(root);
		
		// 버튼 상태 초기화
		for (JButton button : buttons) {
			button.setEnabled(false);
		}
		
		easy.setEnabled(false);
		medium.setEnabled(true);
		hard.setEnabled(true);
		hint_btn.setEnabled(true);

		// 단어 상태 초기화
		for(int i=0;i<checked.length;i++) {
			checked[i]=0; // 아직 선택되지 않은 단어 (0)으로 초기화
		}
		
		for(int i=0;i<12;i++) {
			word1[i]=' ';
			word2[i]=" ";
		}
		
		// 초기 점수 상태
		wins = 0;
		looses =0 ;
		winningPercentage = 0.0; //승률
		
		repaint();
	}
	
	// 게임 화면 그리는 메서드
	public void paint(Graphics screen) {
		super.paint(screen);
		Graphics2D screen2D = (Graphics2D) screen;
		screen2D.setFont(warningFont);
		
		screen2D.setColor(Color.WHITE);
		screen2D.fillOval(700,  50, 50, 50);
		screen2D.fillOval(740,  50, 50, 50);
		screen2D.fillOval(680, 70, 50, 50);
		screen2D.fillOval(700,  90, 50, 50);
		screen2D.fillOval(740,  90, 50, 50);
		screen2D.fillOval(760, 70, 50, 50);
		screen2D.fillOval(740, 70, 50, 50);
		
		screen2D.setColor(new Color(0x00036635));
		screen2D.drawLine(30, 670, 40, 660);
		screen2D.drawLine(40, 660, 50, 670);
		
		screen2D.drawLine(600, 600, 610, 590);
		screen2D.drawLine(610, 590, 620, 600);
		
		screen2D.drawLine(330, 570, 340, 560);
		screen2D.drawLine(340, 560, 350, 570);
	
		screen2D.setColor(Color.BLACK);
		screen2D.drawLine(70,60,130,60);
		screen2D.drawLine(70,60,70,80);
		screen2D.drawLine(130,60,130,170);
		screen2D.drawLine(60,170,160,170);
		
		if (level == 0) { // easy
		    drawHangman(screen2D, guessNum, 60, 80);
		} else if (level == 1) { // medium
		    drawHangman(screen2D, guessNum, 60, 80);
		} else if (level == 2) { // hard
		    drawHangman(screen2D, guessNum, 60, 80);
		}

		screen2D.setColor(Color.RED);
		screen2D.drawString(Integer.toString(guessNum)+" guesses left", 350, 100 );
		
		screen2D.setFont(normalFont);
		screen2D.setColor(Color.BLACK);
		
		screen2D.drawString("Wins:", 220, 200 );
		screen2D.drawString(Integer.toString((int)wins), 270, 200 );
		screen2D.drawString("Looses:", 300, 200 );
		screen2D.drawString(Integer.toString((int)looses), 365, 200 );
		screen2D.drawString("WinningPercentage:", 400, 200 );
		screen2D.drawString(Double.toString(winningPercentage)+"%", 560, 200 );
		
		screen2D.drawString("Current skill level: "+slevel[level], 300, 230 );
		
		screen2D.setFont(normalFont);
		screen2D.setColor(Color.BLACK);
		int xCoordinate = 300;
	    for (int i = 0; i < word_length; i++) { //_ 나 맞힌 알파벳 보여줌
	        screen2D.drawString(word2[i], xCoordinate, 150);
	        xCoordinate += 20; // 글자 간격 조절
	    }
	}
	
	// 행맨 캐릭터를 현재 남은 추측 횟수에 따라 단계적으로 그리는 메서드
	private void drawHangman(Graphics2D screen2D, int guessNum, int startX, int startY) {
	    switch (guessNum) {
	        case 1:
	            screen2D.drawOval(startX, startY, 20, 20); // 얼굴
	            break;
	        case 2:
	            screen2D.drawOval(startX, startY, 20, 20); //얼굴
	            screen2D.drawLine(startX + 10, startY + 20, startX + 10, startY + 50); // 몸통
	            break;
	        case 3:
	        	screen2D.drawOval(startX, startY, 20, 20); // 얼굴
	            screen2D.drawLine(startX + 10, startY + 20, startX + 10, startY + 50); // 몸통
	            screen2D.drawLine(startX + 10, startY + 30, startX + 30, startY + 30); // 팔
	            break;
	        case 4:
	        	screen2D.drawOval(startX, startY, 20, 20); // 얼굴
	            screen2D.drawLine(startX + 10, startY + 20, startX + 10, startY + 50); // 몸통
	            screen2D.drawLine(startX - 10, startY + 30, startX + 30, startY + 30); // 팔
	            break;
	        case 5:
	        	screen2D.drawOval(startX, startY, 20, 20); // 얼굴
	            screen2D.drawLine(startX + 10, startY + 20, startX + 10, startY + 50); // 몸통
	            screen2D.drawLine(startX - 10, startY + 30, startX + 30, startY + 30); // 팔
	            screen2D.drawLine(startX + 10, startY + 50, startX - 5, startY + 70); // 왼 다리
	            break;
	        case 6:
	        	screen2D.drawOval(startX, startY, 20, 20); // 얼굴
	            screen2D.drawLine(startX + 10, startY + 20, startX + 10, startY + 50); // 몸통
	            screen2D.drawLine(startX - 10, startY + 30, startX + 30, startY + 30); // 팔
	            screen2D.drawLine(startX + 10, startY + 50, startX - 5, startY + 70); // 왼 다리
	            screen2D.drawLine(startX + 10, startY + 50, startX + 25, startY + 70); // 오른 다리
	            break;
	        case 7:
	        	screen2D.drawOval(startX, startY, 20, 20); // 얼굴
	            screen2D.drawLine(startX + 10, startY + 20, startX + 10, startY + 50); // 몸통
	            screen2D.drawLine(startX - 10, startY + 30, startX + 30, startY + 30); // 팔
	            screen2D.drawLine(startX + 10, startY + 50, startX - 5, startY + 70); // 왼 다리
	            screen2D.drawLine(startX + 10, startY + 50, startX + 25, startY + 70); // 오른 다리
	            screen2D.drawOval(startX-20, startY+25, 10, 10); //왼손
	            break;
	        case 8:
	        	screen2D.drawOval(startX, startY, 20, 20); // 얼굴
	            screen2D.drawLine(startX + 10, startY + 20, startX + 10, startY + 50); // 몸통
	            screen2D.drawLine(startX - 10, startY + 30, startX + 30, startY + 30); // 팔
	            screen2D.drawLine(startX + 10, startY + 50, startX - 5, startY + 70); // 왼 다리
	            screen2D.drawLine(startX + 10, startY + 50, startX + 25, startY + 70); // 오른 다리
	            screen2D.drawOval(startX-20, startY+25, 10, 10); //왼손
	            screen2D.drawOval(startX+30, startY+25, 10, 10);//오른손
	            break;
	        case 9:
	        	screen2D.drawOval(startX, startY, 20, 20); // 얼굴
	            screen2D.drawLine(startX + 10, startY + 20, startX + 10, startY + 50); // 몸통
	            screen2D.drawLine(startX - 10, startY + 30, startX + 30, startY + 30); // 팔
	            screen2D.drawLine(startX + 10, startY + 50, startX - 5, startY + 70); // 왼 다리
	            screen2D.drawLine(startX + 10, startY + 50, startX + 25, startY + 70); // 오른 다리
	            screen2D.drawOval(startX-20, startY+25, 10, 10); //왼손
	            screen2D.drawOval(startX+30, startY+25, 10, 10);//오른손
	            screen2D.drawOval(startX-13, startY+70, 10, 10); //왼발
	            break;
	        case 10:
	            screen2D.drawOval(startX, startY, 20, 20); // 얼굴
	            screen2D.drawLine(startX + 10, startY + 20, startX + 10, startY + 50); // 몸통
	            screen2D.drawLine(startX - 10, startY + 30, startX + 30, startY + 30); // 팔
	            screen2D.drawLine(startX + 10, startY + 50, startX - 5, startY + 70); // 왼 다리
	            screen2D.drawLine(startX + 10, startY + 50, startX + 25, startY + 70); // 오른 다리
	            screen2D.drawOval(startX-20, startY+25, 10, 10); //왼손
	            screen2D.drawOval(startX+30, startY+25, 10, 10);//오른손
	            screen2D.drawOval(startX-13, startY+70, 10, 10); //왼발
	            screen2D.drawOval(startX + 23, startY + 70, 10, 10); //오른발
	            break;
	    }
	}

	// 랜덤 단어를 선택하고, 중복되지 않도록 체크 배열을 활용하는 메서드
	public void wordSelect() {
		double sel_num = Math.random() * 201;// 0~200.xx
		int selection = (int) Math.floor(sel_num); // 0~200
		while(true) { 
			if(checked[selection] == 0) {
				checked[selection] = 1;
				break;
			}
			else { //다시 고르기
				sel_num = Math.random() * 201;
				selection = (int)Math.floor(sel_num);
			}
		}   
		
		if(words[selection] != null) { // 고른 단어가 null이 아니면
			String sel_Word = words[selection].toLowerCase();
			word_length = sel_Word.length(); //단어 길이 설정

			char[] temp = sel_Word.toCharArray();   // character 배열로 변환
			for(int index1 = 0; index1 < word_length; index1++) {
				 word1[index1] = temp[index1]; //word1에 복사
			}
			for(int index2 = 0; index2 < word_length; index2++) {
				 word2[index2] = "_"; // .또는 _로 유저에게 단어의 철자 수를 알려줌
			}
		}
	}

	// 단어 배열(word2)을 초기화하고 새 단어를 선택하는 메서드
	public void word_reset() { 
		for(int i=0;i<12;i++) {
			word2[i]="_";
		}
		wordSelect();
	}
	
	// 사용자가 입력한 알파벳이 정답 단어에 있는지 확인하는 메서드

	public void spell_check(char spell) {
		int check_key = 0;
		for(int i=0; i<word_length; i++) {
			if(word1[i] != ' ') {
				if(word1[i] == spell) {
					word2[i] = "" + spell;
					check_key = 1;
					repaint();
				}
			}
		}
		if(check_key == 0) {
			guessNum--;
			repaint();
		}
		Adjust_display();
		repaint();
	}
	
	// 힌트 기능: 단어 끝에 "on"이 포함된 경우 이를 화면에 보여주는 메서드
	void select_hint() {
		int n;
		
		for(int i = word_length - 3;i<word_length;i++) {
			if(word1[i] != ' ') {
				if(word1[i] == 'o' && word1[i+1] == 'n') {
					word2[i] = "" + word1[i];
					word2[i+1] = "" + word1[i+1];
					repaint();
				}
			}
		}
	}
	
	// 게임의 현재 상태(승리/패배)를 확인하고 UI 상태를 조정하는 메서드
	public void Adjust_display() {
		boolean success = true;
		
		for(int i = 0;i<word_length; i++) {
			if(word2[i].equals("_")) { //단어 중 _가 있다면 패배
				success = false;
				break;
			}
		}
		
		if(success) { //이겼을 때
			disableAlphabetButtons();
			enableLevelButtons();
			wins++;
			winningPercentage = (wins/(wins+looses)) * 100.0;
			repaint();
		}
		
		if(guessNum <= 0) { //기회 없을 때(패배)
			disableAlphabetButtons();
			for(int i=0;i<12;i++){
				word2[i] = "" + word1[i];
			}
			enableLevelButtons();
			looses++;
			winningPercentage = (wins/(wins+looses)) * 100.0;
			repaint();
		}
	}
	
	// 알파벳 버튼들을 모두 비활성화하는 메서드
	private void disableAlphabetButtons() {
		for(JButton button : buttons) {
			button.setEnabled(false);
		}
	}
	
	// 난이도 버튼 및 BEGIN/HINT 버튼을 활성화하는 메서드
	private void enableLevelButtons() {
		begin.setEnabled(true);
		hint_btn.setEnabled(true);
		hint_bool = false;
		
		easy.setEnabled(level == 1 || level == 2);
		medium.setEnabled(level == 0 || level == 2);
		hard.setEnabled(level == 0 || level == 1);
	}
	
	// 버튼 클릭 이벤트 처리: BEGIN, 난이도, 알파벳, 힌트 클릭 처리
	public void actionPerformed(ActionEvent event) {
		String typed = event.getActionCommand();
		
		if(typed.equals("BEGIN")) {
			for(int i=0; i<12; i++){
				word1[i] = ' ';
				word2[i] = "_";
			}
				
			easy.setEnabled(false);
			medium.setEnabled(false);
			hard.setEnabled(false);
			hint_btn.setEnabled(false);

			if(level == 0) {
				guessNum = 10;
			} 
			else if(level == 1) {
				guessNum = 8;
			} 
			else if(level == 2) {
				guessNum = 6;
			}

			repaint();

			for(JButton button : buttons) {
				button.setEnabled(true);
			}
			
			begin.setEnabled(false);
			word_reset();		
			
			if(hint_bool == true) {
				select_hint();
			}
		}
		
		for(JButton button : buttons) {
			if(typed.equals(button.getText())) {
				button.setEnabled(false);
				spell_check(typed.toLowerCase().charAt(0));
				break;
			}
		}
		if(typed.equals("EASY")) {
			easy.setEnabled(false);
			medium.setEnabled(true);
			hard.setEnabled(true);
			level = 0;
			repaint();
		}
		if(typed.equals("MEDIUM")) {
			medium.setEnabled(false);
			easy.setEnabled(true);
			hard.setEnabled(true);
			level = 1;
			repaint();
		}
		if(typed.equals("HARD")) {
			easy.setEnabled(true);
			medium.setEnabled(true);
			hard.setEnabled(false);
			level = 2;
			repaint();
		}
		if(typed.equals("Hint")) {
			hint_bool = true;
		}
	}

	// 메인 메서드
	public static void main(String [] args) {
		Hangman h=new Hangman();
		
		h.init();
	}
}
