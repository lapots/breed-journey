package com.lapots.game.journey

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.ui.dsl.menu.MenuBarDSL
import com.lapots.game.journey.util.EvaluationUtils

class ApplicationMenuScreen extends ScreenAdapter {

    private static final String MENU_COMPONENT = UiHelper["components.menu.name"]
    private static final String MENU_ENTRY = UiHelper["components.menu.entry"]

    Stage stage = new Stage(new ScreenViewport());

    @Override
    public void show() {
        // global UI stage
        UiHelper.default_stage = stage
        Gdx.input.setInputProcessor(stage)

        // get result and layout
        def menubar = UiHelper["ui:$MENU_COMPONENT"]
        EvaluationUtils.evaluateWithBinding(menubar, [ (MENU_ENTRY) : new MenuBarDSL() ])

        UiHelper.default_stage.addActor(UiHelper.root)
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        if (width == 0 && height == 0) return;
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() { stage.dispose(); }
}
