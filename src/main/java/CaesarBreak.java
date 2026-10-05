import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CaesarBreak {
    static int mostFrequentLetter(String text) {
        int[] counts = new int[26];
        for (char ch : text.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                counts[ch - 'A']++;
            } else if (ch >= 'a' && ch <= 'z') {
                counts[ch - 'a']++;
            }
        }
        int best = 0;
        for (int i = 1; i < 26; i++) {
            if (counts[i] > counts[best]) {
                best = i;
            }
        }
        return best;
    }

    static String caesarEncoder(int shift, String text) {
        StringBuilder out = new StringBuilder(text.length());
        for (char ch : text.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                out.append((char) ('A' + Math.floorMod(ch - 'A' + shift, 26)));
            } else if (ch >= 'a' && ch <= 'z') {
                out.append((char) ('a' + Math.floorMod(ch - 'a' + shift, 26)));
            } else {
                out.append(ch);
            }
        }
        return out.toString();
    }

    static String caesarDecoder(int shift, String text) {
        return caesarEncoder(-shift, text);
    }

    public static void main(String[] args) throws IOException {
        int C = mostFrequentLetter(Files.readString(Path.of("big.txt")));
        String plaintext = Files.readString(Path.of("plaintext.txt"));
        String ciphertext = caesarEncoder(C, plaintext);
        int D = mostFrequentLetter(ciphertext);
        int diff = Math.floorMod(D - C, 26);
        String decoded = caesarDecoder(diff, ciphertext);

        System.out.println("C = " + C + ", D = " + D + ", Diff = " + diff + ", decoded equals plaintext: " + decoded.equals(plaintext));
    }
}
