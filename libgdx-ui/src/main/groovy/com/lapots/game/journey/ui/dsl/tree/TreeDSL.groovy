package com.lapots.game.journey.ui.dsl.tree

import com.kotcrab.vis.ui.widget.VisTree
import com.lapots.game.journey.ui.dsl.api.traits.CompositeTrait
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
        DslUtils.delegate(closure, this)
    }

    def node(map, closure) {
        TreeNodeDSL childNode = new TreeNodeDSL(map)
        DslUtils.delegate(closure, child_node)

        visTree.add(childNode.getInnerComponent())
    }
    //================================END=====================

    @Override
    Object getParentUid() { return parentUid }

    @Override
    void setParentUid(Object parentUid) { this.parentUid = parentUid }

    @Override
    Object getId() { return id }

    @Override
    void setId(Object id) { this.id = id }

    @Override
    def enumerateChildren() {
        ids.each {}
    }

    @Override
    def getInnerComponent() { return visTree }

    @Override
    def getRawComponent() { return visTree }
}
