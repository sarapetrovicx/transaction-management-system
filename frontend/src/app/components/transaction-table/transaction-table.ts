import { Component, OnInit } from '@angular/core'
import { CommonModule, CurrencyPipe } from '@angular/common'
import { TransactionService } from '../../services/transaction'
import { Transaction } from '../../models/transaction'
import { AddTransactionModal } from '../add-transaction-modal/add-transaction-modal'

@Component({
  selector: 'app-transaction-table',
  standalone: true,
  imports: [CommonModule, CurrencyPipe, AddTransactionModal],
  templateUrl: './transaction-table.html'
})
export class TransactionTable implements OnInit {

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
