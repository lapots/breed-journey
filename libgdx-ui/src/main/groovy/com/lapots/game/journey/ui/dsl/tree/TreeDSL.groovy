package com.lapots.game.journey.ui.dsl.tree

import com.kotcrab.vis.ui.widget.VisTree
import com.lapots.game.journey.util.DslUtils;;
/**
 * Represents game component tree.
 */
class TreeDSL {

    @Lazy VisTree visTree = new VisTree()

    def call(closure) {
        DslUtils.delegate(closure, this)
    }

    def node(map, closure) {
        TreeNodeDSL child_node = new TreeNodeDSL(map)
        DslUtils.delegate(closure, child_node)

        visTree.add(~child_node)
    }

    def component_reference() { null }

    def bitwiseNegate() { visTree }

}
