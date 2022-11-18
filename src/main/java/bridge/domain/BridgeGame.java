package bridge.domain;

import static bridge.constant.GameStatus.ON_WAY;
import static bridge.constant.GameStatus.FAIL;
import static bridge.constant.GameStatus.END;

import bridge.constant.GameStatus;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final List<String> bridge;
    private GameStatus gameStatus;
    private int location;
    private int tryCount;

    public BridgeGame(List<String> bridge) {
        this.bridge = bridge;
        this.gameStatus = ON_WAY;
        this.location = 0;
        this.tryCount = 0;
        System.out.println(this.bridge);
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(String moving) {
        this.tryCount += 1;
        if (isCorrect(moving)) {
            this.location += 1;
            checkIsEnd();
            return;
        }
        this.gameStatus = FAIL;
    }

    private boolean isCorrect(String moving) {
        return this.bridge.get(this.location) == moving;
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
    }

    public boolean isOnWay() {
        return this.gameStatus == ON_WAY;
    }

    public boolean isFail() {
        return this.gameStatus == FAIL;
    }

    public boolean isEnd() {
        return this.gameStatus == END;
    }

    private void checkIsEnd() {
        int bridgeSize = this.bridge.size();
        if (bridgeSize == this.location) {
            this.gameStatus = END;
        }
    }
}
