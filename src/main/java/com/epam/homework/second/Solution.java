package com.epam.homework.second;

import com.epam.homework.second.exception.WrongEmailException;
import com.epam.homework.second.exception.WrongNameException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Solution {

  @SuppressWarnings("All")
  public static void main(String[] args) throws IOException, WrongNameException, WrongEmailException {

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    String fileNameWithNames = null;
    String fileNameWithEmails = null;
    List<String> names = new ArrayList<>();
    List<String> emails = new ArrayList<>();

    try {
      fileNameWithNames = bufferedReader.readLine();
      fileNameWithEmails = bufferedReader.readLine();
      bufferedReader.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    getFileData(fileNameWithNames, names, true);
    getFileData(fileNameWithEmails, emails, false);

    writeDataToFile(names, emails);
  }

  private static final Pattern patternForNames = Pattern.compile("^[A-Za-z]+\\s[A-Za-z]+$");
  private static final Pattern patternForEmails =
      Pattern.compile("^[A-Za-z]+_[A-Za-z]+@(mail|gmail|yandex)\\.(com|ru|net)");

  private static void getFileData(String fileName, List<String> list, boolean isName)
      throws FileNotFoundException, WrongNameException, WrongEmailException {

    File file = new File(fileName);
    Scanner scanner = new Scanner(file);

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line.matches((isName ? patternForNames : patternForEmails).toString())) {
        list.add(line);
      }
      else if (isName) {
        throw new WrongNameException("Incorrect name : " + line);
      }
      else {
        throw new WrongEmailException("Incorrect email : " + line);
      }
    }
    scanner.close();
  }

  private static void writeDataToFile(List<String> names, List<String> emails) {

    try {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
          "./" + "src\\main\\java\\com\\epam\\homework\\second\\result.txt", true
      ));

      for (String name : names) {
        getAndWriteEmailNameMatch(emails, name, bufferedWriter);
      }

      for (String email : emails) {
        bufferedWriter.write("Undefined " + email + "\n");
      }

      bufferedWriter.flush();
      bufferedWriter.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void getAndWriteEmailNameMatch(List<String> emails, String name, BufferedWriter bufferedWriter)
      throws IOException {

    String[] nameParts = name.split("\\s");
    String nameRegex = "^(" + nameParts[0] + ")+_" +
        "(" + nameParts[1] +
        ")+@(mail|gmail|yandex)\\.(com|ru|net)";

    Pattern patternResult = Pattern.compile(nameRegex);
    List<String> tempEmails = new ArrayList<>(emails);
    boolean hasMatch = false;

    Iterator<String> it = tempEmails.iterator();
    while (it.hasNext()) {
      String email = it.next();
      if (email.matches(patternResult.toString())) {
        bufferedWriter.write(name + " " + email + "\n");
        emails.remove(email);
        hasMatch = true;
        break;
      }
    }

    if (!hasMatch) {
      bufferedWriter.write(name + " Undefined" + "\n");
    }
  }
}
