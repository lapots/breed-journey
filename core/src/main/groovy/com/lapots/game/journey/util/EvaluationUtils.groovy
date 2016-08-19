package com.lapots.game.journey.util

class EvaluationUtils {

    static evaluateWithBinding(code, variables) {
        Binding binding = new Binding()
        variables.each { k, v -> binding.setVariable(k, v) }
        new GroovyShell(binding).evaluate(code)
    }
}
