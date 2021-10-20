package ex.app.main;

// In Menu sub-clasess we only need to implement the CONSTRUCTOR
// This is done as shown: 
// 1. The constructor typically receives and Object of the CORE
// 2. The constructor calls to the constructor of the superclass Menu with two parameters: the title of the MENU and a list of commands that receive the core entity
// 3. In this exemple, the menu has two options represented by two instances od OptionA and OptionB.

import ex.core.IntegerManager;
import pt.tecnico.uilib.menus.Menu;
import pt.tecnico.uilib.menus.DoOpenMenu;
import ex.app.edit.EditMenu;



public class MainMenu extends Menu {

    //Constructor
    public MainMenu(IntegerManager ent) {
        super("MENU PRINCIPAL",
              new ListNumbers(ent),
              new ShowNumberOfIntegers(ent),
              new DoOpenMenu("Abrir Menu Edição", new EditMenu(ent)),
              new ShowGreaterThan(ent),
              new ShowNumberOrdered(ent)
              //new ListNumbers(ent), new ShowNumberOfIntegers(ent), new ShowEditMenu(ent)
              ); 
    }
}

/*
public class MainMenu(IntegerManager ent) {
    super("MENU PRINCIPAL", new Command<?>[] {
                new ListNumbers(ent), new ShowNumberOfIntegers(ent), new ShowEditMenu(ent),
                new ShowGreaterThan(ent),
                }); 
}
*/