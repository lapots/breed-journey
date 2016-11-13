import { Component }          from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'my-app',
    template: `
        <div class="col-md-10 offset-md-1">
            <div class="col-md-6">
                <player-card>Loading...</player-card>
            </div>
        </div>
    `
})
export class AppComponent {}



