package me.ciu.o.pattern;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    public static class Person {
        private String name;
        private String gender;

        public Person(String name, String gender) {
            this.name = name;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        @Override
        public String toString() {
            return "Person{" + "name='" + name + '\'' + ", gender='" + gender + '\'' + '}';
        }
    }

    public interface Criteria {
        public List<Person> meetCriteria(List<Person> persons);
    }

    public static class CriteriaFemale implements Criteria {
        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> femalePersons = new ArrayList<Person>();
            for (Person person : persons) {
                if (person.getGender().equalsIgnoreCase("FEMALE")) {
                    femalePersons.add(person);
                }
            }
            return femalePersons;
        }
    }

    public static class CriteriaMale implements Criteria {
        @Override
        public List<Person> meetCriteria(List<Person> persons) {
            List<Person> malePersons = new ArrayList<Person>();
            for (Person person : persons) {
                if (person.getGender().equalsIgnoreCase("MALE")) {
                    malePersons.add(person);
                }
            }
            return malePersons;
        }
    }

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("Robert", "Male"));
        persons.add(new Person("John", "Male"));
        persons.add(new Person("Laura", "Female"));
        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        System.out.println("Males: ");
        printPersons(male.meetCriteria(persons));
        System.out.println("\nFemales: ");
        printPersons(female.meetCriteria(persons));
    }

    public static void printPersons(List<Person> persons) {
        for (Person person : persons) {
            System.out.println(person.toString());
        }
    }
}