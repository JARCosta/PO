package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
//FIXME import classes

/**
 * Open existing saved state.
 */
class DoOpenFile extends Command<WarehouseManager> {

  /** @param receiver */
  DoOpenFile(WarehouseManager receiver) {
    super(Label.OPEN, receiver);
    //FIXME maybe add command fields
    addStringField("name", Message.newSaveAs());
  }

  @Override
  public final void execute() throws CommandException {
    //FIXME implement command
    try {
      _receiver.load(stringField("name"));      
    } catch (Exception e) {
      //TODO: handle exception
    }
  }

}
