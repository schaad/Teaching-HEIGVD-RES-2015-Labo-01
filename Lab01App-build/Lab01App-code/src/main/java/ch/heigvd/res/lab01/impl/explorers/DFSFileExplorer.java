package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered node
 * (file and directory). When the explorer reaches a directory, it visits all files
 * in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

   private int size;
   private boolean marked[];
   private int count;

   @Override
   public void explore(File rootDirectory, IFileVisitor visitor) {

      File tab[] = rootDirectory.listFiles();
      //rootDirectory.list
      File tmp;
      
      if (tab == null) {
         visitor.visit(rootDirectory);
      } else {
         for (File s : tab) {
            //tmp = new File(rootDirectory.getPath() + "/" + s);
            visitor.visit(s);
            //explore(tmp, visitor);
            ///////////////////// A REPRENDRE ICI ///////////////////////////
            //visitor.visit(rootDirectory);
            //explore(new File(rootDirectory.getPath() + s), visitor);
            
         }
      }

      //throw new UnsupportedOperationException("The student has not implemented this method yet.");
   }
}
