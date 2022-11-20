package bridge.domain;

import static bridge.constant.GameCommand.QUIT;
import static bridge.constant.GameCommand.RETRY;
import static bridge.constant.GameStatus.END;
import static bridge.constant.GameStatus.FAIL;
import static bridge.constant.GameStatus.ON_WAY;
import static bridge.constant.Moving.LOWER_SIDE;
import static bridge.constant.Moving.UPPER_SIDE;

import bridge.constant.GameStatus;
import bridge.constant.Moving;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private GameStatus gameStatus;
    private int tryCount;
    private BridgeMover bridgeMover;
    private BridgeMonitor bridgeMonitor;

    public BridgeGame(List<String> bridge) {
        this.gameStatus = ON_WAY;
        this.tryCount = 1;
        this.bridgeMover = new BridgeMover(bridge);
        this.bridgeMonitor = new BridgeMonitor();
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public String move(String moving) {
        validateMoving(moving);
        GameStatus gameStatusAfterMoving = this.bridgeMover.go(moving);
        this.gameStatus = gameStatusAfterMoving;
        this.bridgeMonitor.record(moving, gameStatusAfterMoving);
        return this.bridgeMonitor.getPicture();
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry(String gameCommand) {
        if (gameCommand.equals(RETRY)) {
            this.gameStatus = ON_WAY;
            this.tryCount += 1;
            this.bridgeMonitor.turnBackOnce();
        }
        if (gameCommand.equals(QUIT)) {
            this.gameStatus = END;
        }
    }

    public boolean isOnWay() {
        return this.gameStatus.equals(ON_WAY);
    }

    public boolean isFail() {
        return this.gameStatus.equals(FAIL);
    }

    public boolean isEnd() {
        return this.gameStatus.equals(END);
    }

    public boolean isSuccess() {
        return this.bridgeMover.isCrossCompletely();
    }

    public String getPicture() {
        return this.bridgeMonitor.getPicture();
    }

    public int getTryCount() {
        return this.tryCount;
    }

    private void validateMoving(String moving) {
        if (moving.equals(UPPER_SIDE) || moving.equals(LOWER_SIDE)) {
            return;
        }
        throw new IllegalArgumentException(String.format(
                "[ERROR] 위로 이동하려면 \"%s\", 아래로 이동하려면 \"%s\"를 입력하세요.", UPPER_SIDE, LOWER_SIDE
        ));
    }
}
