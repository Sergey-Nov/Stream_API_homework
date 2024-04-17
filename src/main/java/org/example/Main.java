package org.example;

import org.example.model.*;
import org.example.util.Util;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
//        task1();
//        task2();
//        task3();
//        task4();
//        task5();
//        task6();
//        task7();
//        task8();
//        task9();
//        task10();
//        task11();
//        task12();
//        task13();
        task14();
        task15();
    }

    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() > 10 && animal.getAge() < 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip(14)
                .limit(7)
                .forEach(System.out::println);
    }

    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getOrigin().equals("Japanese"))
                .map(animal -> animal.getGender().equals("Female") ? animal.getBread().toUpperCase() : animal.getBread())
                .forEach(System.out::println);
    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() > 30)
                .filter(animal -> animal.getOrigin().startsWith("A"))
                .distinct()
                .forEach(System.out::println);
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        long result = animals.stream()
                .filter(animal -> animal.getGender().equals("Female"))
                .count();
        System.out.println(result);
    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        boolean result = animals.stream()
                .filter(animal -> animal.getAge() > 20 && animal.getAge() < 30)
                .anyMatch(animal -> animal.getOrigin().equals("Hungarian"));
        System.out.println(result);
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .allMatch(animal -> animal.getGender().equals("Male") && animal.getGender().equals("Female")));
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .noneMatch(animal -> animal.getOrigin().equals("Oceania")));
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .max(Comparator.comparing(Animal::getAge)));
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .map(animal -> Arrays.toString(animal.getBread().toCharArray()))
                .min(Comparator.comparingInt(String::length)));

    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .map(Animal::getAge)
                .reduce(0, Integer::sum));
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream()
                .filter(animal -> animal.getOrigin().equals("Indonesian"))
                .mapToInt(Animal::getAge)
                .peek(System.out::println)
                .average()
                .getAsDouble());
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
        people.stream()
                .filter(person -> "male".equals(person.getGender().toLowerCase()))
                .filter(person -> LocalDate.now().minusYears(18).plusDays(1).isAfter(person.getDateOfBirth()) &&
                        LocalDate.now().minusYears(30).plusDays(1).isBefore(person.getDateOfBirth()))
                .sorted((o1, o2) -> o1.getRecruitmentGroup(1) - o2.getRecruitmentGroup(2))
                .limit(200)
                .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        Predicate<House> hospitalPredicate = house -> "Hospital".equals(house.getBuildingType());
        Function<House, Stream<? extends Person>> houseStreamToPersonFunction = house -> house.getPersonList().stream();
        Function<House, Stream<? extends Person>> houseStreamToPersonWithFilterYearFunction = house -> house.getPersonList().stream()
                .filter(person -> LocalDate.now().minusYears(18).plusDays(1).isBefore(person.getDateOfBirth())
                        |LocalDate.now().minusYears(65).plusDays(1).isAfter(person.getDateOfBirth()));
        Stream.concat(
                        houses.stream().filter(hospitalPredicate)
                                .flatMap(houseStreamToPersonFunction),
                        houses.stream().flatMap(houseStreamToPersonWithFilterYearFunction))
                .distinct()
                .limit(500)
                .forEach(System.out::println);
    }

    private static void task14() throws IOException {
        List<Car> cars = Util.getCars();
        Map<String,Car> filteredCar = new HashMap<>(1000);
        cars.stream()
                .filter(car -> "Jaguar".equals(car.getCarMake())||"White".equals(car.getColor()))
                .toList()
                .forEach(car ->filteredCar.put("1.T",car));
        System.out.println(filteredCar);
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
    }

}