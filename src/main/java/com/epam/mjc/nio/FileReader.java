package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.nio.channels.Channels;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileReader {
    public Profile getDataFromFile(File file) {
        Path filepath = Paths.get(file.getPath());
        Profile p = new Profile();

        try(BufferedReader bf = Files.newBufferedReader(filepath)) {
            String rawLine = "";
            while((rawLine = bf.readLine()) != null) {
                setProfileAttrs(rawLine, p);
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return p;
    }

    private void setProfileAttrs(String attributeRawLine, Profile p){
        String[] kvPair = attributeRawLine.split(": ");
        String key = kvPair[0];
        String value = kvPair[1];
        if(key.equals("Name")) p.setName(value);
        if(key.equals("Age")) p.setAge(Integer.valueOf(value));
        if(key.equals("Email")) p.setEmail(value);
        if(key.equals("Phone")) p.setPhone(Long.valueOf(value));
    }
}
