import {ErrorHandler, Inject, Injectable, Injector} from '@angular/core';
import {ToastrService} from "./toastr.service";

@Injectable()
export class AppErrorHandler implements ErrorHandler {
    constructor(@Inject(Injector) private injector: Injector) {
    }

    private get toastrService(): ToastrService {
         return this.injector.get(ToastrService);
    }

    public handleError(error: any) {
        if (error.error instanceof ErrorEvent) {
            this.toastrService.error('An error occurred:', error.error.message);
        } else if (error.status === 409) {
            this.toastrService.error('The Server ID you entered already exists.  Please use another id and try again.');
        } else {
            this.toastrService.error(`Backend returned code ${error.status}, ` + `body was: ${error.error}`);
        }
        return error
    }
}
