package utils.window;

import java.awt.*;
import java.awt.event.*;

public class FlexibleFrame extends Frame {

    private String title = "";
    private int defaultWidth = 800;
    private int defaultHeight = 600;
    private int minWidth = 400;
    private int minHeight = 300;
    private int maxWidth = 800;
    private int maxHeight = 600;

    public FlexibleFrame(String title, int minWidth, int minHeight, int maxWidth, int maxHeight, int initWidth,
            int initHeight) {
        init(title, minWidth, minHeight, maxWidth, maxHeight, initWidth, initHeight);
    }

    public FlexibleFrame(String title, int initWidth, int initHeight) {
        init(title, minWidth, minHeight, maxWidth, maxHeight, initWidth, initHeight);
    }

    public FlexibleFrame(String title) {
        init(title, minWidth, minHeight, maxWidth, maxHeight, defaultWidth, defaultHeight);
    }

    public FlexibleFrame() {
        init(title, minWidth, minHeight, maxWidth, maxHeight, defaultWidth, defaultHeight);
    }

    // Initialization method
    private void init(String title, int minWidth, int minHeight,
            int maxWidth, int maxHeight, int initWidth, int initHeight) {

        this.title = title;
        this.minWidth = minWidth;
        this.minHeight = minHeight;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;

        setTitle(title);
        setSize(initWidth, initHeight);
        setVisible(true);

        // Size limit
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int w = getWidth();
                int h = getHeight();

                if (w < FlexibleFrame.this.minWidth)
                    w = FlexibleFrame.this.minWidth;
                if (h < FlexibleFrame.this.minHeight)
                    h = FlexibleFrame.this.minHeight;
                if (w > FlexibleFrame.this.maxWidth)
                    w = FlexibleFrame.this.maxWidth;
                if (h > FlexibleFrame.this.maxHeight)
                    h = FlexibleFrame.this.maxHeight;

                setSize(w, h);
            }
        });

        // Closing the window
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
