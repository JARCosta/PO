package ggc.app.partners;

import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.Partner;
import ggc.core.WarehouseManager;
import ggc.core.exception.BadEntryException;
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
      Partner partner = _receiver.getPartner(stringField("key"));
      _display.popup(partner.toString());
    } catch (BadEntryException upke){
      throw new UnknownPartnerKeyException(stringField("key"));
    }  
    //_display.popup(partner.notification.toString);
  }
}
