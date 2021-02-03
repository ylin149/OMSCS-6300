package edu.gatech.seclass;
import java.util.Arrays;

public class MyCustomString implements MyCustomStringInterface{
    private String string;

    @java.lang.Override
    public String getString() {
        return string;
    }

    @java.lang.Override
    public void setString(String string) {
        this.string = string;
    }

    @java.lang.Override
    public int countStrings() {
        if (this.getString() == null){
            throw new NullPointerException();
        }
        if(this.getString().isEmpty() || this.getString().isBlank()){
            return 0;
        }
        String[] words = this.getString().split("\\P{Alpha}+");
        int count = 0;
        for(String word: words){
            if(!word.equals("")){
                count++;
            }
        }
        return count;
    }

    @java.lang.Override
    public String multiplyNumber(int n, boolean reverse) {
        if (string == null){
            throw new NullPointerException();
        }
        if(n <= 0){
            throw new IllegalArgumentException();
        }

        if(getString().isEmpty()){
            return getString();
        }
        String numbersOnly = getString().replaceAll("[^0-9]+", " ");

        if(numbersOnly.equals("")){
            return getString();
        }
        String[] numbersToArray = numbersOnly.trim().split(" ");

        if(numbersToArray.length == 0){
            return getString();
        }
        int lastIndex = 0;
        StringBuilder finalResult = new StringBuilder();
        for(String number: numbersToArray){
            if(number.equals("")){
                continue;
            }
            int index = getString().indexOf(number, lastIndex);
            Integer intValue = Integer.valueOf(number);
            StringBuilder stringNumber = new StringBuilder();
            if(n != 1){
                intValue *= n;
                stringNumber.append(intValue);
                if(reverse){
                    stringNumber.reverse();
                }
            }
            else{
                if(intValue >= 10){
                    stringNumber.append(intValue);
                    if(reverse){
                        stringNumber.reverse();
                    }
                }
                else {
                    stringNumber.append(number);
                }
            }
            finalResult.append(getString(), lastIndex, index).append(stringNumber);
            lastIndex = index + number.length();
        }
        finalResult.append(getString(), lastIndex, getString().length());
        return finalResult.toString();
    }
    @java.lang.Override
    public void convertDigitsToBinaryInSubstring(int startPosition, int endPosition) {
        if(this.getString() == null){
            throw new NullPointerException();
        }
        if(startPosition < 1 || startPosition > endPosition){
            throw new IllegalArgumentException();
        }
        if(endPosition - 1 >= this.getString().length()){
            throw new MyIndexOutOfBoundsException();
        }
        StringBuilder finalResult = new StringBuilder();
        for(int i = 0 ; i <= startPosition - 2 ; i++){
            finalResult.append(this.getString().charAt(i));
        }
        for(int i = startPosition - 1 ; i < endPosition; i++){
            char currentCharacter = this.getString().charAt(i);
            if(currentCharacter >= '0' && currentCharacter <= '9'){
                finalResult.append(this.digitToBinary(currentCharacter));
            }
            else{
                finalResult.append(currentCharacter);
            }
        }
        for(int i = endPosition ; i < this.getString().length(); i++){
            finalResult.append(this.getString().charAt(i));
        }
        this.setString(finalResult.toString());
    }
    private String digitToBinary(char digit){
        String binary = "0000";
        switch (digit){
            case '1':
                binary = "0001";
                break;
            case '2':
                binary = "0010";
                break;
            case '3':
                binary = "0011";
                break;
            case '4':
                binary = "0100";
                break;
            case '5':
                binary = "0101";
                break;
            case '6':
                binary = "0110";
                break;
            case '7':
                binary = "0111";
                break;
            case '8':
                binary = "1000";
                break;
            case '9':
                binary = "1001";
                break;
        }
        return binary;
    }
}
