import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {ServerPersistenceService} from "../../common/server-persistence.service";
import {IServer} from "../server.model";
import {ToastrService} from "../../common/toastr.service";

@Component({
  templateUrl: './new-server.component.html',
  styles: [`
    em { float: right; color: #E05C65; padding-left: 10px}
    .error input {background-color: #E3C3C5;}
    .error ::-webkit-input-placeholder { color: #999}
    .error ::-moz-placeholder { color: #999}
    .error :-moz-placeholder { color: #999}
    .error :-ms-input-placeholder { color: #999}
  `]
})

export class NewServerComponent {
  newServer: IServer;
  isDirty: boolean = true;
  constructor(private router: Router, private serverPersistenceService: ServerPersistenceService
              , private toastr: ToastrService) {

  }

  saveServer(formValues) {
    this.isDirty = false;
    this.serverPersistenceService.addServer(formValues).subscribe(
      result =>{
          this.toastr.success('Server added successfully.');
          this.router.navigate(['/servers']);
      }
    );
  }
  cancel() {
    this.router.navigate(['/servers']);
  }
}
