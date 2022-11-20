package bridge.domain;

import static bridge.constant.GameStatus.END;
import static bridge.constant.GameStatus.FAIL;
import static bridge.constant.GameStatus.ON_WAY;
import static bridge.constant.Moving.LOWER_SIDE;
import static bridge.constant.Moving.UPPER_SIDE;
import static org.assertj.core.api.Assertions.assertThat;

import bridge.constant.GameStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BridgeMonitorTest {
    @DisplayName("한 턴의 이동에 대한 현황을 기록한다.")
    @Nested
    class recordMoving {

        @DisplayName("위 방향 이동에 성공")
        @Test
        void should_RecordCorrectMoving_When_MoveCorrectly() {
            // given
            BridgeMonitor bridgeMonitor = new BridgeMonitor();
            String moving = UPPER_SIDE;
            GameStatus gameStatusAfterMoving = ON_WAY;
            // when
            bridgeMonitor.record(moving, gameStatusAfterMoving);
            // then
            assertThat(bridgeMonitor.getPicture()).isEqualTo("[ O ]" + "\n" + "[   ]");
        }

        @DisplayName("아래 방향 이동에 실패")
        @Test
        void should_RecordWrongMoving_When_MoveWrong() {
            // given
            BridgeMonitor bridgeMonitor = new BridgeMonitor();
            String moving = LOWER_SIDE;
            GameStatus gameStatusAfterMoving = FAIL;
            // when
            bridgeMonitor.record(moving, gameStatusAfterMoving);
            // then
            assertThat(bridgeMonitor.getPicture()).isEqualTo("[   ]" + "\n" + "[ X ]");
        }

        @DisplayName("위, 아래 방향 이동 성공 후, 아래 방향 이동에 실패")
        @Test
        void should_RecordWrongMoving_When_MoveWrongAfter2Moving() {
            // given
            BridgeMonitor bridgeMonitor = new BridgeMonitor();
            bridgeMonitor.record(UPPER_SIDE, ON_WAY);
            bridgeMonitor.record(LOWER_SIDE, ON_WAY);
            // when
            bridgeMonitor.record(LOWER_SIDE, FAIL);
            // then
            assertThat(bridgeMonitor.getPicture()).isEqualTo(
                    "[ O |   |   ]" + "\n" + "[   | O | X ]"
            );
        }

        @DisplayName("위, 아래 방향 이동 성공 후, 아래 방향 이동에 성공하며 다리를 모두 건너기 완료")
        @Test
        void should_RecordCorrectMoving_When_CrossBridgeCompletely() {
            // given
            BridgeMonitor bridgeMonitor = new BridgeMonitor();
            bridgeMonitor.record(UPPER_SIDE, ON_WAY);
            bridgeMonitor.record(LOWER_SIDE, ON_WAY);
            // when
            bridgeMonitor.record(LOWER_SIDE, END);
            // then
            assertThat(bridgeMonitor.getPicture()).isEqualTo(
                    "[ O |   |   ]" + "\n" + "[   | O | O ]"
            );
        }
    }
}