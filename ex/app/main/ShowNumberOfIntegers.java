package ex.app.main;

// In Command sub-classes we need to implement:
//
// 1. The constructor, that typically receives one or more objects of the core. 
//    We have to call the constructor of the super-class (usually with a title and the entity of the core)
//    The constructor, usually, creates the several Input entities needed to read input data from the user.
// 2. The method execute(), that calls the methods of the core entities
//
// User interaction (input and output data) is done using the classes Form, Input and Display as shown
// Each command has, by default, a Form and a Display that can be used in the execute method of the command,
// or you can create those objects explicitly in the execute method.

import java.util.List;
import ex.core.IntegerManager;
import pt.tecnico.uilib.menus.Command;

public class ShowNumberOfIntegers extends Command<IntegerManager> {        
  //Constructor
  public ShowNumberOfIntegers(IntegerManager ent) {
    super("Obter Número de Inteiros", ent);
  }
  
  protected void execute() { // executed when this option is selected
    List<Integer> list = _receiver.getNumbers();
 
    _display.popup("" + list.size());
  }
}
