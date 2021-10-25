package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.FileNotFoundException;
import java.io.IOException;

import ggc.core.WarehouseManager;
//FIXME import classes
import ggc.core.exception.MissingFileAssociationException;

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<WarehouseManager> {

  /** @param receiver */
  DoSaveFile(WarehouseManager receiver) {
    super(Label.SAVE, receiver);
    addStringField("name", Message.newSaveAs());
  }

  @Override
  public final void execute() throws CommandException {
    //FIXME implement command and create a local Form
    try {
      _receiver.saveAs(stringField("name"));
      
    } catch (Exception e) {
      //TODO: handle exception
    }
  }

}
