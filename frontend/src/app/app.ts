import { Component, signal } from '@angular/core';
import { TransactionTable } from './components/transaction-table/transaction-table';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [TransactionTable],
  template: '<app-transaction-table></app-transaction-table>',
  styleUrls: ['./app.css']
})
export class App {
  protected readonly title = signal('frontend');
}
