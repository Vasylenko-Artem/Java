package utils.window;

import java.awt.*;
import javax.swing.Timer;

public abstract class AnimationCanvas extends Canvas {

    private final Timer timer;

    public AnimationCanvas(int delayMs) {
        // The timer automatically calls updateFrame() every delayMs milliseconds
        timer = new Timer(delayMs, e -> {
            updateFrame();
            repaint();
        });
        timer.start();
    }

    /*
     * Override this method to update the frame
     * This method will be called every delayMs milliseconds
     * and will be automatically called by the timer
     */

    protected abstract void updateFrame();
}
