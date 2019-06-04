import {Routes} from '@angular/router';
import {Error404Component} from './errors/404.component';
import {ServerListComponent} from "./servers/server-list.component";
import {NewServerComponent} from "./servers/new-server/new-server.component";
import {HelpComponent} from "./help-component";
import {ServerCountComponent} from "./servers/server-count.component";

export const appRoutes: Routes = [
  { path: 'servers', component: ServerListComponent},
  { path: 'servers/new', component: NewServerComponent},
  { path: 'servers/count', component: ServerCountComponent},
  { path: 'help', component: HelpComponent},
  { path: '404', component: Error404Component},
  { path: '', redirectTo: 'servers', pathMatch: 'full'},

];
