package com.brennytizer.jumg.gui;

import java.awt.Graphics2D;

import com.brennytizer.jumg.gui.event.GuiComponentListener;
import com.brennytizer.jumg.gui.event.GuiComponentObservable;
import com.brennytizer.jumg.gui.event.KeyEvent;
import com.brennytizer.jumg.gui.event.MouseEvent;
import com.brennytizer.jumg.utils.geom.Rectangle2D;

/**
 * This class contains the basic variables needed to create a GuiComponent. The
 * necessary values are a location value and size value. The location and size
 * are necessary for the mouse events that will be called.
 * 
 * @author Jarod Brennfleck
 */
public abstract class GuiComponent implements GuiComponentListener {
	public Rectangle2D boundingBox;
	
	/**
	 * Constructs a new GUI component. The parameters must be of legitimate
	 * value, such that the width and height must be greater than 1. If not, you
	 * will cause an {@link IndexOutOfBoundsException} because the parameters
	 * are illegal.
	 * 
	 * @param x
	 *        - The X position of the component.
	 * @param y
	 *        - The Y position of the component.
	 * @param width
	 *        - The width of the component.
	 * @param height
	 *        - The height of the component.
	 * @param requiresInput
	 *        - Whether this component requires mouse or key interactions.
	 */
	public GuiComponent(int x, int y, int width, int height, boolean requiresInput) {
		this.boundingBox = new Rectangle2D(x, y, width, height);
		String error = "";
		if(width <= 0) error += "width (" + width + ")";
		if(height <= 0) error += ":height (" + height + ")";
		if(!error.equals("")) {
			String[] errorTmp = error.split(":");
			String built = errorTmp.length > 1 ? "Sizes " : "Size ";
			for(int i = 0; i < errorTmp.length; i++) {
				if(i == 0) {
					built += errorTmp[i];
				} else {
					built += "and " + errorTmp[i];
				}
			}
			throw new IndexOutOfBoundsException(built + " are out of range! They must be >= 1!");
		}
		if(requiresInput) GuiComponentObservable.addListener(this);
	}
	
	public Rectangle2D getBoundingBox() {
		return this.boundingBox;
	}
	
	public void onMouseMove(MouseEvent e) {};
	public void onMouseButton(MouseEvent e) {};
	public void onMouseScroll(MouseEvent e) {};
	public void onKeyPress(KeyEvent ke) {};
	public abstract void draw(Graphics2D g2d);
}