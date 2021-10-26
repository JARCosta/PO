package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.FileNotFoundException;

import ggc.app.exception.FileOpenFailedException;
import ggc.core.WarehouseManager;

import ggc.core.exception.MissingFileAssociationException;
import ggc.core.exception.UnavailableFileException;

class DoOpenFile extends Command<WarehouseManager> {

  /** @param receiver */
  DoOpenFile(WarehouseManager receiver) {
    super(Label.OPEN, receiver);
    addStringField("name", Message.openFile());
  }

  @Override
  public final void execute() throws CommandException {
    try {
      _receiver.load(stringField("name"));
    } catch (UnavailableFileException noFile) {
      throw new FileOpenFailedException(stringField("name"));
    } catch (Exception e){
      System.out.println("appapapapap");
    }
  }

}
