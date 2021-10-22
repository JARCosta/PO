package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
//FIXME import classes

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<WarehouseManager> {

  /** @param receiver */
  DoSaveFile(WarehouseManager receiver) {
    super(Label.SAVE, receiver);
//    addIntegerField("filename", Message.requestDaysToAdvance()); 
  }

  @Override
  public final void execute() throws CommandException {
    //FIXME implement command and create a local Form
//    _receiver.saveAs(stringField("filename"));
  }

}
