package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static char player = 'X';
    static String field = "_________";
    static String gameStage;

    public static void main(String[] args) {
        printField();
        while (true) {
            System.out.print("Enter the coordinates: ");
            getNewField();
            printField();
            if (isGameFinished()) {
                System.out.println(gameStage);
                break;
            }
            changePlayer();
        }
    }

    private static void printField() {
        System.out.println("---------");
        System.out.print("| ");
        for (int i = 0; i < 3; i++) {
            System.out.print(field.charAt(i) + " ");
        }
        System.out.println("|");
        System.out.print("| ");
        for (int i = 3; i < 6; i++) {
            System.out.print(field.charAt(i) + " ");
        }
        System.out.println("|");
        System.out.print("| ");
        for (int i = 6; i < 9; i++) {
            System.out.print(field.charAt(i) + " ");
        }
        System.out.println("|");
        System.out.println("---------");
    }

    private static void getNewField() {

        int index = getIndexOfCoordinates();

        while (isCellOccupied(index)) {
            System.out.println("This cell is occupied! Choose another one!");
            System.out.print("Enter the coordinates: ");
            index = getIndexOfCoordinates();
        }

        field = field.substring(0, index) + player + field.substring(index + 1);
    }

    private static int getIndexOfCoordinates() {
        int[] coordinates = getCoordinatesFromUser();
        String coordinatesString = String.valueOf(coordinates[0]) + coordinates[1];
        switch (coordinatesString) {
            case "13":
                return 0;
            case "23":
                return 1;
            case "33":
                return 2;
            case "12":
                return 3;
            case "22":
                return 4;
            case "32":
                return 5;
            case "11":
                return 6;
            case "21":
                return 7;
            case "31":
                return 8;
        }
        return -1;
    }

    private static boolean isCellOccupied(int index) {
        return field.charAt(index) == 'X' || field.charAt(index) == 'O';
    }

    private static int[] getCoordinatesFromUser() {

        boolean areValid = false;
        boolean areNumber = false;

        int[] coordinatesInIntegers = new int[2];

        while (!(areNumber && areValid)) {

            String coordinatesString = scanner.nextLine();
            String[] coordinates = coordinatesString.split(" ");
            if (coordinates[0].matches("\\d") && coordinates[1].matches("\\d")) {
                areNumber = true;
                coordinatesInIntegers = Arrays.stream(coordinates).mapToInt(Integer::parseInt).toArray();
                if (coordinatesInIntegers[0] > 0 && coordinatesInIntegers[0] < 4
                        && coordinatesInIntegers[1] > 0 && coordinatesInIntegers[1] < 4) {
                    areValid = true;
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                    System.out.print("Enter the coordinates: ");
                }
            } else {
                System.out.println("You should enter numbers!");
                System.out.print("Enter the coordinates: ");
            }
        }
        return coordinatesInIntegers;
    }

    private static boolean isGameFinished() {
        getGameStage();
        return !"Game not finished".equals(gameStage);
    }

    private static void getGameStage() {

        if (isPlayerWins()) {
            gameStage = player + " wins";
        } else if (countDifferenceBetweenPlayers() > 1 || isPlayerWins()) {
            gameStage = "Impossible";
        } else if (field.contains("_")) {
            gameStage = "Game not finished";
        } else {
            gameStage = "Draw";
        }
    }

    private static boolean isPlayerWins() {

        for (int i = 0; i < 9; i += 3) {
            if (player == field.charAt(i)
                    && player == field.charAt(i + 1)
                    && player == field.charAt(i + 2)) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (player == field.charAt(i)
                    && player == field.charAt(i + 3)
                    && player == field.charAt(i + 6)) {
                return true;
            }
        }

        return player == field.charAt(0)
                && player == field.charAt(4)
                && player == field.charAt(8)
                || player == field.charAt(2)
                && player == field.charAt(4)
                && player == field.charAt(6);
    }

    private static int countDifferenceBetweenPlayers() {
        char[] charArray = field.toCharArray();
        int countX = 0;
        int countO = 0;
        for (char element : charArray) {
            if (element == 'X') {
                countX++;
            } else if (element == 'O') {
                countO++;
            }
        }
        return Math.abs(countO - countX);
    }

    private static void changePlayer() {
        player = (player == 'X') ? 'O' : 'X';
    }

}
