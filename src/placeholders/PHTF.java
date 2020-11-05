package placeholders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextField;

import GUI.Kiosk;

@SuppressWarnings("serial")
public class PHTF extends JTextField {

    private String ph;
    private Kiosk kiosk;

    public PHTF() {}


    public PHTF(final int pColumns) {
        super(pColumns);
    }

    public String getPlaceholder() {
        return ph;
    }

    public void setPlaceholder(final String s) {
        ph = s;
    }
    
    @Override
    protected void paintComponent(final Graphics pG) {
    	kiosk = Kiosk.getInstance();
        super.paintComponent(pG);

        if (ph == null || ph.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_OFF);
        Font f = new Font("Verdana", Font.PLAIN, 13);
        g.setFont(f);
        g.setColor(Color.GRAY);
        g.drawString(ph, getInsets().left, pG.getFontMetrics()
            .getMaxAscent() + getInsets().top+4);
    }

}