package ex.app.main;

/**
 * This command class open the edit menu. This menu provides options for changing the list of number kept
 * by this application.
 **/

import ex.core.IntegerManager;
import ex.app.edit.EditMenu;
import pt.tecnico.uilib.menus.Command;

public class ShowEditMenu extends Command<IntegerManager> {        
  //Constructor
  public ShowEditMenu(IntegerManager ent) {
    super("Abrir Menu Edição", ent);
  }
  
  protected void execute() { // executed when this option is selected
    EditMenu menu = new EditMenu(_receiver);
    menu.open();
  }
}
