import { Component, OnInit } from '@angular/core';
import { trigger, state, style, transition, animate} from '@angular/animations';

import {NotificationService} from '../messages/notification.service';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/timer';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'cli-barranotificacao',
  templateUrl: './barranotificacao.component.html',
  styleUrls: ['./barranotificacao.component.css'],
  animations: [
    trigger('barranotificacao-visibility', [
      state('hidden', style({
        opacity: 0,
        bottom: '0px'
      })),
      state('visible', style({
        opacity: 1,
        bottom: '30px'
      })),
      transition('hidden => visible', animate('800ms 0s ease-in')),
      transition('visible => hidden', animate('800ms 0s ease-out'))
    ])
  ]
})
export class BarranotificacaoComponent implements OnInit {

  message: string;

  barranotificacaoVisibility = 'hidden';

  constructor(private notificationService: NotificationService) { }

  ngOnInit() {
    this.notificationService.notifier
      .do(message => {
        this.message = message;
        this.barranotificacaoVisibility = 'visible';
    }).switchMap(message => Observable.timer(5000))
      .subscribe(timer => this.barranotificacaoVisibility = 'hidden');
  }

}
