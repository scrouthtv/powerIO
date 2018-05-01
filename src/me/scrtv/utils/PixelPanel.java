package me.scrtv.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import me.scrtv.main.Main;

public class PixelPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Color[][] pixels;
	private int scale, width, height, mx, my, pushx, pushy; // w & h is *not* upscaled
	private boolean udsymetry, lrsymetry;
	private HashMap<String, Integer> properties = new HashMap<String, Integer>();
	private HashMap<Integer[], ColoredText> overlays = new HashMap<Integer[], ColoredText>();
	//private double rotation = 0.0; // 0 - 360 = 2pi
	private double cosa, sina;
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		for(int x = 0; x < pixels.length; x++) {
			for(int y = 0; y < pixels[x].length; y++) {
				g.setColor(pixels[x][y]);
				for(int i1 = 0; i1 < scale; i1++) {
					for(int i2 = 0; i2 < scale; i2++) {
						int oldx = scale * x + i1 - mx; // not
						int oldy = scale * y + i2 - my; // rotated
						
						int newx = (int)   (cosa * oldx + sina * oldy) + mx + pushx; // rotated and back
						int newy = (int) (- sina * oldx + cosa * oldy) + my + pushy; // to g2d coords
						g.fillRect(newx, newy, 1, 1); // pixel graphics by scale * ( x + y)
					}
				}
				g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.0f));
			}
		}
		g.setColor(Color.BLACK);
		for(Entry<Integer[], ColoredText> ol : overlays.entrySet()) {
			g.setColor(ol.getValue().getColor());
			g.setFont(ol.getValue().getFont());
			if(ol.getValue().isCentered())
				drawCenteredString(g, ol.getValue().getText(), this.getBounds(), ol.getValue().getFont());
			else
				g.drawString(ol.getValue().getText(), ol.getKey()[0], ol.getKey()[1]);
		}
	}
	
	// 0...degrees...360
	public void rotate(double degrees) {
		System.out.println("rotated around " + degrees);
		//rotation = degrees;
		cosa = Math.cos(degrees * 2 * Math.PI / 360);
		sina = Math.sin(degrees * 2 * Math.PI / 360);
		repaint();
		System.out.println(mx);
	}

	public void addOverlay(String txt, int x, int y, Color c, Font f, boolean centered) {
		overlays.put(new Integer[] {x, y}, new ColoredText(txt, c, f, centered));
	}
	public void clearOverlays() {
		overlays.clear();
	}
	
	// scale will be set to at least 1
	public PixelPanel(int w, int h, int scale) {
		scale = Math.max(scale, 1);
		// [w * scale][h * scale] ??
		pixels = new Color[w][h];
		int size = (int) Math.sqrt(w * w * scale * scale + h * h * scale * scale);
		pushx = (size - w * scale) / 2;
		pushy = (size - h * scale) / 2;
		this.setPreferredSize(new Dimension(size, size));
		this.scale = scale;
		udsymetry = false;
		width = w;
		height = h;
		mx = scale * w / 2;
		my = scale * h / 2;
		rotate(0);
	}
	/*public PixelPanel(int w, int h, int scale, double rotation) {
	*	this(w, h, scale);
	*	this.rotation = rotation;
	}*/
	
	// this will draw the pixel(s) according to the set scale
	public void drawPixel(int x, int y, Color c) {
		pixels[x][y] = c;
		if(lrsymetry)
			pixels[width - x - 1][y] = c;
		if(udsymetry)
			pixels[x][height - y - 1] = c;
	}
	//TODO drawArea not working
	/*public void drawArea(Area a, Color c) {
		for(int x = a.x1(); x < a.x2(); x++) {
			for(int y = a.y1(); y < a.y2(); y++) {
				System.out.println(x + " " + y);
				drawPixel(x, y, c);
			}
		}
	}*/
	public void move(int x, int y, boolean usebounds) {
		int newx = (int) (this.getBounds().getX() + x * Main.mvMulti);
		if(usebounds) newx = Math.max(Math.min(newx, Main.mainwidth - this.getPreferredSize().width - Main.mainframe.getInsets().right - Main.mainframe.getInsets().left), 0);
		int newy = (int) (this.getBounds().getY() + y * Main.mvMulti);
		if(usebounds) newy = Math.max(Math.min(newy, Main.mainheight - this.getPreferredSize().height - Main.mainframe.getInsets().bottom - Main.mainframe.getInsets().top), 0);
		this.setBounds(newx, newy, this.getPreferredSize().width, this.getPreferredSize().height);
	}
	public void move(int x, int y) {
		move(x, y, true);
	}
	// will draw the mirrored points on the left & right side
	public void setUDSymetry(boolean symetry) {
		udsymetry = symetry;
	}
	// will draw the mirrored points on the up & down part
	public void setLRSymetry(boolean symetry) {
		lrsymetry = symetry;
	}
	public void setProperty(String key, Integer value) {
		properties.put(key, value);
	}
	public Integer getProperty(String key) {
		return properties.get(key);
	}
	public boolean isOnMap() {
		return isOnMap(Side.values());
	}
	public boolean isOnMap(Side[] sides) {
		if(this.getBounds().getX() + this.getPreferredSize().getWidth() < 0 && Utils.contains(sides, Side.W))
			return false;
		if(this.getBounds().getX() > Main.mainwidth && Utils.contains(sides, Side.E))
			return false;
		if(this.getBounds().getY() + this.getPreferredSize().getHeight() < 0 && Utils.contains(sides, Side.N))
			return false;
		if(this.getBounds().getY() > Main.mainheight && Utils.contains(sides, Side.S))
			return false;
		return true;
	}
	public int getScale() {
		return scale;
	}
	public boolean collides(JComponent test) {
		Rectangle rectB = test.getBounds();
		Rectangle result = SwingUtilities.computeIntersection(getX(), getY(), getWidth() - pushx, getHeight(), rectB);
		return (result.getWidth() > 0 && result.getHeight() > 0);
	}
	public List<PixelPanel> collides(List<PixelPanel> tests) {
		List<PixelPanel> colliders = new ArrayList<PixelPanel>();
		for(PixelPanel test : tests)
			if(collides(test))
				colliders.add(test);
		return colliders;
	}
	public void pSetBackground(Color c) {
		for(int x = 0; x < pixels.length; x++) {
			for(int y = 0; y < pixels[x].length; y++) {
				pixels[x][y] = c;
			}
		}
	}
	public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
		FontMetrics m = this.getFontMetrics(font);
		int x = (rect.width - m.stringWidth(text)) / 2;
		int y = ((rect.height - m.getHeight()) / 2) + m.getAscent();
		g.setFont(font);
		g.drawString(text, x, y);
	}
	/* will return the edge at the given Side s
	 * (looking at the painted / rotated pixelpanel)
	 */
	private Rectangle[] getREdge(Side s) {
		Rectangle edge1 = 
	}
}