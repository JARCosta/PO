package ggc.app.partners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import ggc.core.Partner;
import ggc.core.WarehouseManager;
//FIXME import classes: imperted Partner
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all partners.
 */
class DoShowAllPartners extends Command<WarehouseManager> {

  DoShowAllPartners(WarehouseManager receiver) {
    super(Label.SHOW_ALL_PARTNERS, receiver);
  }

  @Override
  public void execute() throws CommandException {
    
    for(Partner partner : _receiver.getPartnerSortedList()){
      _display.addLine(partner.toString());
    }
    _display.display();
  }
}
