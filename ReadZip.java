/**
 * Read in the contents of a zip file.
 *
 * If you're not seeing exactly what you want in the command
 * prompt/terminal, try typing this:
 *      (Windows): `chcp 65001`
 *      (Mac): `export LANG=en_US.UTF-8`
 *
 * Author: Nicholas Zufelt
 * Date: 4/23/17
 * Course: AP Computer Science A
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.nio.charset.Charset;

@Deprecated

public class ReadZip
{
    private static final int BUFFER_SIZE = 8192;
    private static final String INPUT_ZIP_FILE = "simple.zip";

    public static void main(String[] args)
    {
    	ReadZip zippedFileReader = new ReadZip();
    	System.out.println("\n\n" + zippedFileReader.readZip(INPUT_ZIP_FILE));
    }

    public String readZip(String zipFileName){
        String docText = "";

        try{
            // A ZipInputStream reads in the zip file
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFileName),
                                                    Charset.forName("UTF-8"));

            // Iterate through the files up the zipped folder
            ZipEntry ze = zis.getNextEntry();

            while(ze != null) {
                System.out.println("Opening: " + ze.getName());
                docText += readInFileContents(zis);
                ze = zis.getNextEntry();
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
