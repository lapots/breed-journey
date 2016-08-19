package com.lapots.game.journey

import com.badlogic.gdx.Game
import com.kotcrab.vis.ui.VisUI
import com.kotcrab.vis.ui.VisUI.SkinScale;;

class ApplicationMain extends Game {

    @Override
    public void create() {
        VisUI.load(SkinScale.X1)
        setScreen(new ApplicationMenuScreen())
    }

    @Override
    public void dispose() { VisUI.dispose() }
}
