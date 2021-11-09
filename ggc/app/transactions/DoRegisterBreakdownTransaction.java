package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnavailableProductException;
import ggc.app.exception.UnknownProductKeyException;
import ggc.core.WarehouseManager;
//FIXME import classes
import ggc.core.exception.InvalidProductIdException;
import ggc.core.exception.ProductAmountException;

/**
 * Register order.
 */
public class DoRegisterBreakdownTransaction extends Command<WarehouseManager> {

  public DoRegisterBreakdownTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_BREAKDOWN_TRANSACTION, receiver);
    addStringField("partnerId", Message.requestPartnerKey());
    addStringField("productId", Message.requestProductKey());
    addIntegerField("quantity", Message.requestAmount());
  }

  @Override
  public final void execute() throws CommandException {
    try {
      _receiver.registerBreakSownSale(stringField("partnerId"),stringField("productId"),integerField("quantity"));
    } catch (ProductAmountException e) {
      throw new UnavailableProductException(e.getProductId(), integerField("quantity"), e.getQuantity());
    } catch (InvalidProductIdException e) {
      throw new UnknownProductKeyException(e.getInvalidId());
    }
  }

}
