package bridge;

import bridge.controller.BridgeController;

public class Application {

    public static void main(String[] args) {
        try {
            BridgeController bridgeController = new BridgeController();
            bridgeController.playGame();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
