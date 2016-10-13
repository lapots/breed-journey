package com.lapots.game.journey.ui.dsl.window.wizard

/**
 * Represent window wizard component.
 * Wizard - is a special array of windows that allows
 * to move forward and backward. By default wizard
 * hide other windows. In wizard windows are the frames.
 *
 * The order of frames in DSL is the default wizard order.
 * Might allow indexing.
 * Basically
 *
 * <pre>
 *     wizard(title: 'Configure player') {
 *         frame(index: 1, title: 'Choose defaults') {}
 *         frame(index: 2) {}
 *     }
 * </pre>
 */
class WizardDSL {
}
