package com.demo.model;

public class PaymentRequest {


    /**
     * user_id : 73
     * invoice_id : 1806
     * payment_status : pending
     * pay_amount : 100
     * pending_amount : 100
     */

    private String user_id;
    private String invoice_id;
    private String payment_status;
    private String pay_amount;
    private String pending_amount;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(String pay_amount) {
        this.pay_amount = pay_amount;
    }

    public String getPending_amount() {
        return pending_amount;
    }

    public void setPending_amount(String pending_amount) {
        this.pending_amount = pending_amount;
    }
}
