package edu.gatech.seclass.textmodifier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class TextModifier implements TextModifierInterface{
    private String filePath;
    private boolean s;
    private boolean l;
    private String rString;
    private String kString;
    private int tInt;
    private Set<String> argOrder;
    private List<String> fileContent;

    public TextModifier(){
        initMembers();
    }

    private void initMembers(){
        this.filePath =  null;
        this.s = false;
        this.l = false;
        this.rString = null;
        this.kString = null;
        this.tInt = 0;
        argOrder = new LinkedHashSet<>();
        fileContent = new ArrayList<>();
    }

    @Override
    public void setFilepath(String filepath) {
        this.filePath = filepath;
    }

    @Override
    public void setS(boolean s) {
        this.s = s;
        if(this.s) argOrder.add("s");
    }

    @Override
    public void setL(boolean l) {
        this.l = l;
    }

    @Override
    public void setRString(String rString) throws TextModifierException {
        if(rString == null) throw new TextModifierException("The argument of r cannot be null");
        if(this.kString!= null) throw new TextModifierException("Options r and k are mutually exclusive");
        argOrder.add("r");
        this.rString = rString;
    }

    @Override
    public void setKString(String kString) throws TextModifierException {
        if(kString == null) throw new TextModifierException("The argument of k cannot be null");
        if(this.rString != null) throw new TextModifierException("Options r and k are mutually exclusive");
        argOrder.add("k");
        this.kString = kString;
    }

    @Override
    public void setTInt(int tInt) throws TextModifierException {
        if(tInt <= 0) throw new TextModifierException("The argument of t must be greater than 0");
        this.tInt = tInt;
    }


    @Override
    public void process() throws TextModifierException {
        if (filePath == null) throw new TextModifierException("No filename provided");
        if (filePath.isEmpty() || filePath.isBlank()) throw new TextModifierException("Invalid filename");

        String textLine, lastLine = "";
        Path outputFile = Paths.get(this.filePath);

        //Read the input file
        try (Scanner fileScanner = new Scanner(Paths.get(this.filePath))) {
            while ((textLine = fileScanner.findWithinHorizon(Pattern.compile(".*\\R|.+\\z"), 0)) != null) {
                lastLine = textLine;
                fileContent.add(textLine);
            }
        } catch (Exception e) {
            throw new TextModifierException("Cannot read file: " + filePath);
        }

        //Corner case: The file that only contains end of line
        if(fileContent.size() == 1
                && (fileContent.get(fileContent.size() -1).equals(System.lineSeparator())
                || fileContent.get(fileContent.size() -1).isEmpty())){
            //Write the output file
            try (BufferedWriter writer = Files.newBufferedWriter(outputFile)) {
                StringBuilder sb = new StringBuilder();
                writer.write("");
            } catch (IOException e) {
                throw new TextModifierException("Cannot write output file: " + filePath);
            }
            return;
        }

        //Transform the input file
        for (String argKey : argOrder) {
            if (this.s && argKey.equals("s")) applySortTransformation();
            if (this.rString != null && argKey.equals("r")) applyRemoveTransformation();
            if (kString!= null && argKey.equals("k")) applyKeepTransformation();
        }

        //Trimming Transformation is applied after the remove/keep and also the sort
        if (tInt > 0) applyRemoveCharsTransformation();
        //The number line transformation is always applied at the end if present
        if (l) applyNumberLinesTransformation();

        //Write the output file
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile)) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < this.fileContent.size() ; i++){
                String line = fileContent.get(i);
                sb.append(line);
                if(i + 1 < this.fileContent.size() && !line.endsWith(System.lineSeparator())){
                    sb.append(System.lineSeparator());
                }
            }
            if(!fileContent.isEmpty()) {
                String lastTLine = fileContent.get(fileContent.size() - 1);
                if (lastLine.endsWith(System.lineSeparator()) && !lastTLine.endsWith(System.lineSeparator())) {
                    sb.append(System.lineSeparator());
                }
                if (sb.length() >= 1 && !lastLine.endsWith(System.lineSeparator()) && lastTLine.endsWith(System.lineSeparator())) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
            writer.write(sb.toString());
        } catch (IOException e) {
            throw new TextModifierException("Cannot write output file: " + filePath);
        }

        initMembers();
    }

    //Text Transformations Start Here:
    private void applySortTransformation(){
        List<String> tempList = new ArrayList<>();
        for(String line: fileContent){
            for(char ch: line.toCharArray()){
                if((ch >='A' && ch <= 'Z') || (ch >='0' && ch <= '9') || (ch >='a' && ch <= 'z')){
                    tempList.add(line);
                    break;
                }
            }
        }
        if(!tempList.isEmpty()) {
            Collections.sort(tempList, new SortFileLines());
        }
        this.fileContent = new ArrayList<>(tempList);
    }

    private void applyRemoveTransformation(){
        List<String> tempList = new ArrayList<>();
        for(String line: fileContent){
            if(line.indexOf(rString) == -1) {
                tempList.add(line);
            }
        }
        this.fileContent = new ArrayList<>(tempList);
    }

    private void applyKeepTransformation(){
        List<String> tempList = new ArrayList<>();
        for(String line: fileContent){
            if(line.indexOf(kString) != -1) {
                tempList.add(line);
            }
        }
        this.fileContent = new ArrayList<>(tempList);
    }

    private void applyRemoveCharsTransformation(){
        List<String> tempList = new ArrayList<>();
        for(String line: fileContent){
            if (tInt <= 0 || tInt >= line.length()) {
                tempList.add(line);
                continue;
            }
            String charactersToKeep = line.substring(0, tInt);
            String lastLineCharacter = Character.toString(line.toCharArray()[line.length() - 1]);
            if(lastLineCharacter.equals(System.lineSeparator())){
                charactersToKeep += lastLineCharacter;
            }
            tempList.add(charactersToKeep);
        }
        this.fileContent = new ArrayList<>(tempList);
    }

    private void applyNumberLinesTransformation(){
        List<String> tempList = new ArrayList<>();
        for(int i = 0 ; i < fileContent.size(); i++){
            String line = fileContent.get(i);
            StringBuilder sb = new StringBuilder();
            sb.append(i + 1).append(" ").append(line);
            tempList.add(sb.toString());
        }
        this.fileContent = new ArrayList<>(tempList);
    }
}