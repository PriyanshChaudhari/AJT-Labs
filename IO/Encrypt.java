package IO;

public class Encrypt {

    public String encryptData(String data, int shiftKey){
        StringBuilder encryptedData = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            // Shift each byte by the shiftKey
            //encryptedData[i] = () (data[i] + shiftKey);
            char currentChar = data.charAt(i);

            if (Character.isLetter(currentChar)){
                char shiftedChar = (char) (currentChar + shiftKey);
                if ((Character.isUpperCase(currentChar) && shiftedChar > 'Z')
                        || (Character.isLowerCase(currentChar) && shiftedChar > 'z')) {
                    shiftedChar = (char) (currentChar - (26 - shiftKey));
                }
                encryptedData.append(shiftedChar);
            }
            else if (currentChar == ' ') {
                encryptedData.append(' ');
            } else if (currentChar == '\n') {
                encryptedData.append('\n');
            } else {

                encryptedData.append(currentChar);
            }
        }
        return encryptedData.toString();
    }

}
