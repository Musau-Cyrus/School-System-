package util;

import javax.swing.*;
import javax.swing.table.TableRowSorter;

public class DataFilter {

    public static void filterTable(JTable table, String query) {
        TableRowSorter<?> rowSorter = (TableRowSorter<?>) table.getRowSorter();
        if (rowSorter == null) {
            rowSorter = new TableRowSorter<>(table.getModel());
            table.setRowSorter(rowSorter);
        }
        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
    }
}