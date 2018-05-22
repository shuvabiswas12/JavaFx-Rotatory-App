package sample.animation;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class FadeEffect {

    private FadeTransition transition;

    public FadeEffect(Node node) {
        transition = new FadeTransition(Duration.millis(2000), node);
    }

    public void setEffect() {
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.setByValue(0);
        transition.setCycleCount(1);
        transition.setAutoReverse(false);
        transition.play();
    }
}
