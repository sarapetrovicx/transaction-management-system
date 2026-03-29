import { Component, OnInit } from '@angular/core'
import { CommonModule } from '@angular/common'
import { TransactionService } from '../../services/transaction'
import { Transaction } from '../../models/transaction'
import { AddTransactionModalComponent } from '../add-transaction-modal/add-transaction-modal'

@Component({
  selector: 'app-transaction-table',
  standalone: true,
  imports: [CommonModule, AddTransactionModalComponent],
  templateUrl: './transaction-table.html'
})
export class TransactionTableComponent implements OnInit {

  transactions: Transaction[] = []

  constructor(private transactionService: TransactionService) {}

  ngOnInit(): void {
    this.loadTransactions()
  }

  loadTransactions() {
    this.transactionService.getTransactions()
      .subscribe(data => this.transactions = data)
  }
}