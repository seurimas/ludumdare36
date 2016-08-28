package com.youllknow.game.wiring.nodes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.youllknow.game.IconManager;
import com.youllknow.game.wiring.Schematic;
import com.youllknow.game.wiring.Schematic.EnergyNode;
import com.youllknow.game.wiring.Schematic.EnergyNode.Energy;
import com.youllknow.game.wiring.Schematic.Wire;

public class LogicEnergyNode extends RegisteredEnergyNode {
	public static interface Logic {
		public Energy getOutput(Energy input1, Energy input2);
		public Color getColor();
		public TextureRegion getSprite();
	}
	public static final Logic sameOrNeither = new Logic() {
		public Energy getOutput(Energy input1, Energy input2) {
			if (input1 == input2)
				return input1;
			else if (input1 == Energy.RED || input2 == Energy.RED) {
				if (input1 == Energy.BLUE || input2 == Energy.BLUE)
					return Energy.GREEN;
				else
					return Energy.BLUE;
			} else if (input1 == Energy.BLUE || input2 == Energy.BLUE) {
				return Energy.RED;
			}
			return input2;
		};
		public Color getColor() {
			return Color.WHITE;
		}
		@Override
		public TextureRegion getSprite() {
			return IconManager.getLogicIcon();
		}
	};
	public static final Logic oddBlue = new Logic() {
		public Energy getOutput(Energy input1, Energy input2) {
			if (input1 == input2)
				return Energy.GREEN;
			else
				return Energy.BLUE;
		};
		public Color getColor() {
			return Color.BLUE;
		}
		@Override
		public TextureRegion getSprite() {
			return IconManager.getLogicIcon();
		}
	};
	public static final Logic oddRed = new Logic() {
		public Energy getOutput(Energy input1, Energy input2) {
			if (input1 == input2)
				return Energy.GREEN;
			else
				return Energy.RED;
		};
		public Color getColor() {
			return Color.RED;
		}
		@Override
		public TextureRegion getSprite() {
			return IconManager.getLogicIcon();
		}
	};
	public static final Logic anyRed = new Logic() {
		public Energy getOutput(Energy input1, Energy input2) {
			if (input1.equals(Energy.RED) || input2.equals(Energy.RED))
				return Energy.RED;
			else
				return Energy.BLUE;
		};
		public Color getColor() {
			return Color.RED;
		}
		@Override
		public TextureRegion getSprite() {
			return IconManager.getLogicIconInverted();
		}
	};
//	public static final Logic green = new Logic() {
//		public Energy getOutput(Energy input1, Energy input2) {
//			return Energy.GREEN;
//		};
//		public Color getColor() {
//			return Color.GREEN;
//		}
//	};
	public static final Logic anyBlue = new Logic() {
		public Energy getOutput(Energy input1, Energy input2) {
			if (input1.equals(Energy.BLUE) || input2.equals(Energy.BLUE))
				return Energy.BLUE;
			else
				return Energy.RED;
		};
		public Color getColor() {
			return Color.BLUE;
		}
		@Override
		public TextureRegion getSprite() {
			return IconManager.getLogicIconInverted();
		}
	};
	private static final Logic[] logics = new Logic[] {
			sameOrNeither,
			oddBlue,
//			oddGreen,
			oddRed,
			anyBlue,
//			green,
			anyRed
	};
	
	private Logic logic;
	public LogicEnergyNode(Logic logic) {
		this.logic = logic;
	}
	@Override
	public Energy getOutput(Energy input1, Energy input2) {
		return logic.getOutput(input1, input2);
	}

	@Override
	public TextureRegion getSprite() {
		return logic.getSprite();
	}

	@Override
	public Energy getOutput() {
		return null;
	}

	@Override
	public void handleInput(Wire wire, Energy input1) {
	}
	@Override
	public Color getColor() {
		return logic.getColor();
	}
	public void toggleLogic() {
		for (int i = 0;i < logics.length - 1;i++) {
			if (logic == logics[i]) {
				logic = logics[i + 1];
				return;
			}
		}
		logic = logics[0];
	}
}
