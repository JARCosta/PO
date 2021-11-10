package ggc.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import ggc.core.exception.BadEntryException;
import ggc.core.exception.DuplicatePartnerIdException;
import ggc.core.exception.ImportFileException;
import ggc.core.exception.InvalidDateException;
import ggc.core.exception.InvalidPartnerIdException;
import ggc.core.exception.InvalidProductIdException;
import ggc.core.exception.InvalidTransactionKeyException;
import ggc.core.exception.UnavailableFileException;
import ggc.core.notifications.Notification;
import ggc.core.exception.MissingFileAssociationException;
import ggc.core.exception.ProductAmountException;

import ggc.core.partners.Partner;
import ggc.core.products.Product;
import ggc.core.transactions.Acquisition;
import ggc.core.transactions.Sale;
import ggc.core.transactions.Transaction;

public class WarehouseManager{
  private String _filename = "";
  private Warehouse _warehouse = new Warehouse();

  public WarehouseManager(){
  }


//DATE

  public int currentDate(){
    return _warehouse.currentDate();
  }
  public void advanceDate(int days) throws InvalidDateException{
    _warehouse.advanceDate(days);
  }


//PRODUCT

  public Product registerSimpleProduct(String id){
    return _warehouse.registerSimpleProduct(id);
  }

  public Product registerAggregateProduct(String id, double aggravation, List<String> ids, List<Integer> qnts) throws InvalidProductIdException{
    return _warehouse.registerAggregateProduct(id, aggravation, ids, qnts);
  }

  public Product getProduct(String id) throws InvalidProductIdException{
    return _warehouse.getProduct(id);
  }

  public List<Product> getProductSortedList(){
    return _warehouse.getProductSortedList();
  }



//BATCH

  public List<Batch> getBatchSortedList(){
    return _warehouse.getBatchSortedList();
  }
  public List<Batch> getBatchSortedList(Product product){
    return _warehouse.getBatchSortedList(product);
  }
  public List<Batch> getBatchSortedList(Partner partner){
    return _warehouse.getBatchSortedList(partner);
  }


//PARTNER

  public void registerPartner(String id, String name, String adress) throws BadEntryException, DuplicatePartnerIdException{
    _warehouse.registerPartner(id, name, adress);
  }
  public Map<String, Partner> getPartnerMap(){
    return _warehouse.getPartnerMap();
  }
  public Partner getPartner(String id) throws InvalidPartnerIdException{
      return _warehouse.getPartner(id);
  }
  public List<Partner> getPartnerSortedList(){
    return _warehouse.getPartnerSortedList();
  }

//TRANSACTION
  public List<Transaction> getTransactionList(){
    return _warehouse.getTransactionList();
  }

  public List<Acquisition> getAcquisitionList(String partnerId) throws InvalidPartnerIdException{
    return _warehouse.getAcquisitionList(partnerId);
  }

  public List<Sale> getSaleList(String partnerId) throws InvalidPartnerIdException{
    return _warehouse.getSaleList(partnerId);
  }

  public Transaction getTransaction(int transactionId) throws InvalidTransactionKeyException {
    return _warehouse.getTransaction(transactionId);
  }

  public void registerAcquisition(String partnerId, String productId, int quantity, double price) throws InvalidPartnerIdException, InvalidProductIdException{
    _warehouse.registerAcquisition(getPartner(partnerId), getProduct(productId),quantity, price);
  }
  public void registerSaleByCredit(String partnerId, String productId, int quantity, int deadline) throws ProductAmountException, InvalidProductIdException, InvalidPartnerIdException{
    _warehouse.registerSaleByCredit(partnerId, productId, deadline,quantity);
  }

  public void registerBreakSownSale(String partnerId, String productId, int quantity) throws ProductAmountException, InvalidProductIdException, InvalidPartnerIdException{
    _warehouse.registerBreakSownSale(partnerId,productId,quantity);
  }

  public void pay(int transactionId) throws IndexOutOfBoundsException{
    _warehouse.pay(transactionId);
  }

  //NOTIFICATION

  public void toggleNotifications(String partnerId, String productId) throws InvalidPartnerIdException, InvalidProductIdException{
    _warehouse.toggleNotifications(partnerId,productId);
  }
  public Collection<Notification> showNotifications(String partnerId) throws InvalidPartnerIdException{
    return _warehouse.showNotifications(partnerId);
  }

  /**
   * @@throws IOException
   * @@throws MissingFileAssociationException
   */
  public void save() throws MissingFileAssociationException, IOException {
    FileOutputStream fileOut = new FileOutputStream(_filename);
    ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
    outStream.writeObject(_warehouse);
    outStream.close();
    fileOut.close();
  }

  /**
   * @@param filename
   * @@throws MissingFileAssociationException
   * @@throws IOException
   * @@throws FileNotFoundException
   */
  public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = filename;
    save();
  }

  /**
   * @@param filename
   * @@throws UnavailableFileException
   * @throws IOException
   */
  public void load(String filename) throws UnavailableFileException{
    try(ObjectInputStream file = new ObjectInputStream(new FileInputStream(filename))){
      _warehouse = (Warehouse) file.readObject();
      _filename = filename;

    } catch(ClassNotFoundException a){
      throw new UnavailableFileException(filename);
    } catch(IOException a){
      throw new UnavailableFileException(filename);
    }
  }

  /**
   * @param textfile
   * @throws ImportFileException
   * @throws InvalidProductIdException
   * @throws InvalidPartnerIdException
   * @throws DuplicatePartnerIdException
  */
  public void importFile(String textfile) throws ImportFileException{
    try {
      _warehouse.importFile(textfile);
   } catch (IOException | BadEntryException | InvalidPartnerIdException | InvalidProductIdException | DuplicatePartnerIdException  e) {
      throw new ImportFileException(textfile, e);
    }
  }
}
