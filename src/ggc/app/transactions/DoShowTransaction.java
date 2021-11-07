package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.Transaction;
import ggc.core.WarehouseManager;

/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<WarehouseManager> {

  public DoShowTransaction(WarehouseManager receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
    //FIXME maybe add command fields
  }

  @Override
  public final void execute() throws CommandException {
    for( Transaction trans : _receiver.getTransactionList()){
      _display.popup(trans.toString());
    }
  }
}
