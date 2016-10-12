package com.lapots.game.journey

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Table
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

        // some infinite loop happens if move to UiHelper class
        Table root = new Table()
        root.setFillParent(true)
        // global UI stage
        UiHelper.mainStage = stage
        Gdx.input.setInputProcessor(stage)
        UiHelper.root = root

        EvaluationUtils.evaluateWithBinding(UiHelper["ui:$MENU_COMPONENT"], [(MENU_ENTRY): new MenuBarDSL()])

        UiHelper.mainStage.addActor(root)
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
