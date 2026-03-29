import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { Transaction, TransactionRequest } from '../models/transaction'

@Injectable({
  providedIn: 'root'
})

export class TransactionService {

  private apiUrl = 'http://localhost:8080/api/transactions'

  constructor(private http: HttpClient) {}

  getTransactions(): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(this.apiUrl)
  }

  addTransaction(transaction: TransactionRequest): Observable<string> {
    return this.http.post(this.apiUrl, transaction, { responseType: 'text' })
  }
}
