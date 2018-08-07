package prog02;

/**
 *
 * @author vjm
 */
public class Main {

	/** Processes user's commands on a phone directory.
      @param fn The file containing the phone directory.
      @param ui The UserInterface object to use
             to talk to the user.
      @param pd The PhoneDirectory object to use
             to process the phone directory.
	 */
	public static void processCommands(String fn, UserInterface ui, PhoneDirectory pd) {
		pd.loadData(fn);

		String[] commands = {
				"Add/Change Entry",
				"Look Up Entry",
				"Remove Entry",
				"Save Directory",
		"Exit"};

		String name, number, oldNumber;
               // oldNumber = null;

		while (true) {
			int c = ui.getCommand(commands);
			switch (c) {
			case -1:
				ui.sendMessage("You clicked the red x, restarting.");
				break;
			case 0:
				// Ask for the name.
                        
                                name = ui.getInfo("Enter name");
                                
				// !!! Check for null (cancel) or "" (blank)
                                if(name == null){
                                    break;    
                                }
                                 if(name.compareTo("") == 0){
                                    ui.sendMessage("Blanks are not allowed");
                                    break; 
                                }
                             
                                oldNumber = pd.lookupEntry(name);
				// Ask for the number.
                                
                                number = ui.getInfo("Enter number");
				// !!! Check for cancel.  Blank is o.k.
                                if(number == null){
                                    break;
                                }
				// Call addOrChangeEntry
                                oldNumber = pd.lookupEntry(name);
                                pd.addOrChangeEntry(name, number);
				// Report the result
                                
                                if(oldNumber == null){
                                    ui.sendMessage(name + " was added to the directory\nNew number: " + number);
                                }
                                else{
                                    ui.sendMessage("Number for " + name + " was changed\nOld number: " + oldNumber + "\nNew number: " + number);
                                }
                                 oldNumber = number;
                         
				break;
			case 1:
				// implement
                                name = ui.getInfo("Enter name");
                                
                                if(name == null){
                                    break;
                                }
                                if(name.compareTo("") == 0){
                                    ui.sendMessage("Blanks are not allowed");
                                    break;
                                   
                                }
                                
                                
                                number = pd.lookupEntry(name);
                                
                                if(number == null){
                                    ui.sendMessage(name + " is not listed in the directory");
                                }
                                else{
                                    ui.sendMessage("The number for " + name + " is " + number);
                                }
                                
				break;
			case 2:
				// implement
                                name = ui.getInfo("Enter name");
                                number = pd.lookupEntry(name);
                                
                                  if(name == null){
                                    break;
                                    
                                }
                                if(name.compareTo("") == 0){
                                    ui.sendMessage("Blanks are not allowed");
                                    break; 
                                }
                              
                                 
                                if(number == null){
                                    ui.sendMessage(name + " is not listed in the directory");
                                }
                                else{
                                    ui.sendMessage("Removed entry with name " + name + " and number " + number);
                                    pd.removeEntry(name);
                                }
                                
				break;
			case 3:
				// implement
                                pd.save();
                                ui.sendMessage("Saved directory to " + fn);
				break;
			case 4:
				// implement
                                ui.sendMessage("Goodbye");
				return;
			}
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		String fn = "csc220.txt";
		PhoneDirectory pd = new SortedPD();
		UserInterface ui = new GUI();
		processCommands(fn, ui, pd);
	}
}
