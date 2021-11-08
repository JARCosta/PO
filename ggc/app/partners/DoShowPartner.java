package ggc.app.partners;

import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.WarehouseManager;
import ggc.core.exception.InvalidPartnerIdException;
import ggc.core.partners.Partner;
//FIXME import classes
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show partner.
 */
class DoShowPartner extends Command<WarehouseManager> {

  DoShowPartner(WarehouseManager receiver) {
    super(Label.SHOW_PARTNER, receiver);
    addStringField("key", Message.requestPartnerKey());

    //FIXME add command fields
  }

  @Override
  public void execute() throws CommandException {
    
    try{
      Object partner = _receiver.getPartner(stringField("key"));
      _display.popup(partner.toString());
      _display.popup(((Partner) partner).showNotifications());    
    } catch (InvalidPartnerIdException upke){
      throw new UnknownPartnerKeyException(stringField("key"));
    }  
  }
}
