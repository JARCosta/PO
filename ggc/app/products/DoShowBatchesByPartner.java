package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.core.WarehouseManager;
//FIXME import classes
import ggc.core.exception.BadEntryException;

/**
 * Show batches supplied by partner.
 */
class DoShowBatchesByPartner extends Command<WarehouseManager> {

  DoShowBatchesByPartner(WarehouseManager receiver) {
    super(Label.SHOW_BATCHES_SUPPLIED_BY_PARTNER, receiver);
    addStringField("partner", Message.requestPartnerKey());
  }

  @Override
  public final void execute() throws CommandException {
    try{
      for(Object batch : _receiver.getBatchSortedList(_receiver.getPartner(stringField("partner")))){
        _display.addLine(batch.toString());
      }
    } catch(BadEntryException UnknowPartnerId){
      throw new UnknownPartnerKeyException("partner");
    }
    _display.display();  }

}
