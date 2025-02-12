#include <iostream>
using namespace std;

class Account {
    int accountNumber;
    double balance;
    string ownerName;

public:
    Account(const int accountNumber, const double balance, string ownerName) {
        this->accountNumber = accountNumber;
        this->balance = balance;
        this->ownerName = move(ownerName);
    }

    ~Account() = default;
    Account(const Account &other) = delete;
    Account &operator=(const Account &other) = delete;

    void deposit(const double amount) { this->balance += amount; }

    void withdraw(const double amount) { this->balance -= amount; }

    double getBalance() const { return this->balance; }

    string getOwnerName() const { return this->ownerName; }

    int getAccountNumber() const { return this->accountNumber; }
};

class SavingsAccount : public Account {
    double interestRate;

public:
    SavingsAccount(const int accountNumber, const double balance, const string &ownerName, const double interestRate) :
    Account(accountNumber, balance, ownerName), interestRate(interestRate) {}

    double getInterestRate() const { return interestRate; }

    void calculateInterest() {
        this->deposit(interestRate * getBalance());
    }
};

int main() {
    SavingsAccount savings(123456, 1000.0, "John Doe", 2.5);
    savings.deposit(500.0);
    savings.withdraw(200.0);
    savings.calculateInterest();

    cout << "Account Number: " << savings.getAccountNumber() << endl;
    cout << "Owner's Name: " << savings.getOwnerName() << endl;
    cout << "Current Balance: " << savings.getBalance() << endl;
    cout << "Interest Rate: " << savings.getInterestRate() << "%" << endl;

    return 0;
}