package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.app.exception.UnknownProductKeyException;
import ggc.core.WarehouseManager;
import ggc.core.exception.InvalidPartnerIdException;
import ggc.core.exception.InvalidProductIdException;
import ggc.core.partners.Partner;
import ggc.core.products.Product;

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
    Partner partner;
    Product product;
    try {
      partner = _receiver.getPartner(stringField("partnerId"));
      product = _receiver.getProduct(stringField("productId"));
      ((Partner) partner).toggleNotifications((Product) product);
    } catch (InvalidPartnerIdException e) {
      throw new UnknownPartnerKeyException(e.getInvalidId());
    } catch (InvalidProductIdException e){
      throw new UnknownProductKeyException(e.getInvalidId());
    }
  }

}
