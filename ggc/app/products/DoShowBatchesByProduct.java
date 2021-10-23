package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.Batch;
import ggc.core.Product;
import ggc.core.WarehouseManager;
//FIXME import classes

/**
 * Show all products.
 */
class DoShowBatchesByProduct extends Command<WarehouseManager> {

  DoShowBatchesByProduct(WarehouseManager receiver) {
    super(Label.SHOW_BATCHES_BY_PRODUCT, receiver);
    addStringField("product", Message.requestProductKey());
  }

  @Override
  public final void execute() throws CommandException {
    Product product = _receiver.getProduct(stringField("product"));
    for(Batch batch : _receiver.getBatchSortedList(product)){
      _display.addLine(batch.toString());
    }
    _display.display();  
  }

}
