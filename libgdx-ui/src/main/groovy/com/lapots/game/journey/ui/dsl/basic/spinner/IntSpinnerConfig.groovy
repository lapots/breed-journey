package com.lapots.game.journey.ui.dsl.basic.spinner

import com.kotcrab.vis.ui.widget.spinner.IntSpinnerModel

/**
 * Stores spinner configuration for int type spinner.
 */
class IntSpinnerConfig {

    def initial, lowerBound, upperBound

    def initial(value) { this.initial = value }

    def lowerBound(value) { this.lowerBound = value }

    def upperBound(value) { this.upperBound = value }

    def build() { new IntSpinnerModel(initial, lowerBound, upperBound) }
}
