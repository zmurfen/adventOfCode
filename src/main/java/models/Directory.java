package models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Directory {

    private ArrayList<Integer> files;
    private ArrayList<Directory> directories;
    private String name;
    private Directory superFolder;
    private Integer totalSize;

    public Directory(String name, Directory superFolder) {
        this.files = new ArrayList<>();
        this.directories = new ArrayList<>();
        this.name = name;
        this.superFolder = superFolder;
        this.totalSize = 0;
    }

}