package project1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.opencsv.CSVWriter;
import org.apache.commons.lang3.math.NumberUtils;

//poziom 4

class PlayerResult {
    private final String name;
    private final Integer numberOfAttempts;

    //dodac interface comparable i zrobic wersje z interfacem
    public PlayerResult (String name, Integer numberOfAttempts) {
        this.name = name;
        this.numberOfAttempts = numberOfAttempts;
    }

    public String getName() {
        return this.name;
    }

    public Integer getNumberOfAttempts() {
        return this.numberOfAttempts;
    }
}

class Game {
    public PlayerResult play(int randRange) {
        Integer numberOfAttempts = 0;
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        //wylosuj liczbe
        int randomNumber = rand.nextInt(randRange);

        String menuOption;
        System.out.println("Hello if you write \"exit\", you will exit a game.");

        while (true) {
            System.out.println("Guess a number?");
            menuOption = scanner.nextLine();

            if (NumberUtils.isParsable(menuOption)) {
                //option is number
                int menuOptionAsInt = Integer.parseInt(menuOption);

                if (menuOptionAsInt == randomNumber) {
                    System.out.println("You won!!!");
                    System.out.println("What is your name?");
                    String name = scanner.nextLine();

                    return new PlayerResult(name, numberOfAttempts);
                } else if (menuOptionAsInt < randomNumber) {
                    System.out.println("Random number is higher");
                    numberOfAttempts++;
                } else {
                    System.out.println("Random number is lower");
                    numberOfAttempts++;
                }
            } else if (Objects.equals(menuOption, "exit")){
                //option is not a number
                System.out.println("Bye :)");
                break;
            }
        }
        return null;
    }

    public void addPlayerResultToDataBase(PlayerResult yourResult, ArrayList<PlayerResult> dataBase) {
        boolean playerWasInDataBase = false;
        //add result to dataBase
        //check if player is already there
        for (PlayerResult playerResult : dataBase) {
            if (Objects.equals(playerResult.getName(), yourResult.getName())) {
                playerWasInDataBase = true;
                int index = dataBase.indexOf(playerResult);

                //if new result is better swap with previous one
                if (yourResult.getNumberOfAttempts() < playerResult.getNumberOfAttempts()) {
                    dataBase.set(index, yourResult);
                }
            }
        }

        if (!playerWasInDataBase)
            dataBase.add(yourResult);
    }

    public void updateDataBaseFile(ArrayList<PlayerResult> dataBase, String fileName) {

        File file = new File(fileName);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = { "Name", "NumberOfAttempts"};
            writer.writeNext(header);

            for (PlayerResult playerResult : dataBase) {
                String[] playerArray = {playerResult.getName(), String.valueOf(playerResult.getNumberOfAttempts())};
                writer.writeNext(playerArray);
            }

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void sort(ArrayList<PlayerResult> dataBase) {
        dataBase.sort((Comparator.comparing(PlayerResult::getNumberOfAttempts)));
    }

    public static void main(String[] args) {
        //baza graczy i ich wynikow
        ArrayList<PlayerResult> dataBase = new ArrayList<>();
        String dataBaseFile = "C:\\Users\\mmale\\Repozytoria\\javaNauka\\src\\main\\java\\com\\marcel_malewski\\nauka\\demo.csv";

        PlayerResult test = new PlayerResult("jan", 15);
        dataBase.add(test);

        PlayerResult test2 = new PlayerResult("janek", 10);
        dataBase.add(test2);

        PlayerResult test3 = new PlayerResult("wojtek", 20);
        dataBase.add(test3);

        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        int menuOption;

        Menu:
            while (true) {
                System.out.println("Guessing game Menu:");
                System.out.println("1 - Play a game");
                System.out.println("2 - Exit");
                menuOption = scanner.nextInt();

                switch (menuOption) {
                    case 1:
                        //gra zwraca gracza z wynikiem
                        PlayerResult yourResult = game.play(100);
                        //player didnt click break
                        if (yourResult != null)
                            //update data base
                            game.addPlayerResultToDataBase(yourResult, dataBase);

                        break;
                    case 2:
                        sort(dataBase);
                        for (PlayerResult playerResult : dataBase) {
                            System.out.println(playerResult.getName());
                            System.out.println(playerResult.getNumberOfAttempts());
                        }

                        //game.updateDataBaseFile(dataBase, dataBaseFile);

                        break Menu;
                }
            }
    }
}
