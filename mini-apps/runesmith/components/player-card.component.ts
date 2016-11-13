import { Component } from '@angular/core';

import { Player } from './player';

const CONTENT_CARD_META = {
    "rank": "Rank",
    "age": "Age",
    "gender": "Gender",
    "race": "Race"
};

const CONTENT_CARD_META_KEYS = [ "rank", "age", "gender", "race" ];

@Component({
    moduleId: module.id,
    selector: 'player-card',
    templateUrl: 'player-card.component.html'
})
export class PlayerCardComponent {
    player: Player;
}
