package ggc.core;

public class SaleByCredit extends Sale{
    private Date _deadine;
    private double _amountPaid;

    public SaleByCredit(Partner partner, Product product, int quantity, int deadline){
        super(product, quantity, partner);
        _deadine = new Date(deadline);
    }

    public double getAmountToPay(){
        return super.getBaseValue() - _amountPaid; // should be total amopunt to pay - _amount paied;
    }

    public String toString(){
        String ret = "";
        if(super.getPaymentDate() != null){ ret = "|" + super.getPaymentDate(); }
        return "VENDA|" + super.getId() + "|" + super.getpartner().getId() + "|" + super.getProduct().getId() + "|" + super.getQuantity() + "|" + super.getBaseValue() + "|" + getAmountToPay() + "|" + _deadine + ret;
    }
}
