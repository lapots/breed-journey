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
import com.kotcrab.vis.ui.building.utilities.CellWidget
import com.lapots.game.journey.platform.CorePlatform
import com.lapots.game.journey.platform.ResourcePlatform;
import com.lapots.game.journey.platform.UiPlatform;
import com.lapots.game.journey.platform.resource.ResourceRouter;
import com.lapots.game.journey.ui.dsl.MenuBarDSL
import com.lapots.game.journey.ui.dsl.WindowDSL
import com.lapots.game.journey.util.EvaluationUtils
import com.lapots.game.journey.util.GrlUtils;

class ApplicationMenuScreen extends ScreenAdapter {

    private static final String MENU_COMPONENT = "app_menu"

    Stage stage = new Stage(new ScreenViewport());

    { Stage.metaClass.add = { component -> addActor(component) } }
    { Actor.metaClass.parentUid = "" }

    @Override
    public void show() {
        UiPlatform.default_stage = stage
        Gdx.input.setInputProcessor(stage)

        def result = ResourcePlatform <<
            GrlUtils.createGetRequest("ui://$MENU_COMPONENT", null)

        EvaluationUtils.evaluateWithBinding(result, [ "menuBar" : new MenuBarDSL() ])
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
