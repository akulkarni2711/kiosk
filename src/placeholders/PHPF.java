package placeholders;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.Document;

import GUI.Kiosk;

@SuppressWarnings("serial")
public class PHPF extends JPasswordField {

	private Kiosk kiosk;
	private String placeholder;

	public PHPF() {
	}

	public PHPF(final Document pDoc, final String pText, final int pColumns) {
		super(pDoc, pText, pColumns);
	}

	public PHPF(final int pColumns) {
		super(pColumns);
	}

	public PHPF(final String pText) {
		super(pText);
	}

	public PHPF(final String pText, final int pColumns) {
		super(pText, pColumns);
	}

	public String getPlaceholder() {
		return placeholder;
	}

	@Override
	protected void paintComponent(final Graphics pG) {
		kiosk = kiosk.getInstance();
		super.paintComponent(pG);

		if (placeholder == null || placeholder.length() == 0 || getText().length() != 0) {
			return;
		}

		final Graphics2D g = (Graphics2D) pG;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		Font f = new Font("Verdana", Font.PLAIN, 13);
		g.setFont(f);
		g.setColor(Color.GRAY);
		g.drawString(placeholder, getInsets().left, pG.getFontMetrics().getMaxAscent() + getInsets().top);
	}

	public void setPlaceholder(final String s) {
		placeholder = s;
	}

}