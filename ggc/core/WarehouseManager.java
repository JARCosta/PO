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
  /*public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    //FIXME implement serialization method
  }*/
  
  /**
   * @@param filename
   * @@throws MissingFileAssociationException
   * @@throws IOException
   * @@throws FileNotFoundException
   */
  /*public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = filename;
    save();
  }*/

  /**
   * @@param filename
   * @@throws UnavailableFileException
   */
  /*public void load(String filename) throws UnavailableFileException, ClassNotFoundException  {
    //FIXME implement serialization method
  }*/

  /**
   * @param textfile
   * @throws ImportFileException
   */
  /*public void importFile(String textfile) throws ImportFileException {
    try {
      _warehouse.importFile(textfile);
    } catch (IOException | BadEntryException /* FIXME maybe other exceptions  e) {
      throw new ImportFileException(textfile, e);
    }
  }*/

//-------------------------------------------------------------------------------------------------------------------------------------------


  /**
   * Serialize the persistent state of this application.
   * 
   * @throws MissingFileAssociationException if the name of the file to store the persistent
   *         state has not been set yet.
   * @throws IOException if some error happen during the serialization of the persistent state
   */
  public void save() throws MissingFileAssociationException, IOException {
    if( _fileAssociation == null )
      throw new MissingFileAssociationException();

    saveAs(_fileAssociation);
  }

  /**
   * Serialize the persistent state of this application into the specified file.
   * 
   * @param filename the name of the target file
   *
   * @throws MissingFileAssociationException if the name of the file to store the persistent
   *         is not a valid one.
   * @throws IOException if some error happen during the serialization of the persistent state
   */
  public void saveAs(String filename) throws MissingFileAssociationException, IOException {
    
    try(FileOutputStream fileOut = new FileOutputStream(filename); ObjectOutputStream outStream = new ObjectOutputStream(fileOut)) {
      outStream.writeObject(_warehouse);
      _fileAssociation = filename;
    } catch ( FileNotFoundException fnfe ) {
      throw new MissingFileAssociationException();
    } 
  }

  /**
   * Recover the previously serialized persitent state of this application.
   * 
   * @param filename the name of the file containing the perssitente state to recover
   *
   * @throws IOException if there is a reading error while processing the file
   * @throws FileNotFoundException if the file does not exist
   * @throws ClassNotFoundException 
   */
  public void load(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
    try(FileInputStream fileIn = new FileInputStream(filename); ObjectInputStream inStream = new ObjectInputStream(fileIn)) {
      _warehouse = (Warehouse) inStream.readObject();
      _fileAssociation = filename;
    }
  }

  /**
   * Set the state of this application from a textual representation stored into a file.
   * 
   * @param txtfile the filename of the file with the textual represntation of the state of this application.
   * @throws ImportFileException if it happens some error during the parsing of the textual representation.
   */
  public void importFile(String txtfile) throws ImportFileException {
    try {
      _warehouse.importFile(txtfile);
    } catch (IOException |BadEntryException/*| BadEntrySpecificationException | BadEntryUserException*/ e) {
      throw new ImportFileException();
    }
  }
}
