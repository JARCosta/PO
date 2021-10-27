package ggc.app.products;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.app.exception.UnknownProductKeyException;
import ggc.core.Batch;
import ggc.core.Product;
import ggc.core.WarehouseManager;
//FIXME import classes
import ggc.core.exception.InvalidProductIdException;
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
    try {
      Product product = _receiver.getProduct(stringField("product"));
      for(Batch batch : _receiver.getBatchSortedList(product)){
        _display.addLine(batch.toString());
      }
      _display.display();  
    } catch (InvalidProductIdException ipke) {
      throw new UnknownProductKeyException(stringField("product"));
    }
  }

}
