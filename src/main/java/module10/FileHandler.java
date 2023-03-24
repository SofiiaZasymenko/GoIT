package module10;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class FileHandler {
    public static final String FILE_PATH = "src/main/java/module10/";

    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        System.out.println("--- Task 1 ---");
        fileHandler.printValidPhoneNumbers(FILE_PATH + "file-1.txt");
        System.out.println("--- Task 2 ---");
        fileHandler.readAndWriteUsers(FILE_PATH + "file-2.txt", FILE_PATH + "user.json");
        System.out.println("--- Task 2 with invalid file ---");
        try {
            fileHandler.readAndWriteUsers(FILE_PATH + "invalid-file.txt", FILE_PATH + "user.json");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("--- Task 3 ---");
        fileHandler.printWordFrequency(FILE_PATH + "words.txt");
    }

    public void printValidPhoneNumbers(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (isPhoneNumberValid(line)) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        Pattern pattern1 = Pattern.compile("\\(\\d\\d\\d\\) \\d\\d\\d-\\d\\d\\d\\d");
        Pattern pattern2 = Pattern.compile("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d");
        return pattern1.matcher(phoneNumber).find() || pattern2.matcher(phoneNumber).find();
    }

    public void readAndWriteUsers(String txtSrcFileName, String jsonDestFileName) {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(txtSrcFileName))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    if (!line.equals("name age")) {
                        throw new IllegalArgumentException("The first line must be 'name age'");
                    }
                } else {
                    String[] wordsInLine = line.split("\\s+");
                    if (isUserValid(wordsInLine)) {
                        User user = new User(wordsInLine[0], Integer.parseInt(wordsInLine[1]));
                        users.add(user);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.toString(users.toArray()));
        try (Writer writer = new FileWriter(jsonDestFileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(users));
            gson.toJson(users, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isUserValid(String[] userRecords) {
        if (userRecords.length != 2) {
            System.err.println("The user record should contain 'name age': " + Arrays.toString(userRecords));
            return false;
        }
        String name = userRecords[0];
        if (name == null || name.isBlank()) {
            System.err.println("The name is not valid: " + name);
            return false;
        }
        String age = userRecords[1];
        if (age == null || age.isBlank()) {
            System.err.println("The age is not valid: " + age);
            return false;
        }
        try {
            int parsedAge = Integer.parseInt(age);
            if (parsedAge < 0) {
                System.err.println("The age must be positive: " + age);
                return false;
            }
        } catch (NumberFormatException e) {
            System.err.println("The age is not valid: " + age);
            return false;
        }
        return true;
    }

    public void printWordFrequency(String fileName) {
        Map<String, Integer> words = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] wordsInLine = line.split("\\s+");
                for (String wordInLine : wordsInLine) {
                    if (words.containsKey(wordInLine)) {
                        words.put(wordInLine, words.get(wordInLine) + 1);
                    } else {
                        words.put(wordInLine, 1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(words.entrySet());
        entries.sort(new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return b.getValue() - a.getValue();
            }
        });

        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
