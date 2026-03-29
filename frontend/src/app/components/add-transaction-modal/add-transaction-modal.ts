import { Component, EventEmitter, Output } from '@angular/core'
import { CommonModule } from '@angular/common'
import { FormsModule } from '@angular/forms'
import { TransactionService } from '../../services/transaction'

@Component({
  selector: 'app-add-transaction-modal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './add-transaction-modal.html'
})
export class AddTransactionModalComponent {

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
      .subscribe(() => {
        this.transactionAdded.emit()
        this.close()
      })
  }
}