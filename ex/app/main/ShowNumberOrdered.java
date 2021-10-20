package ex.app.main;

import ex.core.IntegerManager;
import pt.tecnico.uilib.menus.Command;

public class ShowNumberOrdered extends Command<IntegerManager>{
    
    public ShowNumberOrdered(IntegerManager ent){
        super("Mostra numeros ordenados", ent);
    }

    @Override
    protected void execute(){
        for(Integer i : _receiver.getOrderedNumbers()){
            _display.popup(""+i);
        }
    }

}
