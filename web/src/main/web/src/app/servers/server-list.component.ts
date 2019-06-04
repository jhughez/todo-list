import {Component, AfterViewInit, ViewChild, ChangeDetectorRef} from '@angular/core';
import {IServer} from './server.model';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {ServerPersistenceService} from "../common/server-persistence.service";
import {ToastrService} from "../common/toastr.service";

@Component({
  selector: 'server-list',
  templateUrl: './server-list.component.html'
})

export class ServerListComponent implements AfterViewInit {
  displayedColumns: string[] = ['id', 'name', 'description', 'actionsColumn'];
  dataSource = new MatTableDataSource<IServerEdit>();

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;


  constructor(private serverPersistenceService: ServerPersistenceService
              , private changeDetectorRef: ChangeDetectorRef
              , private toastr: ToastrService) {
   this.refresh();
  }

  refresh() {
    this.serverPersistenceService.getAllServers().subscribe(servers => {
      this.dataSource.data = <IServerEdit[]>servers;
      this.dataSource._updateChangeSubscription();
      this.changeDetectorRef.detectChanges();
    });
  }

  ngAfterViewInit() {

    // If the user changes the sort order, reset back to the first page.
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

    this.dataSource = new MatTableDataSource();
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  update(server: IServerEdit) {
    this.serverPersistenceService.addOrUpdateServer(server)
      .subscribe(result => {
        this.refresh();
        this.toastr.success('Server updated successfully.');
      } );
  }

  editServer(server: IServerEdit){
    server.editable = !server.editable;
  }
  deleteServer(id: number){
    this.serverPersistenceService.deleteServer(id)
      .subscribe(result => {
        this.refresh();
        this.toastr.success('Server deleted successfully.');
      });
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }

}

interface IServerEdit extends IServer{
  editable: boolean;
}

