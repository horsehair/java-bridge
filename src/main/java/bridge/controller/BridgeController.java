package bridge.controller;

import bridge.domain.BridgeGame;
import bridge.ui.InputView;
import bridge.ui.OutputView;

public class BridgeController {
    private BridgeGame bridgeGame;
    private InputView inputView;
    private OutputView outputView;

    public BridgeController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void playGame() {

    }
}
