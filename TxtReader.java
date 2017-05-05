public class TxtReader implements Reader {
  public String fileName;
  public String file = "";

  public TxtReader(String f){
    fileName = f;
  }

  public String readLines() {
      String dirty="";
  		EasyReader inputFile = new EasyReader(fileName);
  		if (inputFile.bad()) {
  			System.err.println("*** Cannot open " + fileName + " ***");
  			System.exit(1);
  		}

  		String line = inputFile.readLine();
  		while (!inputFile.eof()) {
  			//System.out.println(line);
			  line = inputFile.readLine();
        dirty += line + " ";
  		}
      String clean = Cleaner.cleanWhiteSpace(dirty);
      file = clean;
      return clean;
  	}

    public String readFile() {
        return null;
    }
  }
