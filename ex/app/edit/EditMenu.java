package ex.app.edit;

// In Menu sub-clasess we only need to implement the CONSTRUCTOR
// This is done as shown: 
// 1. The constructor typically receives and Object of the CORE
// 2. The constructor calls to the constructor of the superclass Menu with two parameters: the title of the MENU and a list of commands that receive the core entity
// 3. In this exemple, the menu menu represents the operations avalable for changing the list of numbers kept by the applciation

import ex.core.IntegerManager;
import pt.tecnico.uilib.menus.Menu;

public class EditMenu extends Menu {
  //Constructor
  public EditMenu(IntegerManager ent) {
    super("Menu De Edição",
          new AddNumber(ent)
          ); 
  }
}
