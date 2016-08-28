package com.youllknow.game.ancient;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.youllknow.game.wiring.Schematic.Wire;
import com.youllknow.game.wiring.nodes.RegisteredEnergyNode;

public class GameStateEnergyInputNode extends RegisteredEnergyNode {
	public static interface GameStateSetter {
		public void handleInput(Wire wire, Energy energy);
		public String getDescription();
	}
	private final Color color;
	private final TextureRegion sprite;
	private final GameStateSetter gameState;
	public GameStateEnergyInputNode(Color color, TextureRegion sprite, GameStateSetter gameState) {
		this.color = color;
		this.sprite = sprite;
		this.gameState = gameState;
	}
	@Override
	public TextureRegion getSprite() {
		return sprite;
	}

	@Override
	public Energy getOutput() {
		return null;
	}

	@Override
	public void handleInput(Wire wire, Energy input1) {
		gameState.handleInput(wire, input1);
	}

	@Override
	public Energy getOutput(Energy input1, Energy input2) {
		return null;
	}
	@Override
	public Color getColor() {
		return color;
	}
	@Override
	public String getTooltip() {
		return String.format("When this node receives energy, the following occurs: %s", 
				gameState.getDescription());
	}

}
