package com.blackjack.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game {
    public static ArrayList<String> result = new ArrayList<>();
    private static boolean isEnd = false;
    public static Deck deckForGame ;

    private static BufferedReader reader ;

    private static int rating = 0;

    private static Player dealer = new Player("Dealer");
    private static Player gamer;

    public static Card getCard(){

        return deckForGame.getCardsForGame().get((int) (Math.random()*deckForGame.getCardsForGame().size()));
    }

    public static void initialize() {
        gamer.updateCards();
        dealer.updateCards();
        gamer.getCards().add(getCard());
        gamer.getCards().add(getCard());
        dealer.getCards().add(getCard());

    }

    private static void start() throws IOException {
        isEnd = false;
        System.out.println("Введите ваше имя:");
        reader  = new BufferedReader(new InputStreamReader(System.in));
        gamer = new Player(reader.readLine());
        deckForGame = new Deck();
    }

    private static void playerTurn() throws IOException {
        boolean play = true;
        while (play == true) {
            gamer.setScore();
            if (gamer.getScore()<21) {
                System.out.println("Ваш счет: " + gamer.getScore());
                System.out.println("Хотите взять карту ? Да : Нет");
                String answer = reader.readLine();
                if (answer.toLowerCase().equals("да") || answer.toLowerCase().equals("yes")) {
                    gamer.getCards().add(getCard());
                } else {
                    System.out.println("Передаю ход оппоненту!");
                    play = false;
                }
            }
            else {
                System.out.println("Вас счет: " + gamer.getScore());
                System.out.println("Передаю ход оппоненту!");
                play = false;
            }
        }
    }

    private static void dealerTurn() {
        while (true) {
            dealer.setScore();
            if (dealer.getScore()<21){
                int probability = 0;
                for (Card card : deckForGame.getCardsForGame()) {
                    if ((dealer.getScore() + card.getValue())>21){
                        probability++;
                    }
                }
                if (probability<5){
                    dealer.getCards().add(getCard());
                }
                else {
                    System.out.println("Оппонент завершил ход !");
                    break;
                }
            }
            else {
                System.out.println("Оппонент завершил ход !");
                break;
            }
        }
    }

    private static void resultInfo() throws IOException {
        gamer.getScore();
        dealer.setScore();

        if (dealer.getScore() > 21 && gamer.getScore()>21 || gamer.getScore()==dealer.getScore()){
            result.add("Н");
            System.out.println("Ничья!" + gamer.getScore() + ":" + dealer.getScore());
        }
        else if (gamer.getScore()>21 && dealer.getScore()<=21 || dealer.getScore()>gamer.getScore()){
            result.add("К");
            System.out.println("Оппонент выиграл раунд!" + gamer.getScore() + ":" + dealer.getScore());
        }
        else if (gamer.getScore()<=21 && dealer.getScore()>21 || dealer.getScore()<gamer.getScore()){
            result.add("И");
            System.out.println("Вы выиграли раунд!" + gamer.getScore() + ":" + dealer.getScore());
        }
        System.out.println("Желаете продолжить ? Да : Нет");
        String answer = reader.readLine();
        if (answer.toLowerCase().equals("да") || answer.toLowerCase().equals("yes")){
            System.out.println("Начало нового раунда !");
        }
        else {
            isEnd = true;
        }
    }

    private static void gamesEnd() throws IOException {
        int draw = 0;
        int bot = 0;
        int player = 0;
        for (String s : result) {
            if (s.equals("Н")){
                rating = rating - 1;
                draw++;
            }
            else if (s.equals("К")){
                rating = rating - 10;
                bot++;
            }
            else if (s.equals("И")){
                rating = rating + 10;
                player++;
            }
        }
        System.out.println("Игра окончена !");
        System.out.println("Ваш рейтинг: " + rating);
        System.out.println("Хотите посмотреть подробную статистику ? Да : Нет");
        String s = reader.readLine();
        if (s.toLowerCase().equals("yes") || s.toLowerCase().equals("да")){
            System.out.println("Раундов выиграно: " + player + "\r\n" + "Раундов проиграно: " + bot + "\r\n" + "Раундов сыгранных в ничью: " + draw);
        }

        System.out.println("Удачной охоты Каа!");
    }

    public static void main(String[] args) throws IOException {
        start();
        while (isEnd==false) {
            initialize();
            playerTurn();
            dealerTurn();
            resultInfo();
        }
        gamesEnd();
        reader.close();
    }
}
