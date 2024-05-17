package org.example;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileSearcher {
    public List<File> searchFiles(String directoryPath) throws IOException {
        List<File> jsonFiles = new ArrayList<>();
        List<Stations> stations = new ArrayList<>();

        File directory = new File(directoryPath);

        searchFilesRecursively(directory, jsonFiles);
        System.out.println("Found JSON files: " + jsonFiles.size());

        ObjectMapper mapper = new ObjectMapper();

        for (File file : jsonFiles) {
            try {
                String fileContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
                Stations[] stationsArray = mapper.readValue(fileContent, Stations[].class);
                stations.addAll(Arrays.asList(stationsArray));
            } catch (JsonParseException e) {
                System.out.println("Error parsing JSON file: " + file.getName() + " Skip");
            } catch (IOException e) {
                System.out.println("Error processing file: " + file.getName());
                e.printStackTrace();
            }
        }


        for(Stations stats : stations){
            System.out.println(stats.toString());
        }

        return jsonFiles;
    }

    private void searchFilesRecursively(File directory, List<File> jsonFiles) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                searchFilesRecursively(file, jsonFiles);
            } else {
                if (file.getName().endsWith(".json")) {
                    jsonFiles.add(file);
                }
            }
        }
    }
}