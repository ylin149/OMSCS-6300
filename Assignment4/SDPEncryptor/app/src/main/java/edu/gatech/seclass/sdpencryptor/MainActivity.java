package edu.gatech.seclass.sdpencryptor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private int ASCIILOWERCASEA = 97;

    private Button encryptButton;
    private EditText messageEditText;
    private EditText alphaEditText;
    private EditText betaEditText;
    private TextView encryptedMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        encryptButton = findViewById(R.id.encryptButtonID);
        messageEditText = findViewById(R.id.messageID);
        alphaEditText = findViewById(R.id.alphaValueID);
        betaEditText = findViewById(R.id.betaValueID);
        encryptedMessageTextView = findViewById(R.id.encryptedMessageID);
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encryptButtonClickEvent();
            }
        });
    }

    private void encryptButtonClickEvent() {
        boolean isMessageValid = isMessageValid();
        boolean isAlphaValid = isAlphaValid();
        boolean isBetaValid = isBetaValid();
        if(isMessageValid && isAlphaValid && isBetaValid) {
            String message = getMessage();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < message.length(); i++) {
                char letter = message.charAt(i);
                boolean isLowerCase = Character.isLowerCase(letter) ? true : false;
                if (Character.isLetter(letter)) {
                    sb.append(computeAffineCypher(letter, isLowerCase));
                } else {
                    sb.append(letter);
                }
            }
            String encryptedMessage = sb.toString();
            encryptedMessageTextView.setText(encryptedMessage);
        }
    }

    private boolean isMessageValid(){
        String message = getMessage();
        if(message == null || message.isEmpty() ||
                !message.matches(".*[a-zA-Z]+.*")) {
            messageEditText.setError("Invalid Message");
            return false;
        }
        return true;
    }

    private boolean isAlphaValid(){
        String stringAlpha = alphaEditText.getText().toString();
        if(stringAlpha == null || stringAlpha.isEmpty()){
            alphaEditText.setError("Invalid Alpha Value");
            return false;
        }
        Set<Integer> alphaValues = new HashSet<Integer>
                (Arrays.asList(1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23,25));
        try{
            if(!alphaValues.contains(getIntegerAlpha())){
                alphaEditText.setError("Invalid Alpha Value");
                return false;
            }
        }
        catch (Exception e){
            alphaEditText.setError("Invalid Alpha Value");
            return false;
        }
        return true;
    }

    private boolean isBetaValid(){
        String stringBeta = betaEditText.getText().toString();
        if(stringBeta == null || stringBeta.isEmpty()){
            betaEditText.setError("Invalid Beta Value");
            return false;
        }
        try{
            Integer betaValue = getIntegerBeta();
            if(betaValue < 1 || betaValue >= 26){
                betaEditText.setError("Invalid Beta Value");
                return false;
            }
        }
        catch (Exception e){
            betaEditText.setError("Invalid Beta Value");
            return false;
        }
        return true;
    }

    private String getMessage(){
        return messageEditText.getText().toString();
    }
    private char computeAffineCypher(char letter, boolean isLowerCase){
       char lowerCaseChar = Character.toLowerCase(letter);
       int x = computeCypherFromInteger(lowerCaseChar);
       int affineValue = (getIntegerAlpha() * x + getIntegerBeta()) % 26;
       return computeCharacterFromAffine(affineValue, isLowerCase);
    }

    private Integer getIntegerAlpha(){
        return Integer.valueOf(alphaEditText.getText().toString());
    }
    private Integer getIntegerBeta(){
        return Integer.valueOf(betaEditText.getText().toString());
    }

    private int computeCypherFromInteger(int letter){
        return letter - ASCIILOWERCASEA;
    }

    private char computeCharacterFromAffine(int affineValue, boolean isLowerCase){
        int decryptLetter = affineValue + ASCIILOWERCASEA;
        char letterValue = (char)decryptLetter;
        return isLowerCase? Character.toUpperCase(letterValue) : Character.toLowerCase(letterValue);

    }



}