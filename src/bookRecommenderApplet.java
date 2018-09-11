import java.applet.Applet;
import java.awt.*;

public class bookRecommenderApplet extends Applet {
    public void init() {
        setBackground(Color.white);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("Book Recommender", 10, 50);
    }
}
