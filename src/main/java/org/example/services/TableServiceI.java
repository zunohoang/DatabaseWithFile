package org.example.services;

import org.example.models.Table;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface TableServiceI {
    public boolean isTableExist(String name) throws FileNotFoundException;
    public void createTable(Table table);
    public void deleteTable(String name);
    public void showTable(String name);
    public void addRow(String name, List<String> columns);
    public List<String> findRows(String table, String column, String value);
}
