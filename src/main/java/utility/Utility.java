package utility;

import java.util.Iterator;
import java.util.List;

public class Utility {

    public static void removeDividendRow(List<List<String>> objTable) {

        Iterator<List<String>> itr = objTable.iterator();

        while (itr.hasNext()) {
            List<String> row = itr.next();
            if (row.isEmpty())
                itr.remove();
            for (String cell : row) {
                if (cell.contains("Dividend")) {
                    itr.remove();
                }
            }
        }

    }

}
