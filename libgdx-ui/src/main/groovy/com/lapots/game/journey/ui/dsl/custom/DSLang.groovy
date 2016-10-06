package com.lapots.game.journey.ui.dsl.custom

import groovy.json.JsonSlurper

class DSLang {

    static def key_words = [
        "resources" : {
                /**
                 * -- player creating resources
                 * {
                 *      "races" : [
                 *              "ELF",
                 *              "ORC",
                 *              "HUMAN"
                 *      ],
                 *      "genders" : [
                 *              "MALE",
                 *              "FEMALE"
                 *      ]
                 * }
                 */
                new JsonSlurper().parseText('{ "races" : [ "ELF", "ORC", "HUMAN" ], "genders" : [ "MALE", "FEMALE" ] }')
         }
    ]

}
