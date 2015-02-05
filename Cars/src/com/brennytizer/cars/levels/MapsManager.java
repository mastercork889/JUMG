package com.brennytizer.cars.levels;

import java.awt.Graphics2D;

import com.brennytizer.cars.Cars;
import com.brennytizer.cars.levels.tiles.TileManager;
import com.brennytizer.jumg.gui.GuiScreen;
import com.brennytizer.jumg.level.Map;
import com.brennytizer.jumg.utils.Logging;
import com.brennytizer.jumg.utils.Logging.LoggingSpice;
import com.brennytizer.jumg.utils.Words;

public class MapsManager extends GuiScreen {
	public Map map;
	public float xOffset;
	public float yOffset;
	public float scale;
	
	public MapsManager(String mapName) {
		if(!TileManager.initialised) {
			Logging.log(LoggingSpice.EXTRA_HOT, "HoLy cArP! There's gonna be a bad problem! The tiles aren't initialised!");
		}
		try {
			mapName = "/com/brennytizer/cars/levels/maps/" + mapName;
			this.map = new Map(Words.swapChars(mapName + ".png", " ", "_"), Words.swapChars(mapName + ".set", " ", "_"), 50, 50);
			this.xOffset = 0;
			this.yOffset = 0;
			this.scale = 1;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void adjustOffsets(float xOffset, float yOffset) {
		this.xOffset += xOffset;
		this.yOffset += yOffset;
	}
	
	public void setOffsets(float xOffset, float yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void render(Graphics2D g2d, int boardWidth, int boardHeight) {
		map.render(g2d, xOffset, yOffset, boardWidth, boardHeight, scale);
	}

	public void draw(Graphics2D g2d) {
		render(g2d, Cars.WIDTH, Cars.HEIGHT);
	}
}