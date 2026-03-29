export interface Transaction {
  transactionDate: string
  accountNumber: string
  accountHolderName: string
  amount: number
  status: string
}

export interface TransactionRequest {
  accountNumber: string
  accountHolderName: string
  amount: number
  status: string
}