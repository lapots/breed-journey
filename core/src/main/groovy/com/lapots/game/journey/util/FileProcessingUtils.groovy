package com.lapots.game.journey.util

class FileProcessingUtils {

    static getFileName(file) {
        file.name.lastIndexOf('.').with {
            it != -1 ? file.name[0..<it]:
            file.name
        }
    }

    static getFileExt(file) {
        def filename = file.name
        filename[filename.lastIndexOf('.') + 1..filename.length() - 1]
    }

    static createFileName(prefix, postfix, ext) {
        def half_name = ext[1..ext.length() - 1]
        def first_letter = ext[0].toUpperCase()
        prefix + "." + first_letter + half_name + postfix
    }
}
