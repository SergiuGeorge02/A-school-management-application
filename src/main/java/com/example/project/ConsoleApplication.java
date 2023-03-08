package com.example.project;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleApplication {

    public void information(){
        System.out.println("This application is meant to be used by the secretary department of the FIPS Univeristy");
        System.out.println("This application works in two ways selected from the configuration file");
        System.out.println("If the configuration selected is FileApplication details about the students will be stored in a file");
        System.out.println("If the configuration chosen is DatabaseApplication details about students will be stored in a mysql database");
        System.out.println("This gives the user different ways in which he wants to use the program, but keep in mind that they are not connected in between");
        System.out.println("The information stored in the folder might not be found in the database also");
        System.out.println("When the user chooses the application format he prefers he should stick to it");
    }
    public void backup1(Map<Person,Department> database1){

        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter("databasebackup.txt"));
            for(Map.Entry<Person,Department> entry:database1.entrySet())
                writer.write(entry.getKey()+" "+entry.getValue()+"\n");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
//    public int last_id(){
//        int id=0;
//        try {
//
//            BufferedReader reader=new BufferedReader(new FileReader("databasebackup.txt"));
//            String read;
//            while((read=reader.readLine())!=null){
//                String y=read.replace("ID: ","");
//                String s=y.replace("Student: ","");
//                String f=s.replace("has age: ","");
//                String x=f.replace("gender: ","");
//                String finals=x.replace(" and is enrolled at department:","");
//                String[] person=new String[finals.length()];
//                person=finals.split(" ");
//                String name=person[1];
//
//                 id= Integer.parseInt(person[0]);
//
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return id;
//    }
    public int last_id(){
        int id = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("databasebackup.txt"));
            String read;
            while((read = reader.readLine()) != null){
                String y=read.replace("ID: ","");
                String s=y.replace("Student: ","");
                String f=s.replace("has age: ","");
                String x=f.replace("gender: ","");
                String finals=x.replace(" and is enrolled at department:","");
                String[] person=new String[finals.length()];
                person=finals.split(" ");
                String name=person[1];

                id= Integer.parseInt(person[0]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file databasebackup.txt was not found. Please check the file name and path.");
            System.exit(1);
        } catch (IOException e) {

            System.out.println("An error occurred while reading the file databasebackup.txt");
            System.out.println("Please check if the file exists and it can be opened");
            System.exit(1);
        }
        return id;
    }
    public boolean check_database1(Person student, Map<Person,Department> database1){
        for(Map.Entry<Person,Department> entry:database1.entrySet()){
            Person p=entry.getKey();
            if(p.compareTo(student)==0)
                return true;
        }
        return false;
    }
    public Map<Person, Department> recover_map(){
        Map<Person,Department> recover=new TreeMap<>();
        try {
            int id=0;
            BufferedReader reader=new BufferedReader(new FileReader("databasebackup.txt"));
            String read;
            while((read=reader.readLine())!=null){
                String y=read.replace("ID: ","");
                String s=y.replace("Student: ","");
                String f=s.replace("has age: ","");
                String x=f.replace("gender: ","");
                String finals=x.replace(" and is enrolled at department:","");
                String[] person=new String[finals.length()];
                person=finals.split(" ");
                String name=person[1]+" "+person[2];

                int age= Integer.parseInt(person[3]);
                id= Integer.parseInt(person[0]);

                Gender b=Gender.Male;
                if(person[4].equals("Male")){
                    b=Gender.Male;
                }
                else
                    b=Gender.Female;
                Person student=new Person(name,age,b,id);
                Department d=new Department("");
                if(person[5].equals("Art")){
                    List<String> subjects=new ArrayList<>();
                    subjects.add("Graphic Design");
                    subjects.add("3D Design");
                    subjects.add("Drawing");
                    subjects.add("Painting");
                    Department back=new Art(subjects);
                    d=back;
                }
                else if(person[4].equals("Computers"))
                {
                    List<String> subjects=new ArrayList<>();
                    subjects.add("Algorithms");
                    subjects.add("Operating Systems");
                    subjects.add("Programming");
                    subjects.add("Computer Architecture");
                    Department c=new Computer(subjects);
                    d=c;
                }
                else if(person[4].equals("Science")){
                    List<String> subjects=new ArrayList<>();
                    subjects.add("Biology");
                    subjects.add("Geography");
                    subjects.add("Physics");
                    subjects.add("Chemistry");
                    Department science=new Science(subjects);
                    d=science;
                }
                else{
                    List<String> subjects=new ArrayList<>();
                    subjects.add("How to read a book");
                    subjects.add("The beautiful part of reading");
                    subjects.add("The correct way to write a book");
                    Department literature=new Literature(subjects);
                    d=literature;
                }
                recover.put(student,d);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return recover;
    }




}


