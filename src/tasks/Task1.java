package tasks;

import java.awt.*;

import tasks.Task1.Task1Canvas;
import utils.window.AnimationCanvas;
import utils.window.FlexibleFrame;

public class Task1 {

    public static void run() {
        FlexibleFrame frame = new FlexibleFrame();
        frame.setTitle("Task 1");

        Task1Canvas canvas = new Task1Canvas();
        frame.add(canvas);

        frame.setVisible(true);
    }

    static class Task1Canvas extends AnimationCanvas {

        private float size = 50;
        private float speed = 1.0f;
        private boolean growing = true;

        public Task1Canvas() {
            super(10); // 10 мс
        }

        @Override
        protected void updateFrame() {
            if (growing) {
                size += speed;
                if (size >= 200)
                    growing = false;
            } else {
                size -= speed;
                if (size <= 50)
                    growing = true;
            }
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            Color darkBg = new Color(13, 13, 13);
            Color orange = new Color(255, 107, 0);

            // Background
            g2.setColor(darkBg);
            g2.fillRect(0, 0, getWidth(), getHeight());

            // Circle position
            int x = getWidth() / 2 - (int) (size / 2);
            int y = getHeight() / 2 - (int) (size / 2);

            // Gradient
            Color centerColor = orange;
            Color edgeColor = centerColor.darker();
            GradientPaint gp = new GradientPaint(
                    x + size / 4, y + size / 4, centerColor,
                    x + size, y + size, edgeColor);

            g2.setPaint(gp);
            g2.fillOval(x, y, (int) size, (int) size);
        }
    }

}
