package com.lapots.game.journey.ui.dsl.tree

import com.kotcrab.vis.ui.widget.VisTree
import com.lapots.game.journey.ui.dsl.api.traits.CompositeTrait
import com.lapots.game.journey.ui.helper.UiHelper
import com.lapots.game.journey.util.DslUtils

import java.awt.Composite;;
/**
 * Represents game component tree.
 * Basically
 *
 * <pre>
 *      tree {
 *          node (name: 'SimpleNode') {
 *              onClick {
 *                  "NodeItem click"
 *              }
 *          }
 *      }
 * </pre>
 *
 * Also it supports inner nodes.
 *
 * <pre>
 *     tree {
 *         node {
 *             node {
 *
 *             }
 *         }
 *     }
 * </pre>
 */
class TreeDSL implements CompositeTrait {

    @Lazy VisTree visTree = new VisTree()

    //==============================DSL specifics============
    def call(closure) {
        id = "tree-" + uuid()
        DslUtils.delegate(closure, this)

        UiHelper.componentRegistry[(id)] = this
    }

    def node(map, closure) {
        TreeNodeDSL childNode = new TreeNodeDSL(map)
        childNode.parentUid = this.id
        DslUtils.delegate(closure, childNode)

        visTree.add(childNode.getInnerComponent())

        UiHelper[(childNode.id)] = childNode
    }
    //================================END=====================

    @Override
    def enumerateChildren() {
        ids.each {}
    }

    @Override
    def appendChild(Object child) { visTree.add(child.getInnerComponent()) }

    @Override
    def getInnerComponent() { return visTree }

    @Override
    def getRawComponent() { return visTree }
}
