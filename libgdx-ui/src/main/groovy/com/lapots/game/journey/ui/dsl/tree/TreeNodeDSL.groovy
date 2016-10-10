package com.lapots.game.journey.ui.dsl.tree

import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node
import com.lapots.game.journey.ui.dsl.basic.TextLabelDSL
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.DslUtils

/**
 * Represent tree node. {@link TreeDSL}.
 */
class TreeNodeDSL {
    private static final def LABEL = UiHelper["dsl.config.label_key"]

    @Lazy Node node = new Node(TextLabelDSL.createLabel(label))
    def label

    def node(map, closure) {
        TreeNodeDSL child_node = new TreeNodeDSL(map)
        DslUtils.delegate(closure, child_node)

        node.add(~child_node)
    }

    def component_reference() { null }

    def bitwiseNegate() { node }
}
