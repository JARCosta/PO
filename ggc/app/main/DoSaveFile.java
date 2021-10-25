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

  /*
  @Override
  public final void execute() throws CommandException {
    String _filename;
    try{
      _receiver.save();
    } catch (IOException | MissingFileAssociationException fnf){
      _filename = Form.requestString(Message.newSaveAs());
      try{
        _receiver.saveAs(_filename);
      } catch (IOException | MissingFileAssociationException das){
        throw new FileOpenFailedException(_filename);
      }
    }
    //FIXME implement command and create a local Form      
  }


  @Override
  public final void execute() throws CommandException {
    try {
      _receiver.saveAs(Form.requestString(Message.newSaveAs()));
    } catch (MissingFileAssociationException lala) {
      System.out.println("lalalallala");
    } catch (IOException papa) {
      System.out.println("papapapapa");
    }
  }
  */

  @Override
  public final void execute() throws CommandException {
    try{
      _receiver.save();
    } catch (FileNotFoundException lili){
      try {
        _receiver.saveAs(Form.requestString(Message.newSaveAs()));
      } catch (MissingFileAssociationException lala) {
        System.out.println("lalalallala");
      } catch (IOException papa) {
 //       System.out.println("papapapapa");
      }
    } catch (MissingFileAssociationException lulu){
      System.out.println("lululululu");
    } catch (IOException io){
  //    System.out.println("ioiooioioio");
    }
  }
}
