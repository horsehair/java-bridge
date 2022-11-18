package bridge.controller;

import bridge.BridgeRandomNumberGenerator;
import bridge.domain.BridgeGame;
import bridge.domain.BridgeMaker;
import bridge.ui.InputView;
import bridge.ui.OutputView;
import java.util.List;

public class BridgeController {
    private BridgeGame bridgeGame;
    private InputView inputView;
    private OutputView outputView;

    public BridgeController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        List<String> bridge = makeBridge();
        this.bridgeGame = new BridgeGame(bridge);
    }

    private List<String> makeBridge() {
        BridgeRandomNumberGenerator bridgeRandomNumberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeRandomNumberGenerator);
        int bridgeSize = inputView.readBridgeSize();
        return bridgeMaker.makeBridge(bridgeSize);
    }

    public void playGame() {
        while (!this.bridgeGame.isEnd()) {
            playTurn();
        }
        printResultOfGame();
    }
//
    private void playTurn() {
        while (!this.bridgeGame.isOnWay()) {
            String moving = inputView.readMoving();
            this.bridgeGame.move(moving);
            printResultOfMove();
        }
        if (this.bridgeGame.isFail()) {
//            retryTurn();
        }
    }
//
//    private void retryTurn() {
//       String inputGameCommand = inputView.readGameCommand();
//       this.bridgeGame.retry(inputGameCommand);
//    }
//
    private void printResultOfMove() {
        String resultNow = this.bridgeGame.getResult();
        this.outputView.printMap(resultNow);
    }
//
    private void printResultOfGame() {
        String resultNow = this.bridgeGame.getResult();
        this.outputView.printResult(resultNow);
    }
}
