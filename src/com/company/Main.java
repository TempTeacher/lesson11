package com.company;

import com.company.entity.Person;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //0

        //System.out.
        //System.in

        //1
        FileInputStream inputStream = new FileInputStream("store/file1.txt");
        FileOutputStream outputStream = new FileOutputStream("store/file2.txt");

        while (inputStream.available() > 0)
        {
            int data = inputStream.read();
            outputStream.write(data);
        }

        inputStream.close();
        outputStream.close();


        //
        Reader r = new FileReader("store/file1.txt");
        Writer w = new FileWriter("store/file3.txt");
        int c;
        while ((c = r.read()) != -1) {
            w.write(c);
        }
        r.close();
        w.close();

        //buff
        inputStream = new FileInputStream("store/file1.txt");
        outputStream = new FileOutputStream("store/file4.txt");

        byte[] buffer = new byte[1000]; //<- any number
        while (inputStream.available() > 0)
        {
            int count = inputStream.read(buffer); // <- number of left bytes
            outputStream.write(buffer, 0, count);
        }

        inputStream.close();
        outputStream.close();

        //
        try (BufferedReader reader = new BufferedReader(new FileReader("store/file1.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("store/file5.txt"))) {
                String line;
                while (!(line = reader.readLine()).equals("exit")) {
                    writer.write(line);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //
        File directory = new File("store");
        System.out.println(Arrays.toString(directory.list()));


        //
        Files.lines(Paths.get("store/file4.txt")); //!! after collections and java 8

        //2
        Scanner scanner = new Scanner(System.in);
        Scanner sc = new Scanner(new File("store/file1.txt"));

        while (sc.hasNextLine()) {
            String word = sc.next();
            System.out.print(word + " ");
        }
        sc.close();

        // 2

        //1
        String str = new String(Files.readAllBytes(Paths.get("store/file1.txt")));

        //2
        outputStream = new FileOutputStream("store/file7.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(new Person("name", 10));
        objectOutputStream.close();

        //
        FileInputStream fileInputStream = new FileInputStream("store/file7.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Person person = (Person) objectInputStream.readObject();
        System.out.println(person);
    }
}
