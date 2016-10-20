import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { RunesmithModule } from './runesmith.module';

const platform = platformBrowserDynamic();
platform.bootstrapModule(RunesmithModule)
