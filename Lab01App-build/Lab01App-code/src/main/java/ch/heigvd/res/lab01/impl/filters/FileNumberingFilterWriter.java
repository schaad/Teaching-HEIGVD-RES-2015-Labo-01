package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int noLine = 0;
  private boolean firstLine = true;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {     
     String[] tabStr = Utils.getNextLine(str.substring(off, off + len));
     String tmpStr = "";
     
     if(firstLine){
        tmpStr = ++noLine + "\t";
        firstLine = false;
     }
     
     while(true){     
        if(tabStr[0].isEmpty()){
           tmpStr += tabStr[1];
           break;
        } else {
           tmpStr += tabStr[0] + ++noLine + "\t";
           tabStr = Utils.getNextLine(tabStr[1]);
        }
     }
     
     out.write(tmpStr, 0, tmpStr.length());
     
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
     String tmpStr = new String(cbuf);
     write(tmpStr, off, len);
     
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {
     String tmpStr = "";
     
     // Cette condition n'est pas optimale car en cas de 2 '\n' consécutifs, 
     // la numérotation ne se ferait pas, néanmoins par manque de temps je ne 
     // l'ai pas améliorée
     if(firstLine && c != '\n'){
        tmpStr = ++noLine + "\t";
        firstLine = false;
     }
     
     out.write(tmpStr);
     out.write(c);
     
     if(c == '\n' || c == '\r'){
        firstLine = true;
     }
     
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
