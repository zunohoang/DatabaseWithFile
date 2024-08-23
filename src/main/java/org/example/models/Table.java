package org.example.models;

import java.util.List;
import java.util.Map;

public class Table {
    private String name;
    private List<String> columns;

    public Table(String name, List<String> columns) {
        this.name = name;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }
}
