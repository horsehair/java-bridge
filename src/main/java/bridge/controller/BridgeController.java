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
        this.outputView.announceStartGame();
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
        if (this.bridgeGame.checkIsRightEnd()) {
            System.out.println();
        }
        outputView.announceEndGame();
        printResultOfMove();
    }

    private void playTurn() {
        String moving = this.inputView.readMoving();
        this.bridgeGame.move(moving);
        printResultOfMove();
        if (this.bridgeGame.isEnd()) {
            return;
        }
        if (this.bridgeGame.isFail()) {
            retryTurn();
        }
    }

    private void retryTurn() {
       String inputGameCommand = inputView.readGameCommand();
       this.bridgeGame.retry(inputGameCommand);
    }

    private void printResultOfMove() {
        List<String> userRecord = this.bridgeGame.getUserRecord();
        boolean isCorrect = this.bridgeGame.isOnWay();
        if (this.bridgeGame.isEnd()) {
            if (this.bridgeGame.isSameSizeBridgeAndUserRecord()) {
                isCorrect = this.bridgeGame.checkIsRightEnd();
            }
        }
        this.outputView.printMap(userRecord, isCorrect);
    }
//
//    private void printResultOfGame() {
//        List<String> resultNow = this.bridgeGame.getUserRecord();
//        this.outputView.printResult(resultNow);
//    }
}
