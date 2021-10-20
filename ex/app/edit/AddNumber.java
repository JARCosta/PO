package ex.app.edit;

// In Command sub-classes we basically interact with  entities of the core (call methods)
// In Command sub-classes we need to implement:
//
// 1. The constructor, that typically receives one or more objects of the core. 
//    We have to call the constructor of the super-class (usually with a title and the entity of the core)
//    The constructor, usually, creates the several Input entities needed to read input data from the user.
// 2. The method execute(), that calls the methods of the core entities
//
// User interaction (input and output data) is done using the classes Form and Display as shown
// Each command has, by default, a Form and a Display that can be used in the execute method of the command,
// or you can create those objects explicitly in the execute method.

/**
 * This command adds a new number to the list of numbers kept by the applcation.
 **/

import ex.core.IntegerManager;
import pt.tecnico.uilib.menus.Command;

public class AddNumber extends Command<IntegerManager> {
  //Constructor
  public AddNumber(IntegerManager ent) {
    super("Adicionar Número", ent);
    addIntegerField("number", "Introduza um número: ");
  }

  protected void execute() {  // executed when this option is selected
    Integer number = integerField("number");
    String message;

    if (_receiver.addNumber(number))
      message = "Número adicionado: " + number;
    else
      message = "Número não adicionado";

    _display.popup(message);
  }
}
