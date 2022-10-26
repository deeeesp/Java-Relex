package ru.stazaev.site.controller;


import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileConverter {

    public List<Integer> readFromFile(String path){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new java.io.FileReader(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String str;

        ArrayList<Integer> lines = new ArrayList<Integer>();
        while(true){
            try {
                if ((str = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(!str.isEmpty()){
                lines.add(Integer.valueOf(str));
            }}
        return lines;
    }

    public void writeToFile(String path, Object object){
        try(FileWriter writer = new FileWriter(path))
        {
            writer.write(object.toString());
            writer.flush();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
