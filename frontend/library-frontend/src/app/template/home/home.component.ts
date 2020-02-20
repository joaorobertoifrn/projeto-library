import { Component, OnInit } from '@angular/core';
import { Dashboard } from '../../../models/dashboard.model';
import { DashboardService } from '../../../services/dashboard.service';

@Component({
  selector: 'cli-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {

  dash: Dashboard;

  constructor(public dashboardService: DashboardService) { }

  ngOnInit() {
    this.dashboardService.findAll()
      .subscribe(response => {
        this.dash = response as Dashboard;
      },
      error => {});
  }

}
