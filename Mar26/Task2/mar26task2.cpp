#include <unordered_map>
#include <string>
#include <iostream>
using namespace std;

class PayPal
{
public:
    void makePayment(std::string item)
    {
        std::cout << "Making PayPal payment for item: " << item << std::endl;
    }

    bool verifyPayment(std::string transactionId)
    {
        std::cout << "Verifying PayPal payment with transaction ID: " << transactionId << std::endl;
        return true;
    }

    void refundPayment(std::string item)
    {
        std::cout << "Refunding PayPal payment for item: " << item << std::endl;
    }
};

class Stripe
{
public:
    void chargePayment(std::string item)
    {
        std::cout << "Charging Stripe payment for item: " << item << std::endl;
    }

    bool verifyCharge(std::string transactionId)
    {
        std::cout << "Verifying Stripe payment with transaction ID: " << transactionId << std::endl;
        return true;
    }

    void issueRefund(std::string item)
    {
        std::cout << "Issuing Refund for Stripe payment for item: " << item << std::endl;
    }
};

class PaymentProvider
{
public:
    virtual void makePayment(std::string item) = 0;
    virtual bool verifyPayment(std::string transactionId) = 0;
    virtual void refundPayment(std::string item) = 0;
};

class PayPalProvider : public PaymentProvider
{
private:
    PayPal paypal;

public:
    void makePayment(std::string item) override
    {
        paypal.makePayment(item);
    }

    bool verifyPayment(std::string transactionId) override
    {
        return paypal.verifyPayment(transactionId);
    }

    void refundPayment(std::string item) override
    {
        paypal.refundPayment(item);
    }
};

class StripeProvider : public PaymentProvider
{
private:
    Stripe stripe;

public:
    void makePayment(std::string item) override
    {
        stripe.chargePayment(item);
    }

    bool verifyPayment(std::string transactionId) override
    {
        return stripe.verifyCharge(transactionId);
    }

    void refundPayment(std::string item) override
    {
        stripe.issueRefund(item);
    }
};

class PaymentGateWay
{
private:
    std::unordered_map<std::string, PaymentProvider *> paymentProviders;

public:
    PaymentGateWay()
    {
        paymentProviders = std::unordered_map<std::string, PaymentProvider *>();
    }

    void addPaymentProvider(std::string providerName, PaymentProvider *paymentProvider)
    {
        paymentProviders[providerName] = paymentProvider;
    }

    void processPayment(std::string providerName, std::string paymentInfo)
    {
        if (paymentProviders.find(providerName) != paymentProviders.end())
        {
            paymentProviders[providerName]->makePayment(paymentInfo);
        }
        else
        {
            std::cout << "Payment provider not found: " << providerName << std::endl;
        }
    }

    void refundPayment(std::string providerName, std::string refundInfo)
    {
        if (paymentProviders.find(providerName) != paymentProviders.end())
        {
            paymentProviders[providerName]->refundPayment(refundInfo);
        }
        else
        {
            std::cout << "Payment provider not found: " << providerName << std::endl;
        }
    }

    void verifyPayment(std::string providerName, std::string transactionId)
    {
        if (paymentProviders.find(providerName) != paymentProviders.end())
        {
            bool result = paymentProviders[providerName]->verifyPayment(transactionId);
            std::cout << "Verification result for " << providerName << ": " << (result ? "Success" : "Failure") << std::endl;
        }
        else
        {
            std::cout << "Payment provider not found: " << providerName << std::endl;
        }
    }
};

int main()
{
    PaymentGateWay gateway;
    PayPalProvider paypalProvider;
    StripeProvider stripeProvider;

    gateway.addPaymentProvider("PayPal", &paypalProvider);
    gateway.addPaymentProvider("Stripe", &stripeProvider);

    gateway.processPayment("PayPal", "Item1");
    gateway.verifyPayment("PayPal", "TX12345");
    gateway.refundPayment("PayPal", "Item1");

    gateway.processPayment("Stripe", "Item2");
    gateway.verifyPayment("Stripe", "TX67890");
    gateway.refundPayment("Stripe", "Item2");

    return 0;
}
