package ggc.core;

//FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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

  public void advanceDate(int days){
    _warehouse.advanceDate(days);
  }

  public void registerPartner(String name, String adress, String id){
    _warehouse.registerPartner(name, adress, id);
  }

  public Map<String, Partner> getPartners(){
    return _warehouse.getPartners();
  }

  public Partner getPartner(String id){
    return _warehouse.getPartner(id);
  }

  public HashMap<String, Product> getProductMap(){
    return _warehouse.getProductMap();
  }

  public Product getProduct(String id){
    return _warehouse.getProduct(id);
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
