import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DocXReader {

    private String file;
    private static final int BUFFER_SIZE = 8192;

    public static void main(String[] args) {

    }

    public DocXReader(String filename) {
        file = filename;
    }

    public String readFile() { //reads the Docx file and returns the String contents of the desired subfile
        String docText = "";

        try{
            // A ZipInputStream reads in the zip file
            ZipInputStream zis = new ZipInputStream(new FileInputStream(file),
                    Charset.forName("UTF-8"));

            // Iterate through the files up the zipped folder
            ZipEntry ze = zis.getNextEntry();

            while(ze != null) {

                int index = ze.getName().indexOf('/');
                if (ze.getName().matches(".*\\Qdocument.xml\\E")) {

                    docText += readInFileContents(zis);
                    break;
                }
                else {
                    ze = zis.getNextEntry();
                }
            }

            // Close the stream to release the file handle
            zis.closeEntry();
            zis.close();
        }catch(IOException ex){
            // We had some kind of problem, so print its stack trace
            ex.printStackTrace();
        }
        return docText;
    }

    private String readInFileContents(ZipInputStream zis) throws IOException{
        String document = "";

        // A buffer is an array used to read in or write out data.
        final byte[] buffer = new byte[BUFFER_SIZE];

        int bytesRead = 0;
        /* The while statement says: "read in BUFFER_SIZE amount of bytes
         * of data, store it in `buffer`, and then update `bytesRead`
         * accordingly. Repeat this process until the buffer didn't read
         * anything new, then stop."
         */
        while((bytesRead = zis.read(buffer)) != -1) {
            // Create a string from the data we just read in, append it to `docText`.
            document += new String(buffer, 0, bytesRead);
        }
        return document;
    }
}
