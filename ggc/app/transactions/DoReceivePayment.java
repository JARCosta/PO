package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownTransactionKeyException;
import ggc.core.WarehouseManager;
//FIXME import classes

/**
 * Receive payment for sale transaction.
 */
public class DoReceivePayment extends Command<WarehouseManager> {

  public DoReceivePayment(WarehouseManager receiver) {
    super(Label.RECEIVE_PAYMENT, receiver);
    addIntegerField("transactionId", Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws CommandException {
    try{
      _receiver.pay(integerField("transactionId"));
    } catch(IndexOutOfBoundsException ioot){
      throw new UnknownTransactionKeyException(integerField("transactionId"));
    }

  }

}
