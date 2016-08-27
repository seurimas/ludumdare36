package com.youllknow.game.fighting.input;

import java.util.Iterator;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.youllknow.game.MainGameScreen;
import com.youllknow.game.fighting.PlayerComponent;
import com.youllknow.game.fighting.WorldDenizen;
import com.youllknow.game.utils.AshleyUtils;

public class PlayerWalkingSystem extends EntitySystem {
	private static final Family entityFamily = Family.all(PlayerComponent.class, WorldDenizen.class).get();
	private Iterable<WorldDenizen> walkers = AshleyUtils.getComponentIterable(this, entityFamily, WorldDenizen.class);
	
	@Override
	public void update(float deltaTime) {
		Vector2 walkImpulse = new Vector2();
		if (Gdx.input.isKeyPressed(Keys.A))
			walkImpulse.x = -100f;
		else if (Gdx.input.isKeyPressed(Keys.D))
			walkImpulse.x = 100f;
		for (WorldDenizen walker : walkers) {
			walker.applyImpulse(walkImpulse);
		}
	}
}