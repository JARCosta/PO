package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.DuplicatePartnerKeyException;
import ggc.core.WarehouseManager;
//FIXME import classes
import ggc.core.exception.BadEntryException;

/**
 * Register new partner.
 */
class DoRegisterPartner extends Command<WarehouseManager> {

  DoRegisterPartner(WarehouseManager receiver) {
    super(Label.REGISTER_PARTNER, receiver);

    addStringField("key", Message.requestPartnerKey());
    addStringField("name", Message.requestPartnerName()); 
    addStringField("adress", Message.requestPartnerAddress()); 
  }

  @Override
  public void execute() throws CommandException {
    try{
      _receiver.registerPartner(stringField("name"), stringField("adress"), stringField("key"));
    } catch(BadEntryException PartnerAlreadyexists){
      throw new DuplicatePartnerKeyException(stringField("key"));
    }

  }

}
