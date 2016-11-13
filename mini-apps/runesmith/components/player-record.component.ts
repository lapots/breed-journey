import { Component, Input } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'player-record',
    templateUrl: 'player-record.component.html'
})
export class PlayerRecordComponent {
    @Input() label: string;
    @Input() value: string
}
