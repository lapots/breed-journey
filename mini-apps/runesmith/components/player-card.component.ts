import { Component } from '@angular/core';

import { Player } from './player';

@Component({
    moduleId: module.id,
    selector: 'player-card',
    templateUrl: 'player-card.component.html'
})
export class PlayerCardComponent {
    cartAttributes = {
        "rank" : "Rank",
        "age" : "Age",
        "gender" : "Gender",
        "race" : "Race"
    };
    cartAttributeKeys = [ "rank", "age", "gender", "race" ];
    player: Player = {
        id: '1',
        rank: 'C',
        age: 10,
        gender: 'male',
        race: 'human',
        name: 'UltimateWarrior15'
    };
}
