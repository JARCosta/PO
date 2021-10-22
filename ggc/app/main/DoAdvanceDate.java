package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.InvalidDateException;
import ggc.core.WarehouseManager;
//FIXME import classes
import ggc.core.exception.BadEntryException;

/**
 * Advance current date.
 */
class DoAdvanceDate extends Command<WarehouseManager> {

  DoAdvanceDate(WarehouseManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    //FIXME add command fields
    addIntegerField("days", Message.requestDaysToAdvance());
  }

  @Override
  public final void execute() throws CommandException {
    //FIXME implement command
    int days = integerField("days");
    try{
      _receiver.advanceDate(days);
    } catch(BadEntryException ide) {
      throw new InvalidDateException(days);
    }
  }

}
