import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TestControlComponent } from './test-control/test-control.component';
import { SubControlComponent } from './sub-control/sub-control.component';

@NgModule({
  declarations: [
    AppComponent,
    TestControlComponent,
    SubControlComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
