import {Component, OnInit} from '@angular/core';
import {ServerPersistenceService} from "../common/server-persistence.service";

@Component({
  selector: 'server-count',
  template: `  <h1>Number of servers: {{count}}</h1>`
})

export class ServerCountComponent implements OnInit {
  count: number;

  constructor(private serverPersistenceService: ServerPersistenceService) {
  }

  ngOnInit() {
    this.serverPersistenceService.countServers().subscribe(count => {
      this.count = count;
    });
  }
}


