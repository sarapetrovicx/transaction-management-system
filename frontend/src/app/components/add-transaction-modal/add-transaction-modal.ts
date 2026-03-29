import { Component, ViewChild, TemplateRef } from '@angular/core'
import { FormsModule } from '@angular/forms'
import { NgbModal } from '@ng-bootstrap/ng-bootstrap'
import { TransactionService } from '../../services/transaction'

@Component({
  selector: 'app-add-transaction-modal',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './add-transaction-modal.html'
})
export class AddTransactionModal {

  @ViewChild('modalContent') modalContent!: TemplateRef<any>

  transaction = {
    accountNumber: '',
    accountHolderName: '',
    amount: 1,
    status: 'Pending'
  }

  constructor(private transactionService: TransactionService, private modalService: NgbModal) {}

  open() {
    this.modalService.open(this.modalContent, { centered: true })
  }

  submit() {
    this.transactionService.addTransaction(this.transaction)
      .subscribe({
        next: () => {
          this.modalService.dismissAll()
          window.location.reload()
        },
        error: (err) => {
          console.error('Error adding transaction:', err)
        }
      })
  }
}
