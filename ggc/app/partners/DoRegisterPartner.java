package ggc.app.partners;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
//FIXME import classes
import ggc.core.exception.BadEntryException;

/**
 * Register new partner.
 */
class DoRegisterPartner extends Command<WarehouseManager> {

  DoRegisterPartner(WarehouseManager receiver) {
    super(Label.REGISTER_PARTNER, receiver);

    addStringField("name", Message.requestPartnerName()); 
    addStringField("adress", Message.requestPartnerAddress()); 
    addStringField("key", Message.requestPartnerKey());
  }

  @Override
  public void execute() throws CommandException {
 /*   try{
      _receiver.registerPartner(stringField("name"),stringField("adress"), stringField("key"));
    } catch (BadEntryException bee){
      
    }*/

  }

}
