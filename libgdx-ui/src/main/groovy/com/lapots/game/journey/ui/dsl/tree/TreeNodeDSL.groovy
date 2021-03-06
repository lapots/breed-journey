package com.lapots.game.journey.ui.dsl.tree

import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node
import com.lapots.game.journey.ui.dsl.api.IEventableDSL
import com.lapots.game.journey.ui.dsl.api.traits.ComponentValueTrait
import com.lapots.game.journey.ui.dsl.api.traits.CompositeTrait
import com.lapots.game.journey.ui.dsl.basic.TextLabelDSL
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.DslUtils
import com.lapots.game.journey.util.ReflectionUtils

import java.awt.Composite

/**
 * Represent tree node. {@link TreeDSL}.
 */
class TreeNodeDSL implements CompositeTrait, ComponentValueTrait, IEventableDSL {
    private static final def LABEL = UiHelper["dsl.config.label_key"]

    @Lazy Node node =  new Node(innerLabel)
    def innerLabel

    //=============================DSL specifics================
    def node(map, closure) {
        def label = map[UiHelper["dsl.config.label_key"]]
        innerLabel = TextLabelDSL.TextLabelUtil.createLabel(label)
        processSub(closure)
    }

    def node(closure) {
        innerLabel = TextLabelDSL.TextLabelUtil.createLabel("")
        processSub(closure)
    }

    @Override
    def onClick(Object closure) {
        def event = closure()
        if (event) {
            def instance = ReflectionUtils.instantiateOne(UiHelper["application.packages.event_packages"], event)
            instance.boundId = this.id
            UiHelper.eventRegistry[(this.id)] = instance
            innerLabel.addListener(instance)
        }
    }
    //=================================END=========================
    def processSub(closure) {
        this.id = "node-" + uuid()
        TreeNodeDSL childNode = new TreeNodeDSL()
        childNode.parentUid = this.id
        DslUtils.delegate(closure, childNode)
        node.add(childNode.getInnerComponent())

        UiHelper.componentRegistry[(id)] = this
        UiHelper.componentRegistry[(childNode.id)] = childNode
    }

    @Override
    def enumerateChildren() {
        ids.each {}
    }

    @Override
    def appendChild(Object child) { node.add(child.getInnerComponent()) }

    @Override
    def getValue() {
        // questionable (maybe label ? )
        return node
    }

    @Override
    def setValue(Object value) { this.value = value }

    @Override
    def getInnerComponent() {
        return node
    }

    @Override
    def getRawComponent() { return innerLabel }
}
