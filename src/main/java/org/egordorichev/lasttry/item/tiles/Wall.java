package org.egordorichev.lasttry.item.tiles;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.world.tile.TileData;
import org.newdawn.slick.Image;

public class Wall extends Item {
	protected Image tiles;

	public Wall(int id, String name, Image texture, Image tiles) {
		super(id, name, texture);

		this.tiles = tiles;
	}

	public void renderWall(TileData data, int x, int y) {
		boolean t = LastTry.world.getWallId(x, y - 1) == this.id;
		boolean r = LastTry.world.getWallId(x + 1, y) == this.id;
		boolean b = LastTry.world.getWallId(x, y + 1) == this.id;
		boolean l = LastTry.world.getWallId(x - 1, y) == this.id;

		this.tiles.getSubImage(Block.calculateBinary(t, r, b, l) * Block.TEX_SIZE, data.variant * Block.TEX_SIZE, Block.TEX_SIZE, Block.TEX_SIZE).draw(x * Block.TEX_SIZE, y * Block.TEX_SIZE);
	}

	@Override
	public int getMaxInStack() {
		return 999;
	}

	public static Wall getForBlockID(int id) {
		switch(id) {
			case ItemID.none: default: return null;
			case ItemID.dirtBlock: return (Wall) Item.dirtWall;
		}
	}
}
