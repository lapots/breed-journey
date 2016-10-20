/**

    MASTERY
        Rune Master LV1
            9-cell rune, 6-cell composition, 1 element type
        Rune Master LV2
            25-cell rune, 15-cell composition, 2 element types
        Rune Master LV3
            49-cell rune, 29-cell composition, 4 elements type
        Rune Master LV4
            81-cell rune, 45-cell composition, 5 elements type
        Rune Master LV5
            121-cell rune, 66-cell composition, 7 elements type

*/

class ColoredRune {
    var runeColorMap = []
}

class RuneModel {
    constructor(type, effectMap) {
        this.runeType = type
        this.effects = effectMap
    }
}

class RunesModel {
    constructor() {
        this.hiddenRunes = [ 'Dark', 'Light' ]
        this.basicRunes = [ 'Red', 'Aqua', 'Brown', 'Blue', 'Yellow' ]
    }

    availableRunes(includeHidden) {
        if (includeHidden)
            return hiddenRunes + basicRunes
        else
            return basicRunes
    }
}

class RunesmithModel {

    constructor(size) {
        this.size = size
        this.currentSelection = 0
    }

    calculateAllowedSelection() {
        // basically upper part of the table
        for (var r = 0; r < size; r++)
            for (var c = r; c < size; c++)
                this.allowedSelection += 1
    }

    addRuneElement(row, column, color) {
        this.runeParts[row][column] = color
    }

    calculateRuneEffect() {
        this.runeParts.forEach { }
    }
}

function randomElementFromList(list) {
    return list[Math.floor(Math.random() * list.length)]
}
