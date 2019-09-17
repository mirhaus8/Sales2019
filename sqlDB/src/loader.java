package loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class loader {

	/** contains the initial board state of all the levels */
	private Vector<int[][]> _levels;

	
	    public loader() {

			_levels = new Vector<>();
		}

		/**
		 * Loads all the levels to the internal levels buffer
		 * 
		 * @param levelsFile
		 *            the name of the file contains the levels
		 * @return true if success
		 * @throws IOException
		 *             if there is any error with the file
		 */
		public boolean load(String levelsFile) throws IOException {
			_levels.clear();

			BufferedReader br = new BufferedReader(new FileReader(levelsFile));
			String line = null;
			int[][] level = null;
			int w = 0;
			int row = 0;
			while ((line = br.readLine()) != null) {
				
				// end of level
			
				if (line.length()==1) {
					if (null != level) {
						_levels.add(level);
						level = null;
						w=Integer.parseInt(line);
						continue;
					}
				}

				// board size
				
				if (line.trim().length()==1) {
					
					w = Integer.valueOf(""+line.charAt(0));
					
					continue;
				}

				// start of level definition
				if (null == level && w > 0) {
					level = new int[w][w];
					
					row = 0;
				}

				// regular board line
				String[] values = line.split(",");
				for (int col = 0; col < values.length; col++) {
					String st =  ""+values[col];
					int x = Integer.parseInt(st);
					level[col][row] = x;
					/*if (0 != x) {
						System.out.println(x+"mirhaus");
						level[col][row] = x;
					} else {
						System.out.println("elese");
						br.close();
						return false;
					}*/
				}
				row++;
				
			}
			
			if (null != level) {
				
				_levels.add(level);
				level = null;
			}
			br.close();
			return true;
		}

		/**
		 * @return the number of levels available
		 */
		public int getLevelsCount() {
			return _levels.size();
		}

		/**
		 * @param index
		 *            - the level number
		 * @return the initial state of level number {@code index}
		 * 
		 *         is recommended to create a deep copy of the array.
		 */
		public int[][] get(int index) {
			return _levels.get(index);
		}


}
