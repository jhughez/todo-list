import { Component } from '@angular/core';

@Component({
  selector: 'sample-app',
  template: `
    <nav-bar></nav-bar>
    <router-outlet></router-outlet>
  `
})
export class SampleAppComponent {
  title = 'Sample App';

}
