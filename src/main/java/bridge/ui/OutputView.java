package bridge.ui;

import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {
    private static final String START_BRACKET = "[";
    private static final String SPACE = "   ";
    private static final String CORRECT_MOVING = " O ";
    private static final String WRONG_MOVING = " X ";
    private static final String BETWEEN_LINE = "|";
    private static final String END_BRACKET = "]";

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(List<String> userRecord, boolean isCorrect) {
        if (isCorrect) {
            printMapOnWay(userRecord, isCorrect);
            return;
        }
//        printMapOnFail(userRecord, isCorrect);
    }

    private void printMapOnWay(List<String> userRecord, boolean isCorrect) {
        StringBuilder upperLine = new StringBuilder(START_BRACKET);
        StringBuilder lowerLine = new StringBuilder(START_BRACKET);
        userRecord.forEach(moving -> {
            if (moving.equals("U")) {
                upperLine.append(CORRECT_MOVING + BETWEEN_LINE);
                lowerLine.append(SPACE + BETWEEN_LINE);
            }
            if (moving.equals("D")) {
                upperLine.append(SPACE + BETWEEN_LINE);
                lowerLine.append(CORRECT_MOVING + BETWEEN_LINE);
            }
        });
        int length = upperLine.length();
        upperLine.replace(length-1, length, END_BRACKET);
        lowerLine.replace(length-1, length, END_BRACKET);
        System.out.println(upperLine.toString() + "\n" + lowerLine.toString());
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult() {
    }
}
