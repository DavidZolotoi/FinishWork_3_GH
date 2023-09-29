package org.example.controller;

import org.example.controller.*;

import java.sql.*;
import java.util.ArrayList;

class DB {
    // Параметры подключения
    private final String url = "jdbc:mysql://localhost:3306/Animal";
    private final String user = "root";
    private final String password = "testtest";
    private final String tableName = "AnimalRegistry";

    /**
     * Распознать таблицу из БД в список животных
     *
     * @return список животных из БД
     */
    protected ArrayList<Animal> getAllRows() {
        // Сама таблица - коллекция строк - животных
        ArrayList<Animal> allRows = new ArrayList<>();
        // SQL-запрос для выборки всех записей из таблицы Pets
        String sql = "SELECT * FROM " + tableName;
        // Попытка установки соединения
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            // Обработка результатов запроса
            while (resultSet.next()) {
                int number = resultSet.getInt("Number");
                String group = resultSet.getString("GroupA");
                String type = resultSet.getString("Type");
                String name = resultSet.getString("Name");
                Date birthday = resultSet.getDate("Birthday");
                String commandsReport = resultSet.getString("Commands");
                ArrayList<Command> commands = new ArrayList<>();    //todo доделать pars строки в коллекцию

                // Распознавание типа животного
                Animal animal;
                switch (type) {
                    case AnimalTypes.Dog ->
                            animal = new Dog(number, group, type, name, birthday, commandsReport, commands);
                    case AnimalTypes.Cat ->
                            animal = new Cat(number, group, type, name, birthday, commandsReport, commands);
                    case AnimalTypes.Hamster ->
                            animal = new Hamster(number, group, type, name, birthday, commandsReport, commands);
                    case AnimalTypes.Horse ->
                            animal = new Horse(number, group, type, name, birthday, commandsReport, commands);
                    default ->  // система требует учета абсолютно всех вариантов, поэтому универсальным делаем осла
                            animal = new Donkey(number, group, type, name, birthday, commandsReport, commands);
                }
                allRows.add(animal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allRows;
    }

    /**
     * Добавить новую строку (животное)
     *
     * @param animal животное (строка), которое необходимо добавить
     */
    protected void newRow(Animal animal) {
        // SQL-запрос на добавление строки
        String insertSQL = "INSERT INTO AnimalRegistry (Number, GroupA, Type, Name, Birthday, Commands) " +
                "VALUES (" +
                animal.number + ", " +
                "'" + animal.group + "', " +
                "'" + animal.type + "', " +
                "'" + animal.name + "', " +
                "'" + animal.birthday + "', " +
                "'" + animal.commandsReport + "')";
        // Создаем объекты для установления соединения и выполнения SQL-запросов
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            // Выполняем SQL-запрос
            statement.executeUpdate(insertSQL);
            System.out.println("--log: в БД добавлено животное " + animal.name);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("--log: ОШИБКА! при выполнении SQL-запроса: " + e.getMessage());
        }
    }

    /**
     * Добавление новой команды для животного
     *
     * @param number         номер животного
     * @param newCommandName новая команда
     */
    protected void updateCell(Integer number, String newCommandName) {
        // SQL-запрос на обновление строки
        String updateSQL = "UPDATE AnimalRegistry " +
                "SET Commands = CONCAT(Commands, ', " + newCommandName + "') " +
                "WHERE Number = " + number;
        // Создаем объекты для установления соединения и выполнения SQL-запросов
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            // Выполняем SQL-запрос
            statement.executeUpdate(updateSQL);
            System.out.println("--log: в БД добавлена новая команда " + newCommandName + " для животного с номером " + number);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("--log: ОШИБКА! при выполнении SQL-запроса: " + e.getMessage());
        }
    }
}
