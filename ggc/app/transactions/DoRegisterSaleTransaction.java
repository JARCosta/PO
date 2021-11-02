package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
//FIXME import classes

/**
 * 
 */
public class DoRegisterSaleTransaction extends Command<WarehouseManager> {

  public DoRegisterSaleTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_SALE_TRANSACTION, receiver);
    //FIXME maybe add command fields 
    addStringField("partnerid", Message.requestPartnerKey());
  }

  @Override
  public final void execute() throws CommandException {
    //FIXME implement command
    _receiver.getPartner(stringField("partnerid")).registerSale(product, quantity, partner);
  }

}
