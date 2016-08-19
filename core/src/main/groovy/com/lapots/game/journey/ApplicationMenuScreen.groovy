package com.lapots.game.journey

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.lapots.game.journey.ui.UiControl
import com.lapots.game.journey.ui.dsl.MenuBarDSL
import com.lapots.game.journey.util.EvaluationUtils;;

class ApplicationMenuScreen extends ScreenAdapter {

    private static final String MENU_COMPONENT = "app_menu"

    private Stage stage

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport())
        Table root = new Table()
        root.setFillParent(true)
        stage.addActor(root)

        UiControl.default_stage = stage
        UiControl.ui_root = root
        Gdx.input.setInputProcessor(stage)

        // move to some configurable place
        def code = UiControl.components[MENU_COMPONENT]
        EvaluationUtils.evaluateWithBinding(code, [ "menuBar" : new MenuBarDSL() ])
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
