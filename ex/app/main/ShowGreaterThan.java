package ex.app.main;

import java.util.List;
import ex.core.IntegerManager;
import pt.tecnico.uilib.menus.Command;

public class ShowGreaterThan extends Command<IntegerManager> {
  //Constructor
  public ShowGreaterThan(IntegerManager ent) {
    super("Apresentar números maiores que um dado valor", ent);
    addIntegerField("lowerBound", "Insira número a comparar: ");
  }

  protected void execute() { // executed when this option is selected
    
    List<Integer> list = _receiver.getNumbers();

    for (Integer i : list) {
      if (i > integerField("lowerBound"))
        _display.addLine("" + i);
    }
    
    _display.display();
    // or have just _display.popup(_receiver.getNumbers());
  }
}