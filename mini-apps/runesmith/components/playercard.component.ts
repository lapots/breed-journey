import { Component } from '@angular/core';


export class PlayerCardComponent {
    title = 'Guild card'
    player : PlayerCommonSettings
}

// player common settings
export class PlayerCommonSettings {
    id: string;
    name: string;
    age: number;
    race: string;
    image_path: string
}

var playerCard = `
    <h1>Guild card</h1>
    <h2>Adventurer: level 1</h2>
    <div></div>
`;