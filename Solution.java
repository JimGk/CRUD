package com.codegym.task.task17.task1711;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2

*/

public class Solution {

    private static Person person;

    public static volatile List<Person> allPeople = new ArrayList<>();

    static {
        allPeople.add(Person.createMale("Donald Chump", new Date())); 
        allPeople.add(Person.createMale("Larry Gates", new Date()));  
    }



    public static void main(String[] args) throws ParseException {
        SimpleDateFormat date1  = new SimpleDateFormat("MM dd yyyy", Locale.ENGLISH);
        switch (args[0]){
            case "-c" :
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i+=3) {
                        Date birthDate = date1.parse(args[i+2]);
                        if (sex(args[i+1]).equals(Sex.MALE)) {
                            person = Person.createMale(args[i], birthDate);
                        } else if(sex(args[i+1]).equals(Sex.FEMALE)) {
                            person = Person.createFemale(args[i], birthDate);
                        }
                        allPeople.add(person);
                        System.out.println(allPeople.indexOf(person));
                    }
                }
                break;
            case "-u" :
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i += 4) {
                        allPeople.get(Integer.parseInt(args[i])).setName(args[i + 1]);
                        allPeople.get(Integer.parseInt(args[i])).setSex(sex(args[i + 2]));
                        allPeople.get(Integer.parseInt(args[i])).setBirthDate(date1.parse(args[i + 3]));
                    }
                }
                break;
            case "-d" :
                synchronized (allPeople){
                    for ( int i = 1; i < args.length; i++){
                        allPeople.get(Integer.parseInt(args[i])).setName(null);
                        allPeople.get(Integer.parseInt(args[i ])).setSex(null);
                        allPeople.get(Integer.parseInt(args[i])).setBirthDate(null);
                    }
                }
                break;
            case "-i" :
                synchronized (allPeople){
                    for (int i = 1; i < args.length; i++) {
                        printPeople(Integer.parseInt(args[i]));
                    }
                }
                break;
        }
    }
    public static Sex sex(String sex){
        Sex properSex = null;

        if(sex.equals("m")){
            properSex = Sex.MALE;
        }else if(sex.equals("f")){
            properSex = Sex.FEMALE;
        }
        return properSex;
    }
    public static void printPeople(int id) throws ParseException {
        String sex;
        if(allPeople.get(id).getSex().equals(Sex.MALE)){
            sex = "m";
        }else{
            sex = "f";
        }
        Date date = allPeople.get(id).getBirthDate();
        DateFormat properDate = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
        String outputDate = properDate.format(date);

        System.out.println(allPeople.get(id).getName() + " " + sex + " " + outputDate);
    }
}
