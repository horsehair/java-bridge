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
        while (!this.bridgeGame.inEnd()) {
            playTurn();
        }
        printResultOfMove();
    }

    private void playTurn() {
        while (this.bridgeGame.isNotOnWay()) {
            String inputDirection = inputView.readMoving();
            this.bridgeGame.move(inputDirection);
            printResultOfGame();
        }
        if (this.bridgeGame.isFail) {
            retryTurn();
        }
    }

    private void retryTurn() {
       String inputGameCommand = inputView.readGameCommand();
       this.bridgeGame.retry(inputGameCommand);
    }

    private void printResultOfMove() {
        String resultNow = this.bridgeGame.getResult();
        this.outputView.printMap(resultNow);
    }

    private void printResultOfGame() {
        String resultNow = this.bridgeGame.getResult();
        this.outputView.printResult(resultNow);
    }
}
