package ggc.app.transactions;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//import ggc.core.Component;
import ggc.core.WarehouseManager;

import ggc.core.exception.InvalidProductIdException;
import ggc.core.exception.InvalidPartnerIdException;

import java.util.ArrayList;
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
    addRealField("price", Message.requestPrice());
    addIntegerField("quantity", Message.requestAmount());
  }

  @Override
  public final void execute() throws CommandException {
    try{
      Object partner = (Object)_receiver.getPartner(stringField("partnerId"));
      Object product = (Object)_receiver.getProduct(stringField("productId"));
      int quantity = integerField("quantity");
      _receiver.registerAcquisition(product, quantity, partner);  
    } catch(InvalidPartnerIdException ipie){
      throw new UnknownPartnerKeyException(stringField("partnerId"));
    } catch(InvalidProductIdException ipie){
      addBooleanField("newRecipe",Message.requestAddRecipe());
      if(booleanField("newRecipe")==false){
        _receiver.registerSimpleProduct(stringField("productId"));
      }else{
        addIntegerField("nComponents", Message.requestNumberOfComponents());
        addRealField("agravation", Message.requestAlpha());
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<Integer> qnts = new ArrayList<>();
//        ArrayList<Component> comps = new ArrayList<>();
        for(int i=0;i<integerField("nComponentes");i++){
          addStringField("id", Message.requestProductKey());
          ids.add(stringField("id"));
          addIntegerField("qnt", Message.requestPrice());
          qnts.add(integerField("qnt"));
/*
          try {
            comps.add(new Component(_receiver.getProduct(stringField("id")), integerField("qnt")));
          } catch (InvalidProductIdException e) {
            throw new UnknownProductKeyException(stringField("id"));
          }
        }_receiver.registerAggregateProduct(stringField("productId"),realField("agravation"), comps);
*/
        }try {
          _receiver.registerAggregateProduct(stringField("productId"),realField("agravation"), ids, qnts);
        } catch (InvalidProductIdException e) {
          throw new UnknownProductKeyException(stringField("productId"));
        }
      }
    }
  }
}
