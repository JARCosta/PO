package ggc.app.transactions;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//import ggc.core.Component;
import ggc.core.WarehouseManager;
import ggc.core.Partner;
import ggc.core.Product;

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
    String productId = stringField("productId");
    String partnerId = stringField("partnerId");
    int quantity = integerField("quantity");
    double price = realField("price");

    try {
      _receiver.registerAcquisition(partnerId, productId, quantity, price );
    } catch (InvalidPartnerIdException e1) {
      throw new UnknownPartnerKeyException(partnerId);
    } catch (InvalidProductIdException e1) {
      // ask simple or agregate product
      if(!Form.requestString(Message.requestAddRecipe()).toLowerCase().equals("s"))
        // register simple
        _receiver.registerSimpleProduct(productId);
      else{
        // register aggregate
        int nComponents = Form.requestInteger(Message.requestNumberOfComponents());
        Double alpha = Form.requestReal(Message.requestAlpha());
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<Integer> qnts = new ArrayList<>();
        for(int i=0;i<nComponents;i++){
          ids.add(Form.requestString(Message.requestProductKey()));
          qnts.add(Form.requestInteger(Message.requestAmount()));
        }
        try{
        _receiver.registerAggregateProduct(productId, alpha, ids, qnts);
        }catch (InvalidProductIdException e) {
          // duplicate product
          throw new UnknownProductKeyException(productId); // should be duplicate product exception
        }
        _receiver.registerAcquisition(partnerId, productId, quantity, price);
      }
    }
    /*
    try {
      // find partner
      partner = _receiver.getPartner(stringField("partnerId"));
    } catch(InvalidPartnerIdException ipie){
      // no partner found
      throw new UnknownPartnerKeyException(stringField("partnerId"));
    }
    try{
      // find product
      product = _receiver.getProduct(stringField("productId"));
    }
    catch(InvalidProductIdException ipie){
      // no product found
      if(!Form.requestString(Message.requestAddRecipe()).toLowerCase().equals("s"))
        // create simple product
        product = _receiver.registerSimpleProduct(stringField("productId"));
      else{
        // create agregate product
        int nComponents = Form.requestInteger(Message.requestNumberOfComponents());
        Double alpha = Form.requestReal(Message.requestAlpha());
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<Integer> qnts = new ArrayList<>();
        for(int i=0;i<nComponents;i++){
          // ask components
          ids.add(Form.requestString(Message.requestProductKey()));
          qnts.add(Form.requestInteger(Message.requestAmount()));
        }try {
          // register agregate product
          product = _receiver.registerAggregateProduct(stringField("productId"),alpha, ids, qnts);
        } catch (InvalidProductIdException e) {
          // duplicate product
          throw new UnknownProductKeyException(stringField("productId")); // should be duplicate product exception
        }
        // take the product created or found
        // register warehouse.aquisition partner.aquisition and partner.batch
        _receiver.registerAcquisition(product, quantity, partner);
        partner.registerBatch(price, quantity, partner, product);
        partner.registerAcquisition(product, quantity);      
      }
    }*/
  }
}
