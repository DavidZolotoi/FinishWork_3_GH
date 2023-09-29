package org.example.controller;

import java.sql.*;
import java.util.ArrayList;

/**
 * Класс команд для животных
 */
class Command{

    /**
     * Имя животного
     */
    private final String name;

    /**
     * Класс команд для животных
     * @param name имя животного
     */
    public Command(String name) {
        this.name = name;
        System.out.println("--log: создана команда " + name);
    }

    /**
     * Выполнить команду
     */
    protected void execute(){
        System.out.println("--log: команда " + name + " исполнена.");
    }
}

/**
 * Класс с данными
 */
class DataInfo {
    /**
     * Количество животных в реестре
     */
    private static int animalCount;

    /**
     * Получить количество животных в реестре
     * @return количество животных в реестре
     */
    protected static int getAnimalCount() {
        return animalCount;
    }
    /**
     * Установить количество животных в реестре
     */
    protected static void setAnimalCount(int animalCount) {
        DataInfo.animalCount = animalCount;
    }
    /**
     * Увеличить количество животных в реестре на 1
     */
    protected static void increaseAnimalCount() {
        DataInfo.animalCount++;
    }
    /**
     * Увеличить количество животных в реестре на заданное количество
     * @param i количество, на которое надо увеличить число животных в реестре
     */
    protected static void increaseAnimalCount(int i) {
        DataInfo.animalCount += i;
    }
}

/**
 * Абстрактный класс Животное
 */
abstract class Animal{
    /**
     * Номер животного в общем реестре
     */
    protected Integer number;
    /**
     * Группа животных: домашние, вьючные и т.п.
     */
    protected String group;
    /**
     * Вид животного: кошки, собаки, лошади, ослы и т.п.
     */
    protected String type;
    /**
     * Имя животного
     */
    protected String name;
    /**
     * День рождения животного
     */
    protected Date birthday;
    /**
     * Все команды животного одной строкой
     */
    protected String commandsReport;
    /**
     * Коллекция команд животного
     */
    protected ArrayList<Command> commands;

    /**
     * Животное
     * @param number Номер животного в общем реестре
     * @param group Группа животных: домашние, вьючные и т.п.
     * @param type Вид животного: кошки, собаки, лошади, ослы и т.п.
     * @param name Имя животного
     * @param birthday День рождения животного
     * @param commandsReport Все команды животного одной строкой
     * @param commands Коллекция команд животного
     */
    public Animal(Integer number, String group, String type, String name, Date birthday, String commandsReport, ArrayList<Command> commands) {
        this.number = number;
        this.group = group;
        this.type = type;
        this.name = name;
        this.birthday = birthday;
        this.commandsReport = commandsReport;
        this.commands = commands;
        DataInfo.increaseAnimalCount();
        System.out.println("--log: создана основа животного " + this.name + ". Количество животных увеличено и = " + DataInfo.getAnimalCount());
    }

    /**
     * Обучение новой команде
     * @param newCommandName название новой команды для изучения
     */
    protected void addNewCommand(String newCommandName){
        this.commands.add(new Command(newCommandName));
        this.commandsReport += ", " + newCommandName;
        System.out.println("--log: животное " + this.name + " обучено новой команде " + newCommandName);
    }

    /**
     * Вернуть всю инфу о животном
     * @return всю инфу о животном
     */
    protected String getInfo() {
        System.out.println("Привет, меня зовут " + name + ".");
        return
                "Номер: " + this.number + ";" +
                "Группа: " + this.group + ";" +
                "Тип: " + this.type + ";" +
                "Имя: " + this.name + ";" +
                "День рождения: " + this.birthday + ";" +
                "Команды: " + this.commands + ".";
    }
}

/**
 * Животное группы "Домашние животные" наследуется от Animal
 */
abstract class Pet extends Animal {
    /**
     * Наименование группы животных - домашние
     */
    protected final String group = AnimalGroups.Pets;

    /**
     * Животное группы "Домашние животные" наследуется от Animal
     * @param number Номер животного в общем реестре
     * @param group Группа животных - домашние животные
     * @param type Вид животного: кошки, собаки, лошади, ослы и т.п.
     * @param name Имя животного
     * @param birthday День рождения животного
     * @param commandsReport Все команды животного одной строкой
     * @param commands Коллекция команд животного
     */
    public Pet(Integer number, String group, String type, String name, Date birthday, String commandsReport, ArrayList<Command> commands) {
        super(number, group, type, name, birthday, commandsReport, commands);
        System.out.println("--log: определена принадлежность животного к группе домашних животных");
    }
}

/**
 * Животное группы "Вьючные животные" наследуется от Animal
 */
abstract class PackAnimal extends Animal {
    /**
     * Наименование группы животных - вьючные
     */
    protected final String group = AnimalGroups.PackAnimals;

    /**
     * Животное группы "Вьючные животные" наследуется от Animal
     * @param number Номер животного в общем реестре
     * @param group Группа животных - вьючные
     * @param type Вид животного: кошки, собаки, лошади, ослы и т.п.
     * @param name Имя животного
     * @param birthday День рождения животного
     * @param commandsReport Все команды животного одной строкой
     * @param commands Коллекция команд животного
     */
    public PackAnimal(Integer number, String group, String type, String name, Date birthday, String commandsReport, ArrayList<Command> commands) {
        super(number, group, type, name, birthday, commandsReport, commands);
        System.out.println("--log: определена принадлежность животного к группе вьючных животных");
    }

}

/**
 * Вид домашних животных - собаки
 */
class Dog extends Pet {
    /**
     * Вид животного - собака
     */
    protected final String type = AnimalTypes.Dog;

    /**
     * Подтип домашних животных - собаки
     * @param number Номер животного в общем реестре
     * @param group Группа животных - домашние животные
     * @param type Вид животного - собака
     * @param name Имя животного
     * @param birthday День рождения животного
     * @param commandsReport Все команды животного одной строкой
     * @param commands Коллекция команд животного
     */
    public Dog(Integer number, String group, String type, String name, Date birthday, String commandsReport, ArrayList<Command> commands) {
        super(number, group, type, name, birthday, commandsReport, commands);
        System.out.println("--log: определена принадлежность животного к виду собак");
    }
}

/**
 * Вид домашних животных - кошки
 */
class Cat extends Pet {
    /**
     * Вид животного - кошка
     */
    protected final String type = AnimalTypes.Cat;

    /**
     * Подтип домашних животных - кошки
     * @param number Номер животного в общем реестре
     * @param group Группа животных - домашние животные
     * @param type Вид животного - кошка
     * @param name Имя животного
     * @param birthday День рождения животного
     * @param commandsReport Все команды животного одной строкой
     * @param commands Коллекция команд животного
     */
    public Cat(Integer number, String group, String type, String name, Date birthday, String commandsReport, ArrayList<Command> commands) {
        super(number, group, type, name, birthday, commandsReport, commands);
        System.out.println("--log: определена принадлежность животного к виду кошек");
    }
}

/**
 * Вид домашних животных - хомяки
 */
class Hamster extends Pet {
    /**
     * Вид животного - хомяк
     */
    protected final String type = AnimalTypes.Hamster;

    /**
     * Подтип домашних животных - хомяки
     * @param number Номер животного в общем реестре
     * @param group Группа животных - домашние животные
     * @param type Вид животного - хомяки
     * @param name Имя животного
     * @param birthday День рождения животного
     * @param commandsReport Все команды животного одной строкой
     * @param commands Коллекция команд животного
     */
    public Hamster(Integer number, String group, String type, String name, Date birthday, String commandsReport, ArrayList<Command> commands) {
        super(number, group, type, name, birthday, commandsReport, commands);
        System.out.println("--log: определена принадлежность животного к виду хомяков");
    }
}

/**
 * Вид домашних животных - лошади
 */
class Horse extends PackAnimal {
    /**
     * Вид животного - лошадь
     */
    protected final String type = AnimalTypes.Horse;

    /**
     * Подтип домашних животных - лошадь
     * @param number Номер животного в общем реестре
     * @param group Группа животных - вьючные животные
     * @param type Вид животного - лошадь
     * @param name Имя животного
     * @param birthday День рождения животного
     * @param commandsReport Все команды животного одной строкой
     * @param commands Коллекция команд животного
     */
    public Horse(Integer number, String group, String type, String name, Date birthday, String commandsReport, ArrayList<Command> commands) {
        super(number, group, type, name, birthday, commandsReport, commands);
        System.out.println("--log: определена принадлежность животного к виду лошадей");
    }
}

/**
 * Вид домашних животных - лошади
 */
class Donkey extends PackAnimal {
    /**
     * Вид животного - осел
     */
    protected final String type = AnimalTypes.Donkey;

    /**
     * Подтип домашних животных - осел
     * @param number Номер животного в общем реестре
     * @param group Группа животных - вьючные животные
     * @param type Вид животного - осел
     * @param name Имя животного
     * @param birthday День рождения животного
     * @param commandsReport Все команды животного одной строкой
     * @param commands Коллекция команд животного
     */
    public Donkey(Integer number, String group, String type, String name, Date birthday, String commandsReport, ArrayList<Command> commands) {
        super(number, group, type, name, birthday, commandsReport, commands);
        System.out.println("--log: определена принадлежность животного к виду ослов");
    }
}


class AnimalGroups{
    protected static final String Pets = "Pets";
    protected static final String PackAnimals = "Pack animals";
}

class AnimalTypes{
    protected static final String Dog = "Dog";
    protected static final String Cat = "Cat";
    protected static final String Hamster = "Hamster";
    protected static final String Horse = "Horse";
    protected static final String Donkey = "Donkey";
}

class AnimalRegistry{
    // реестр
    private ArrayList<Animal> animals;
    // загрузка реестра
    protected void download(){
        DB db = new DB();
        animals = db.getAllRows();
    }
    // получить реестр
    protected ArrayList<Animal> getAnimals() {
        return animals;
    }
    // добавить в реестр животное
    protected void addAnimal(Animal animal){
        DB db = new DB();
        db.newRow(animal);
        this.animals.add(animal);
        System.out.println("--log: в реестр добавлено животное " + animal.name);
    }
    // обучить животное новой команде
    protected void learnNewCommand(Integer number, String newCommandName){
        // Запись в базу
        DB db = new DB();
        db.updateCell(number, newCommandName);

        // Запись в реестр
        number -= 1; // перевод с человеческого языка на компьютерный
        Animal dog4 = animals.get(number);
        dog4.addNewCommand(newCommandName);
        animals.set(number, dog4);
    }
    // дефолтный конструктор
    public AnimalRegistry() {
        this.animals = new ArrayList<>();
    }
    // конструктор принимающий готовый список животных
    public AnimalRegistry(ArrayList<Animal> animals) {
        this.animals = animals;
    }
    // распечатать реестр
    protected String report(){
        StringBuilder report = new StringBuilder();
        for (var animal : animals) {
            report.append("Номер: ").append(animal.number).append("; ")
                    .append("Группа: ").append(animal.group).append("; ")
                    .append("Вид: ").append(animal.type).append("; ")
                    .append("Имя: ").append(animal.name).append("; ")
                    .append("День рождения: ").append(animal.birthday).append("; ")
                    .append("Команды: ").append(animal.commandsReport).append(".\n");
        }
        return report.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        // 0. Объект реестра
        AnimalRegistry animals = new AnimalRegistry();
        // 1. Загрузить весь реестр
        animals.download();
        // 2. Показать весь реестр
        System.out.println("Реестр животных:\n" + animals.report());
        // 3. Добавить животное
        animals.addAnimal(
          new Dog(  DataInfo.getAnimalCount()+1,
                    AnimalGroups.Pets,
                    AnimalTypes.Dog,
                    "Dog4",
                    new Date(2023, 1, 19),
                    "Сидеть, Принести",
                    new ArrayList<>()));    //todo доделать pars строки в коллекцию
        // 4. Выбрать животное, увидеть команды, выбрать команду или обучить новой команде
        System.out.println("Команды номера 16 до обучения:\n" + animals.getAnimals().get(15).commandsReport);
        animals.learnNewCommand(16, "НоваяКоманда");
        System.out.println("Команды номера 16 после обучения:\n" + animals.getAnimals().get(15).commandsReport);

    }
}