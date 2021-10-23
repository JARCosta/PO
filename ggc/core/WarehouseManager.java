package ggc.core;

//FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.FileNotFoundException;

import ggc.core.exception.BadEntryException;
import ggc.core.exception.ImportFileException;
import ggc.core.exception.UnavailableFileException;
import ggc.core.exception.MissingFileAssociationException;

public class WarehouseManager {
  private String _filename = "";
  private Warehouse _warehouse = new Warehouse();
  String _fileAssociation;

  public WarehouseManager(){
  }

  public int currentDate(){
    return _warehouse.currentDate();
  }
  public void advanceDate(int days) throws BadEntryException{
    _warehouse.advanceDate(days);
  }


  public void registerPartner(String name, String adress, String id) throws BadEntryException{
    _warehouse.registerPartner(name, adress, id);
  }


  public Product getProduct(String id){
    return _warehouse.getProduct(id);
  }
  public HashMap<String, Product> getProductMap(){
    return _warehouse.getProductMap();
  }
  public ArrayList<Product> getProductList(){
    return _warehouse.getProductList();
  }
  public ArrayList<Product> getProductSortedList(){
    return _warehouse.getProductSortedList();
  }


  public ArrayList<Batch> getBatchList(){
    return _warehouse.getBatchList();
  }
  public ArrayList<Batch> getBatchSortedList(){
    return _warehouse.getBatchSortedList();
  }
  public ArrayList<Batch> getBatchSortedList(Product product){
    return _warehouse.getBatchSortedList(product);
  }
  public ArrayList<Batch> getBatchSortedList(Partner partner){
    return _warehouse.getBatchSortedList(partner);
  }


  public Map<String, Partner> getPartnerMap(){
    return _warehouse.getPartnerMap();
  }
  public Partner getPartner(String id) throws BadEntryException{
    return _warehouse.getPartner(id);
  }
  public ArrayList<Partner> getPartnerSortedList(){
    return _warehouse.getPartnerSortedList();
  }



  /**
   * @@throws IOException
   * @@throws FileNotFoundException
   * @@throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    //FIXME implement serialization method
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
   */
  public void load(String filename) throws UnavailableFileException, ClassNotFoundException  {
    //FIXME implement serialization method
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
      _warehouse.importFile(textfile);
    } catch (IOException | BadEntryException /* FIXME maybe other exceptions*/  e) {
      throw new ImportFileException(textfile, e);
    }
  }
}
