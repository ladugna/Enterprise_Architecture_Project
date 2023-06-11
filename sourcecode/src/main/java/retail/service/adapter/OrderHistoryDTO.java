package retail.service.adapter;

import retail.constant.OrderHistoryState;
import retail.domain.Address;
import retail.domain.AddressEmbed;
import retail.domain.Customer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class OrderHistoryDTO {
    private double TotalPrice;
    private OrderHistoryState Status = OrderHistoryState.PLACED;
    private LocalDateTime DateOrdered = LocalDateTime.now();
    private String PaymentMethods;
    private double Subtotal;
    private double ShippingAndHandling;
    private double TaxAmount;
    private AddressEmbed ShippingAddress;
    private AddressEmbed BillingAddress;
    private Customer customer;
    private Collection<OrderHistoryDetailsDTO> orderHistoryDetails = new ArrayList<>();

    public AddressEmbed getShippingAddress() {
        return ShippingAddress;
    }

    public void setShippingAddress(AddressEmbed shippingAddress) {
        ShippingAddress = shippingAddress;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public OrderHistoryState getStatus() {
        return Status;
    }

    public void setStatus(OrderHistoryState status) {
        Status = status;
    }

    public LocalDateTime getDateOrdered() {
        return DateOrdered;
    }

    public void setDateOrdered(LocalDateTime dateOrdered) {
        DateOrdered = dateOrdered;
    }

    public String getPaymentMethods() {
        return PaymentMethods;
    }

    public void setPaymentMethods(String paymentMethods) {
        PaymentMethods = paymentMethods;
    }

    public double getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(double subtotal) {
        Subtotal = subtotal;
    }

    public double getShippingAndHandling() {
        return ShippingAndHandling;
    }

    public void setShippingAndHandling(double shippingAndHandling) {
        ShippingAndHandling = shippingAndHandling;
    }

    public double getTaxAmount() {
        return TaxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        TaxAmount = taxAmount;
    }

    public Collection<OrderHistoryDetailsDTO> getOrderHistoryDetails() {
        return orderHistoryDetails;
    }

    public void setOrderHistoryDetails(Collection<OrderHistoryDetailsDTO> orderHistoryDetails) {
        this.orderHistoryDetails = orderHistoryDetails;
    }

    public AddressEmbed getBillingAddress() {
        return BillingAddress;
    }

    public void setBillingAddress(AddressEmbed billingAddress) {
        BillingAddress = billingAddress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "OrderHistoryDTO{" +
                "TotalPrice=" + TotalPrice +
                ", Status=" + Status +
                ", DateOrdered=" + DateOrdered +
                ", PaymentMethods='" + PaymentMethods + '\'' +
                ", Subtotal=" + Subtotal +
                ", ShippingAndHandling=" + ShippingAndHandling +
                ", TaxAmount=" + TaxAmount +
                ", ShippingAddress=" + ShippingAddress +
                ", BillingAddress=" + BillingAddress +
                ", orderHistoryDetails=" + orderHistoryDetails +
                '}';
    }
}
