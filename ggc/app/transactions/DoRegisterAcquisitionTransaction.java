package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import javax.sound.midi.Receiver;

import ggc.core.WarehouseManager;

import ggc.core.exception.InvalidProductIdException;
import ggc.core.exception.InvalidPartnerIdException;
import ggc.app.exception.UnknownPartnerKeyException;
import ggc.app.exception.UnknownProductKeyException;

/**
 * Register order.
 */
public class DoRegisterAcquisitionTransaction extends Command<WarehouseManager> {

  public DoRegisterAcquisitionTransaction(WarehouseManager receiver) {
    super(Label.REGISTER_ACQUISITION_TRANSACTION, receiver);
    addStringField("partnerId", Message.requestPartnerKey());
    addStringField("productId", Message.requestProductKey());
    addIntegerField("quantity", Message.requestAmount());
  }

  @Override
  public final void execute() throws CommandException {
    try{
      Object partner = (Object)_receiver.getPartner(stringField("partnerId"));
      Object product = (Object)_receiver.getProduct(stringField("productId"));
      int quantity = integerField("quantity");
      _receiver.registerAquisition(product, quantity, partner);  
    } catch(InvalidPartnerIdException ipie){
      throw new UnknownPartnerKeyException(stringField("partnerId"));
    } catch(InvalidProductIdException ipie){
      throw new UnknownProductKeyException(stringField("productId"));
    }
  }
}
