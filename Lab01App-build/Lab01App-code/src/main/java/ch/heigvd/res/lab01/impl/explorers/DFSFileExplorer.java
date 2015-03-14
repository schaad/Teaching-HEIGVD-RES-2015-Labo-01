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
      
      visitor.visit(rootDirectory);
      
      File tabFile[] = rootDirectory.listFiles();
      
      if (tabFile != null) {
         for (File f : tabFile) {
            if(f.isFile()){
               visitor.visit(f);
            } else {
               explore(f, visitor);
            }
         }
      }

      //throw new UnsupportedOperationException("The student has not implemented this method yet.");
   }
}
