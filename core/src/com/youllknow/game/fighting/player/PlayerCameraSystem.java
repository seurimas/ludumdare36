package com.youllknow.game.fighting.player;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.youllknow.game.MainGameScreen;
import com.youllknow.game.fighting.WorldDenizen;
import com.youllknow.game.utils.AshleyUtils;
import com.youllknow.game.utils.AshleyUtils.ComponentHandler;
import com.youllknow.game.utils.AshleyUtils.ComponentSubSystem;

public class PlayerCameraSystem extends EntitySystem implements ComponentHandler<WorldDenizen>{
	private final OrthographicCamera camera;
	public PlayerCameraSystem(OrthographicCamera camera) {
		this.camera = camera;
	}
	Family playerFamily = Family.all(PlayerComponent.class, WorldDenizen.class).get();
	private final ComponentSubSystem<WorldDenizen> subSystem = new ComponentSubSystem<WorldDenizen>(playerFamily, WorldDenizen.class, this);
	@Override
	public void update(float deltaTime) {
		subSystem.update(this, deltaTime);
	}

	@Override
	public void update(Engine engine, Entity entity, WorldDenizen denizen, float delta) {
		camera.position.set(denizen.getX() + MainGameScreen.SCREEN_WIDTH * 0.33f, denizen.getY() - denizen.getHeight() + camera.viewportHeight / 2 - MainGameScreen.LOWER_UI_HEIGHT, 0);
		camera.update();
	}
}