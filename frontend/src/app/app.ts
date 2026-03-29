import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TransactionTableComponent } from './components/transaction-table/transaction-table';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, TransactionTableComponent],
  template: '<app-transaction-table></app-transaction-table>',
  styleUrls: ['./app.css']
})
export class App {
  protected readonly title = signal('frontend');
}
