import './rxjs-extensions';

import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';

import { AppComponent }             from './app.component';
import { PlayerCardComponent }      from './components/player-card.component';
import { PlayerRecordComponent }    from './components/player-record.component';
import { HolderjsDirective }        from 'angular-2-holderjs/holderjs.directive';


@NgModule({
    imports: [
        BrowserModule,
        FormsModule
    ],
    declarations: [
        AppComponent,
        PlayerCardComponent,
        PlayerRecordComponent,
        HolderjsDirective
    ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }


