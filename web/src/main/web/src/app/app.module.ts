import { BrowserModule } from '@angular/platform-browser';
import {ErrorHandler, NgModule, OnInit} from '@angular/core';
import {RouterModule} from '@angular/router';

import {SampleAppComponent} from './sample-app.component';
import {appRoutes} from './routes';
import {NavbarComponent} from './nav/navbar.component';
import {ServerPersistenceService} from './common/server-persistence.service';
import {Error404Component} from './errors/404.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ServerListComponent} from './servers/server-list.component';
import {
  MatFormFieldModule
  , MatInputModule
  , MatSelectModule
  , MatSortModule
  , MatTableModule
  , MatCardModule
  , MatProgressSpinnerModule
  , MatMenuModule
  , MatIconModule
  , MatToolbarModule
  , MatButtonModule, MatPaginatorModule, MatTooltipModule
} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {NewServerComponent} from './servers/new-server/new-server.component';
import {HelpComponent} from './help-component';
import {ToastrService} from './common/toastr.service';
import {AppErrorHandler} from './common/app-error-handler.service';
import {ServerCountComponent} from "./servers/server-count.component";

@NgModule({
  declarations: [
    SampleAppComponent,
    NavbarComponent,
    Error404Component,
    ServerListComponent,
    NewServerComponent,
    ServerCountComponent,
    HelpComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(appRoutes, {useHash: true}),
    HttpClientModule,
    //AppRoutingModule,
    MatCardModule,
    MatProgressSpinnerModule,
    MatMenuModule,
    MatIconModule,
    MatToolbarModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatSortModule,
    MatTableModule,
    MatPaginatorModule,
    MatTooltipModule
  ],
  providers: [
    ServerPersistenceService,
    ToastrService,
    { provide: ErrorHandler, useClass: AppErrorHandler }
    //{provide: 'canDeactivateCreateEvent', useValue: checkDirtyState},
  ],
  bootstrap: [SampleAppComponent],

})
export class AppModule {}

// export function checkDirtyState(component: CreateEventComponent): boolean {
//   if (component.isDirty) {
//     return window.confirm('You have not saved this event, do you really want to cancel?');
//   }
//   return true;
// }
