package prog02;

import java.io.*;

/**
 *
 * @author vjm
 */
public class SortedPD extends ArrayBasedPD {
    
    /** Add an entry or change an existing entry.
      @param name The name of the person being added or changed
      @param number The new number to be assigned
      @return The old number or, if a new entry, null
  */
  public String addOrChangeEntry (String name, String number) {
    String oldNumber = null;
    FindOutput fo = find(name);
    int InsertionIndex = fo.index;
   // int InsertionIndex = 0;
    
    if (fo.found) {
      oldNumber = theDirectory[fo.index].getNumber();
      theDirectory[fo.index].setNumber(number);
    } else {
      if (size >= theDirectory.length)
        reallocate();
                  
      for(int i = theDirectory.length-1;i> InsertionIndex;i--){
          theDirectory[i] = theDirectory[i-1];
      }
      theDirectory[InsertionIndex] = new DirectoryEntry(name, number);
      size++;
                  
      return null;
    }
    modified = true;
    return oldNumber;
  }
  
   /** Find an entry in the directory.
      @param name The name to be found
      @return A FindOutput object containing the result of the search.
  */
  protected FindOutput find (String name) {
    
      int first = 0;
      int last = size-1;
      
      while(first <= last){
            int middle = (first+last)/2;
            int cmp = name.compareTo(theDirectory[middle].getName());
        
            if(cmp < 0){
               last = middle - 1;
            }
            else if(cmp > 0){
               first = middle + 1;
            }
            else
                return new FindOutput(true, middle);
             
        }
  
        return new FindOutput(false, first);
  

  }

  /** Remove an entry from the directory.
      @param name The name of the person to be removed
      @return The current number. If not in directory, null is
      returned
  */
  public String removeEntry (String name) {
    FindOutput fo = find(name);
    if (!fo.found)
      return null;
    DirectoryEntry entry = theDirectory[fo.index];
    theDirectory[fo.index] = theDirectory[size-1];
    size--;
    modified = true;
    return entry.getNumber();
  }
  
  
  
}
