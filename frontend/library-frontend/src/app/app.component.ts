import { Component, OnInit } from '@angular/core';
import { StorageService } from '../services/storage.service';

@Component({
  selector: 'cli-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

  emailUser: string;

  constructor(public storage: StorageService) { }

  ngOnInit() {
    const localUser = this.storage.getLocalUser();
    if (localUser) {
      this.emailUser = localUser.email;
    } else {
      this.emailUser = '';
    }
  }
}
