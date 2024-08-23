package org.example;

import org.example.models.Table;
import org.example.services.TableService;

import java.util.List;

public class Main {

    private static final TableService tableService = new TableService();

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Table table = new Table("table1", List.of("column1", "column2"));
        tableService.createTable(table);
        tableService.addRow("table1", List.of("value1", "value2"));
        tableService.addRow("table1", List.of("value1", "value22"));
        tableService.addRow("table1", List.of("value2", "value2"));
        System.out.println("Show table: ");
        tableService.showTable("table1");

        List<String> rows = tableService.findRows("table1", "column1", "value1");
        System.out.println("Find rows: ");
        for(String row: rows) {
            System.out.println(row);
        }
    }

}