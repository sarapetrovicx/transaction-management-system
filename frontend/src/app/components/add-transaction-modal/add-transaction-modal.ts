import { Component, EventEmitter, Output } from '@angular/core'
import { FormsModule } from '@angular/forms'
import { TransactionService } from '../../services/transaction'

@Component({
  selector: 'app-add-transaction-modal',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './add-transaction-modal.html'
})
export class AddTransactionModal {

  @Output()
  transactionAdded = new EventEmitter()

  showModal = false

  transaction = {
    accountNumber: '',
    accountHolderName: '',
    amount: 0,
    status: 'Pending'
  }

  constructor(private transactionService: TransactionService) {}

  open() {
    this.showModal = true
  }

  close() {
    this.showModal = false
  }

  submit() {
    this.transactionService.addTransaction(this.transaction)
      .subscribe({
        next: () => {
          this.close()
          window.location.reload()
        },
        error: (err) => {
          console.error('Error adding transaction:', err)
        }
      })
  }
}
