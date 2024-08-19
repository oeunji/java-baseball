package baseball;

import java.util.Scanner;

public class Application {

    int numArr[] = new int[3];  // 크기가 3인 배열. 세 자리의 난수 저장.
    int inputArr[] = new int[3];   // 사용자 입력 배열
    int strike = 0; // 스트라이크 개수
    int ball = 0;   // 볼 개수

    public void createRandomNumber() {       // 난수 생성 메서드
        boolean isDuplicate;
        do {
            isDuplicate = false;
            for (int i = 0; i < numArr.length; i++) {
                numArr[i] = (int) (Math.random() * 9) + 1; // 1 ~ 9 난수 생성
            }

            // 중복 체크
            if (numArr[0] == numArr[1] || numArr[1] == numArr[2] || numArr[0] == numArr[2]) {
                isDuplicate = true;
            }
        } while (isDuplicate);
    }

    public void inputUserAnswer() {  // 사용자 입력 메서드
        Scanner scanner = new Scanner(System.in);
        createRandomNumber();

        while (true) {
            System.out.println("숫자를 입력해주세요 :");
            for (int i = 0; i < inputArr.length; i++) {
                inputArr[i] = scanner.nextInt();
                for (int j = 0; j < i; j++) {
                    if (inputArr[i] == inputArr[j]) {
                        System.out.println("중복된 숫자가 있습니다. 다시 입력해주세요.");
                        i--;
                        break;
                    }
                }
            }

            strike = 0;
            ball = 0;

            for (int i = 0; i < inputArr.length; i++) { // 스트라이크, 볼 개수
                for (int j = 0; j < numArr.length; j++) {
                    if (inputArr[i] == numArr[j]) {
                        if (i == j) {
                            strike++;
                        } else {
                            ball++;
                        }
                    }
                }
            }

            if (strike == 0 && ball == 0) { // 숫자 비교
                System.out.println("낫싱");
            } else {
                System.out.println(ball + "볼 " + strike + "스트라이크");
            }

            if (strike == 3) {
                System.out.println(strike + "스트라이크");
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

                int choice = scanner.nextInt();
                if (choice == 1) {
                    // 상태 초기화
                    createRandomNumber();
                    strike = 0;
                    ball = 0;
                } else if (choice == 2) {
                    break;
                } else {
                    System.out.println("잘못된 값이 입력되었습니다. 종료합니다.");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.inputUserAnswer();
    }
}
