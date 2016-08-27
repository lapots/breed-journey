package com.lapots.game.journey

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.Widget
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.kotcrab.vis.ui.building.GridTableBuilder;
import com.kotcrab.vis.ui.building.TableBuilder
import com.kotcrab.vis.ui.building.utilities.CellWidget;
import com.lapots.game.journey.ui.UiControl
import com.lapots.game.journey.ui.dsl.MenuBarDSL
import com.lapots.game.journey.ui.dsl.WindowDSL
import com.lapots.game.journey.util.EvaluationUtils
import com.lapots.game.journey.world.CoreControl;;;

class ApplicationMenuScreen extends ScreenAdapter {

    private static final String MENU_COMPONENT = "app_menu"
    private static final String BASIC_WINDOW_COMPONENT = "basic_window"
    private static final String PERSON_EDITOR_COMPONENT = "person_editor"

    private Stage stage

    { Stage.metaClass.add = { component -> addActor(component) } }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport())

        UiControl.default_stage = stage
        Gdx.input.setInputProcessor(stage)

        // move to some configurable place
        def code = null

        code = UiControl.components[MENU_COMPONENT]
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
