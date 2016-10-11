package com.lapots.game.journey.ui.dsl.basic

import com.kotcrab.vis.ui.building.OneRowTableBuilder;
import com.kotcrab.vis.ui.widget.VisTextButton
import com.lapots.game.journey.ui.dsl.api.IEventableDSL
import com.lapots.game.journey.ui.dsl.api.IPrimitiveDSL
import com.lapots.game.journey.ui.dsl.api.traits.ComponentValueTrait
import com.lapots.game.journey.ui.dsl.api.traits.ComponentWidthTrait
import com.lapots.game.journey.ui.exception.UiDSLException
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.DslUtils
import com.lapots.game.journey.util.ReflectionUtils

/**
 * DSL for button.
 * Basically
 *
 * <pre>
 *      button($dsl.config.label_key : 'Button') {
 *          onClick {
 *              "ClickEvent"
 *          }
 *      }
 *  </pre>
 */
class ButtonDSL implements IPrimitiveDSL, ComponentValueTrait, IEventableDSL, ComponentWidthTrait {

    private static final String MISSING_LABEL_EXCEPTION = "No label found for button!!!"
    OneRowTableBuilder oneRowTable = new OneRowTableBuilder()
    VisTextButton button

    //=============================DSL specifics==================================
    def call(map, closure) {
        id = uuid()

        def label = map[UiHelper["dsl.config.label_key"]]
        if (!label) throw UiDSLException(MISSING_LABEL_EXCEPTION)

        button = new VisTextButton(label)

        DslUtils.delegate(closure, this)
        oneRowTable.append(roundify(button))
    }

    @Override
    def onClick(closure) {
        def event = closure()
        if (event) {
            def instance = ReflectionUtils.instantiateOne(UiHelper["application.packages.event_packages"], event)
            button.addListener(instance)
        }
    }

    def initial(closure) { setValue(closure()) }
    //===================================END=====================================

    @Override
    Object getId() { return id }

    @Override
    void setId(Object id) { this.id = id }

    @Override
    def getInnerComponent() { oneRowTable.build() }

    @Override
    def getRawComponent() { button }

    @Override
    def getValue() { button.getText() }

    @Override
    def setValue(def value) { button.setText(value) }

    @Override
    Object getParentUid() { return parentUid }

    @Override
    void setParentUid(Object parentUid) { this.parentUid = parentUid }
}
