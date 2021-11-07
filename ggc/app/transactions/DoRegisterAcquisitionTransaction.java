package ggc.app.transactions;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
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
    int quantity = integerField("quantity");
    double price = realField("price");

    try {
      _receiver.registerAcquisition(stringField("partnerId"), stringField("productId"), quantity, price );
    } catch (InvalidProductIdException e2) {
      // ask simple or agregate product
      if(!Form.requestString(Message.requestAddRecipe()).toLowerCase().equals("s"))
        // register simple
        _receiver.registerSimpleProduct(stringField("productId"));
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
        _receiver.registerAggregateProduct(stringField("productId"), alpha, ids, qnts);
        }catch (InvalidProductIdException e) {
          // duplicate product
          throw new UnknownProductKeyException(stringField("productId")); // should be duplicate product exception
        }
      }
      try {
        _receiver.registerAcquisition(stringField("partnerId"), stringField("productId"), quantity, price );
      } catch (InvalidPartnerIdException | InvalidProductIdException e) {
        System.out.println("second try to registerAquisition failed");
      }
    } catch (InvalidPartnerIdException e) {
      throw new UnknownPartnerKeyException(stringField("partnerId"));
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
