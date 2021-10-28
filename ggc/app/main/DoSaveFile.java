package ggc.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.MissingFormatArgumentException;

import javax.imageio.IIOException;

import ggc.app.exception.FileOpenFailedException;
import ggc.core.WarehouseManager;
//FIXME import classes
import ggc.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
/**
 * Save current state to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<WarehouseManager> {

  /** @param receiver */
  DoSaveFile(WarehouseManager receiver) {
    super(Label.SAVE, receiver);    
  }

  @Override
  public final void execute() throws CommandException {
    try{
      _receiver.save();
    } catch (MissingFileAssociationException | IOException noFile){
      String name = Form.requestString(Message.newSaveAs());
      try {
        _receiver.saveAs(name);
      } catch (MissingFileAssociationException | IOException fileError) {
        throw new FileOpenFailedException(name);
      }
    }
  }
}
