package ggc.core;

import java.util.ArrayList;
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
import ggc.core.exception.UnavailableFileException;
import ggc.core.exception.MissingFileAssociationException;

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

  public void registerSimpleProduct(String id){
    _warehouse.registerSimpleProduct(id);
  }

  public void registerAggregateProduct(String id, double aggravation, List<Component> comps) throws InvalidProductIdException{
    _warehouse.registerAggregateProduct(id, aggravation, comps);
  }

  public void registerAggregateProduct(String id, double aggravation, List<String> ids, List<Integer> qnts) throws InvalidProductIdException{
    ArrayList<Component> comps = new ArrayList<>();
    for(int i=0;i<ids.size();i++){
        Component comp = new Component(getProduct(ids.get(i)), (int)qnts.get(i));
        comps.add(comp);
    }
    _warehouse.registerAggregateProduct(id, aggravation, comps);
  }

  public Product getProduct(String id) throws InvalidProductIdException{
    return _warehouse.getProduct(id);
  }
  public Map<String, Product> getProductMap(){
    return _warehouse.getProductMap();
  }
  public List<Product> getProductList(){
    return _warehouse.getProductList();
  }
  public List<Product> getProductSortedList(){
    return _warehouse.getProductSortedList();
  }



//BATCH

  public List<Batch> getBatchList(){
    return _warehouse.getBatchList();
  }
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
  public void registerAcquisition(Object product, Object quantity, Object partner){
    ((Partner)partner).registerAcquisition((Product)product,(int)quantity);
    _warehouse.registerAcquisition((Product)product,(int)quantity,(Partner)partner);
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
    } catch(ClassNotFoundException | IOException a){
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
