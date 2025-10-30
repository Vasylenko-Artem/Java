package tasks;

import java.awt.*;
import java.util.Random;

import tasks.Task1.MovingHouse;
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

        private final int houseCount = 4;
        private final MovingHouse[] capitalHouses;
        private final MovingHouse[] woodenHouses;

        public Task1Canvas() {
            super(16); // ~60 FPS

            int w = 800;
            int h = 600;

            capitalHouses = new MovingHouse[houseCount];
            woodenHouses = new MovingHouse[houseCount];

            Random rand = new Random();

            // We create capital houses
            for (int i = 0; i < houseCount; i++) {
                float x = rand.nextInt(w);
                float y = rand.nextInt(h);
                capitalHouses[i] = new MovingHouse(x, y, w, h, true);
                new Thread(capitalHouses[i]).start();
            }

            // We create wooden houses
            for (int i = 0; i < houseCount; i++) {
                float x = rand.nextInt(w);
                float y = rand.nextInt(h);
                woodenHouses[i] = new MovingHouse(x, y, w, h, false);
                new Thread(woodenHouses[i]).start();
            }
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            g2.setColor(new Color(20, 20, 20));
            g2.fillRect(0, 0, getWidth(), getHeight());

            // Zone of capital buildings
            g2.setColor(new Color(40, 40, 60));
            g2.fillRect(0, 0, getWidth() / 2, getHeight() / 2);

            // Zone of wooden houses
            g2.setColor(new Color(60, 40, 40));
            g2.fillRect(getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight() / 2);

            // We draw houses
            for (MovingHouse h : capitalHouses)
                h.draw(g2, Color.CYAN);

            for (MovingHouse h : woodenHouses)
                h.draw(g2, Color.ORANGE);
        }

        @Override
        protected void updateFrame() {
            // streams themselves update the state of buildings
        }
    }

    static class MovingHouse implements Runnable {
        private float x, y;
        private float targetX, targetY;
        private final float speed = 2.0f;
        private final boolean isCapital;
        private final int w, h;
        private boolean moving = true;
        private final Random rand = new Random();

        public MovingHouse(float x, float y, int w, int h, boolean isCapital) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.isCapital = isCapital;
            setTarget();
        }

        private void setTarget() {
            if (isCapital) {
                // Upper left quarter
                if (x < w / 2 && y < h / 2) {
                    moving = false;
                    return;
                }
                targetX = rand.nextFloat() * (w / 2);
                targetY = rand.nextFloat() * (h / 2);
            } else {
                // Lower right quarter
                if (x > w / 2 && y > h / 2) {
                    moving = false;
                    return;
                }
                targetX = (w / 2) + rand.nextFloat() * (w / 2);
                targetY = (h / 2) + rand.nextFloat() * (h / 2);
            }
        }

        @Override
        public void run() {
            while (moving) {
                float dx = targetX - x;
                float dy = targetY - y;
                float dist = (float) Math.sqrt(dx * dx + dy * dy);

                if (dist < 1.0f) {
                    moving = false;
                    break;
                }

                x += (dx / dist) * speed;
                y += (dy / dist) * speed;

                try {
                    Thread.sleep(16); // ~60 FPS
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void draw(Graphics2D g2, Color color) {
            g2.setColor(color);
            g2.fillRect((int) x, (int) y, 20, 20);
        }
    }
}
