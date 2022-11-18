package bridge.domain;

import bridge.BridgeNumberGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class BridgeMaker {

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    /**
     * @param size 다리의 길이
     * @return 입력받은 길이에 해당하는 다리 모양. 위 칸이면 "U", 아래 칸이면 "D"로 표현해야 한다.
     */
    public List<String> makeBridge(int size) {
        validate(size);
        List<String> bridge = new ArrayList<>();
        IntStream.range(0, size)
                .forEach(number -> {
                    bridge.add(makeOneSpace());
                });
        return bridge;
    }

    private String makeOneSpace() {
        int bridgeNumber = bridgeNumberGenerator.generate();
        if (bridgeNumber == 0) {
            return "D";
        }
        return "U";
    }

    private void validate(int size) {
        if (3 <= size && size <= 20) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 3과 20 사이 숫자를 입력하세요.");
    }
}
