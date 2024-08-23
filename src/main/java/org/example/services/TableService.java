package org.example.services;

import org.example.models.Table;

import java.io.*;
import java.util.*;

public class TableService implements TableServiceI {
    @Override
    public boolean isTableExist(String name) {
        try {
            String fileName = name + ".txt";
            InputStream inputStream = new FileInputStream(fileName);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    @Override
    public void createTable(Table table) {
        BufferedOutputStream bufferedOutputStream = null;

        try {

            // lay duong dan du an
            String userDirectoryPath = System.getProperty("user.dir");
            String urlFile = userDirectoryPath + "/src/main/java/org/example/databases/" + File.separator + table.getName() + ".txt";
            File file = new File(userDirectoryPath + "/src/main/java/org/example/databases/" + File.separator + table.getName() + ".txt");

            // tao file
            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }

            OutputStream outputStream = new FileOutputStream(urlFile);
            bufferedOutputStream = new BufferedOutputStream(outputStream);

            // ghi du lieu vao file
            for(String column: table.getColumns()) {
                bufferedOutputStream.write(column.getBytes());
                bufferedOutputStream.write(" , ".getBytes());
            }
            bufferedOutputStream.write(" | \n".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void deleteTable(String name) {
        try {
            // lay duong dan du an
            String userDirectoryPath = System.getProperty("user.dir");
            File file = new File(userDirectoryPath + "/src/main/java/org/example/databases/" + File.separator + name + ".txt");

            if (file.delete()) {
                System.out.println("Deleted the file: " + file.getName());
            } else {
                System.out.println("Failed to delete the file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showTable(String name) {
        Scanner sc = null;
        try {
            // lay duong dan du an
            String userDirectoryPath = System.getProperty("user.dir");
            sc = new Scanner(new File(userDirectoryPath + "/src/main/java/org/example/databases/" + File.separator + name + ".txt"));

            while (sc.hasNext()) {
                String column = sc.next();
                if(column.equals("|")) {
                    System.out.println();
                    continue;
                }
                System.out.print(column);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRow(String name, List<String> columns) {
        BufferedOutputStream bufferedOutputStream = null;

        try {
            // lay duong dan du an
            String userDirectoryPath = System.getProperty("user.dir");
            String urlFile = userDirectoryPath + "/src/main/java/org/example/databases/" + File.separator + name + ".txt";

            OutputStream outputStream = new FileOutputStream(urlFile, true);
            bufferedOutputStream = new BufferedOutputStream(outputStream);

            // ghi du lieu vao file
            for(String column: columns) {
                bufferedOutputStream.write(column.getBytes());
                bufferedOutputStream.write(" , ".getBytes());
            }
            bufferedOutputStream.write(" | \n".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<String> findRows(String tableName, String column, String value) {
        List<String> results = new ArrayList<>();

        String userDirectoryPath = System.getProperty("user.dir");
        String urlFile = userDirectoryPath + "/src/main/java/org/example/databases/" + File.separator + tableName + ".txt";

        try {
            Scanner sc = new Scanner(new File(urlFile));
            int index = 0;
            int key = 0;
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] columns = line.split(" , ");
                if(index == 0){
                    for(int i = 0; i < columns.length; i++){
                        if(columns[i].equals(column)){
                            key = i;
                            break;
                        }
                    }
                } else {
                    if(columns[key].equals(value)){
                        results.add(line);
                    }
                }
                index++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return results;
    }
}
