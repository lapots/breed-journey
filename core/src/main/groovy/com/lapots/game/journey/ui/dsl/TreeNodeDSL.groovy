package com.lapots.game.journey.ui.dsl

import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node
import com.lapots.game.journey.util.DslUtils;

class TreeNodeDSL implements IReferenced {
    private static final def LABEL = "label"

    @Lazy Node node = new Node(TextLabel.createLabel(label))
    def label

    def node(map, closure) {
        TreeNodeDSL child_node = new TreeNodeDSL(map)
        DslUtils.delegate(closure, child_node)

        node.add(~child_node)
    }

    def component_reference() { null }

    def bitwiseNegate() { node }
}
