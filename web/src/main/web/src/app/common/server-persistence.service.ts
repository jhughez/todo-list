import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IServer} from "../servers/server.model";
import { Observable } from 'rxjs/Observable';

const SERVERS_URL: string = 'servers';

@Injectable()
export class ServerPersistenceService implements OnInit {

  private servers$: Observable<IServer[]>

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.refreshServers();
  }

  refreshServers() {
    this.servers$ = this.http.get<IServer[]>(SERVERS_URL);
  }

  getAllServers(): Observable<IServer[]> {
    if(!this.servers$){
      this.refreshServers();
    }
    return this.servers$;
  }

  addOrUpdateServer(server: IServer): Observable<any> {
    return this.http.post(SERVERS_URL + "/update", server);
  }

  addServer(server: IServer): Observable<any> {
    return this.http.post(SERVERS_URL + "/new", server);;
  }

  deleteServer (id: number): Observable<{}> {
    return this.http.delete(`${SERVERS_URL}/${id}`);
  }

  countServers (): Observable<any> {
    return this.http.get(SERVERS_URL + "/count");
  }
}
