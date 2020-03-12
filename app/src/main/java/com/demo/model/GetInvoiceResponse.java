package com.demo.model;

import java.util.List;

public class GetInvoiceResponse {


    /**
     * status : ok
     * RespCode : 7019
     * success : true
     * Message : Invoice fetched successfully.
     * invoices : [{"invoice_detail":{"id":1838,"invoice_number":"WPINV-01838","invoice_amount":"600","pending_amount":"400","created_date":"2020-02-26","invoice_description":"Test Description","invoice_detail":"http://182.74.186.138/marklaw/wp-content/uploads/2020/02/sample-1.pdf","paid_amount":[{"amount":"100","pay_date":"2020-03-11","invoice_link":"http://182.74.186.138/marklaw/invoices/invoice_223621520.pdf"},{"amount":"100","pay_date":"2020-03-11","invoice_link":"http://182.74.186.138/marklaw/invoices/invoice_1936773107.pdf"}],"currency":"MYR"}}]
     */

    private String status;
    private String RespCode;
    private String success;
    private String Message;
    private List<InvoicesBean> invoices;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRespCode() {
        return RespCode;
    }

    public void setRespCode(String RespCode) {
        this.RespCode = RespCode;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public List<InvoicesBean> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<InvoicesBean> invoices) {
        this.invoices = invoices;
    }

    public static class InvoicesBean {
        /**
         * invoice_detail : {"id":1838,"invoice_number":"WPINV-01838","invoice_amount":"600","pending_amount":"400","created_date":"2020-02-26","invoice_description":"Test Description","invoice_detail":"http://182.74.186.138/marklaw/wp-content/uploads/2020/02/sample-1.pdf","paid_amount":[{"amount":"100","pay_date":"2020-03-11","invoice_link":"http://182.74.186.138/marklaw/invoices/invoice_223621520.pdf"},{"amount":"100","pay_date":"2020-03-11","invoice_link":"http://182.74.186.138/marklaw/invoices/invoice_1936773107.pdf"}],"currency":"MYR"}
         */

        private InvoiceDetailBean invoice_detail;

        public InvoiceDetailBean getInvoice_detail() {
            return invoice_detail;
        }

        public void setInvoice_detail(InvoiceDetailBean invoice_detail) {
            this.invoice_detail = invoice_detail;
        }

        public static class InvoiceDetailBean {
            /**
             * id : 1838
             * invoice_number : WPINV-01838
             * invoice_amount : 600
             * pending_amount : 400
             * created_date : 2020-02-26
             * invoice_description : Test Description
             * invoice_detail : http://182.74.186.138/marklaw/wp-content/uploads/2020/02/sample-1.pdf
             * paid_amount : [{"amount":"100","pay_date":"2020-03-11","invoice_link":"http://182.74.186.138/marklaw/invoices/invoice_223621520.pdf"},{"amount":"100","pay_date":"2020-03-11","invoice_link":"http://182.74.186.138/marklaw/invoices/invoice_1936773107.pdf"}]
             * currency : MYR
             */

            private int id;
            private String invoice_number;
            private String invoice_amount;
            private String pending_amount;
            private String created_date;
            private String invoice_description;
            private String invoice_detail;
            private String currency;
            private List<PaidAmountBean> paid_amount;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getInvoice_number() {
                return invoice_number;
            }

            public void setInvoice_number(String invoice_number) {
                this.invoice_number = invoice_number;
            }

            public String getInvoice_amount() {
                return invoice_amount;
            }

            public void setInvoice_amount(String invoice_amount) {
                this.invoice_amount = invoice_amount;
            }

            public String getPending_amount() {
                return pending_amount;
            }

            public void setPending_amount(String pending_amount) {
                this.pending_amount = pending_amount;
            }

            public String getCreated_date() {
                return created_date;
            }

            public void setCreated_date(String created_date) {
                this.created_date = created_date;
            }

            public String getInvoice_description() {
                return invoice_description;
            }

            public void setInvoice_description(String invoice_description) {
                this.invoice_description = invoice_description;
            }

            public String getInvoice_detail() {
                return invoice_detail;
            }

            public void setInvoice_detail(String invoice_detail) {
                this.invoice_detail = invoice_detail;
            }

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }

            public List<PaidAmountBean> getPaid_amount() {
                return paid_amount;
            }

            public void setPaid_amount(List<PaidAmountBean> paid_amount) {
                this.paid_amount = paid_amount;
            }

            public static class PaidAmountBean {
                /**
                 * amount : 100
                 * pay_date : 2020-03-11
                 * invoice_link : http://182.74.186.138/marklaw/invoices/invoice_223621520.pdf
                 */

                private String amount;
                private String pay_date;
                private String invoice_link;

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public String getPay_date() {
                    return pay_date;
                }

                public void setPay_date(String pay_date) {
                    this.pay_date = pay_date;
                }

                public String getInvoice_link() {
                    return invoice_link;
                }

                public void setInvoice_link(String invoice_link) {
                    this.invoice_link = invoice_link;
                }
            }
        }
    }
}
