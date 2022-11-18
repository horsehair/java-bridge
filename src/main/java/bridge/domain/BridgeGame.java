package bridge.domain;

import static bridge.constant.GameStatus.ON_WAY;
import static bridge.constant.GameStatus.FAIL;
import static bridge.constant.GameStatus.END;

import bridge.constant.GameStatus;
import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final List<String> bridge;
    private GameStatus gameStatus;
    private List<String> userRecord;
    private int tryCount;

    public BridgeGame(List<String> bridge) {
        this.bridge = bridge;
        this.gameStatus = ON_WAY;
        this.userRecord = new ArrayList<>();
        this.tryCount = 1;
        System.out.println(this.bridge);
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(String moving) {
        recordMoving(moving);
        if (isCorrect(moving)) {
            checkIsEnd();
            return;
        }
        this.gameStatus = FAIL;
    }

    private void recordMoving(String moving) {
        userRecord.add(moving);
    }

    private boolean isCorrect(String moving) {
        int location = this.userRecord.size();
        return this.bridge.get(location - 1).equals(moving);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry(String inputGameCommand) {
        if (inputGameCommand.equals("R")) {
            this.tryCount += 1;
            this.gameStatus = ON_WAY;
            int userRecordSize = this.userRecord.size();
            this.userRecord.remove(userRecordSize - 1);
        }
        if (inputGameCommand.equals("Q")) {
            this.gameStatus = END;
        }
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

    public List<String> getUserRecord() {
        return this.userRecord;
    }

    private boolean checkIsEnd() {
        int bridgeSize = this.bridge.size();
        int userTrackingSize = this.userRecord.size();
        if (bridgeSize == userTrackingSize) {
            this.gameStatus = END;
            return true;
        }
        return false;
    }

    public boolean checkIsRightEnd() {
        if (!isSameSizeBridgeAndUserRecord()) {
            return false;
        }
        int bridgeSize = this.bridge.size();
        String lastOfBridge = this.bridge.get(bridgeSize - 1);
        String lastOfUserRecord = this.userRecord.get(bridgeSize - 1);
        if (lastOfUserRecord.equals(lastOfBridge)) {
            return true;
        }
        return false;
    }

    public boolean isSameSizeBridgeAndUserRecord() {
        int bridgeSize = this.bridge.size();
        int userTrackingSize = this.userRecord.size();
        return bridgeSize == userTrackingSize;
    }

    public int getTryCount() {
        return this.tryCount;
    }
}
