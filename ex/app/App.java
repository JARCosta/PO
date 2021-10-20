package ex.app;

import static pt.tecnico.uilib.Dialog.UI;

import ex.core.IntegerManager;
import ex.app.main.MainMenu;

public class App {

  public static void main (String[] args) {
    try (var ui = UI) {
      IntegerManager myObject = new IntegerManager();  // Create main Entity
      MainMenu mainMenu = new MainMenu(myObject); // Create menu with Entity

      mainMenu.open(); // Open the menu
    }
  }
}
