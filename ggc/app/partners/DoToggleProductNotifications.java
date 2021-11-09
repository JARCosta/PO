package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
//FIXME import classes

/**
 * Toggle product-related notifications.
 */
class DoToggleProductNotifications extends Command<WarehouseManager> {

  DoToggleProductNotifications(WarehouseManager receiver) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, receiver);

    addStringField("partnerId", Message.requestPartnerKey());
    addStringField("productId", Message.requestProductKey());
  }

  @Override
  public void execute() throws CommandException {
    //getPartner(stringField("partnerId")).toggleNotifications(getProduct(stringField("productId")));
    //FIXME implement command
  }

}
