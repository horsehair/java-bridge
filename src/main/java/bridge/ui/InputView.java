package bridge.ui;

import static bridge.constant.BridgeRule.MAXIMUM_SIZE;
import static bridge.constant.BridgeRule.MINIMUM_SIZE;

import bridge.constant.ErrorMessage;
import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String INPUT_BRIDGE_SIZE = "다리의 길이를 입력해주세요.";
    private static final String INPUT_MOVING = "이동할 칸을 선택해주세요. (위: U, 아래: D)";
    private static final String INPUT_GAME_COMMAND = "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";
    private static final String NO_LETTER = "";

    public int readBridgeSize() {
        System.out.println(INPUT_BRIDGE_SIZE);
        int bridgeSize = getBridgeSizeInputAfterParse();
        System.out.println(NO_LETTER);
        return bridgeSize;
    }

    private int getBridgeSizeInputAfterParse() {
        try {
            int bridgeSize = Integer.parseInt(Console.readLine());
            return bridgeSize;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    String.format(ErrorMessage.INPUT_ONLY_NUMBER_FORMAT.getValue(), MINIMUM_SIZE, MAXIMUM_SIZE));
        }
    }

    public String readMoving() {
        System.out.println(INPUT_MOVING);
        String moving = Console.readLine();
        return moving;
    }

    public String readGameCommand() {
        System.out.println(INPUT_GAME_COMMAND);
        String gameCommand = Console.readLine();
        return gameCommand;
    }
}
