package IO;

public class Decrypt {

    public String decryptData(String encryptedData, int shiftKey){
        StringBuilder decryptedData = new StringBuilder();
        for (int i = 0; i < encryptedData.length(); i++) {
            char currentChar = encryptedData.charAt(i);

            if (Character.isLetter(currentChar)) {
                char shiftedChar = (char) (currentChar - shiftKey);

                if (Character.isUpperCase(currentChar)) {
                    if (shiftedChar < 'A') {
                        shiftedChar = (char) (shiftedChar + 26);
                    }
                } else if (Character.isLowerCase(currentChar)) {
                    if (shiftedChar < 'a') {
                        shiftedChar = (char) (shiftedChar + 26);
                    }
                }
                decryptedData.append(shiftedChar);
            } else if (currentChar == ' ') {
                decryptedData.append(' ');
            } else if (currentChar == '\n') {
                decryptedData.append('\n');
            } else {

                decryptedData.append(currentChar);
            }
        }
        return decryptedData.toString();
    }

}