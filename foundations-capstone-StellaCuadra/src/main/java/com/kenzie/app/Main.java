package com.kenzie.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// import necessary libraries


public class Main {
    /* Java Fundamentals Capstone project:
       - Define as many variables, properties, and methods as you decide are necessary to
       solve the program requirements.
       - You are not limited to only the class files included here
       - You must write the HTTP GET call inside the CustomHttpClient.sendGET(String URL) method
         definition provided
       - Your program execution must run from the main() method in Main.java
       - The rest is up to you. Good luck and happy coding!

     */
    static String GET_URL = "https://jservice.io/api/random?count=20";

    public static void main(String[] args)  {
        //Write main execution code here

        String response = CustomHttpClient.sendGET(GET_URL);
        int score = 0;



        ArrayList<CluesDTO> list = new ArrayList<>(CustomHttpClient.getCluesList(response));
        //math.random
        System.out.println("\n"+"********"+ " Welcome to Trivia Game " +"********"+"\n"+"\n");
        //System.out.println("To skip the question: press enter, To exit: press ESC");
        if(list.size()<19){
            System.out.println("Try again!");
        }
        Scanner scanner = new Scanner(System.in);
        //String[] answer = answer.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        for( int i = 0; i <10; i++) {
            Random randomQuestions = new Random();
            List<Integer> questions = new ArrayList<Integer>();

            Integer questionNumber = randomQuestions.nextInt(20) + 1;
            if (!questions.contains(questionNumber))
            {
                questions.add(questionNumber);
            }


            int randomIndex = (int) (Math.random() * 20);



            System.out.println( "\n"+"Category: " + list.get(randomIndex).getCategory().getTitle() + "\n" + "Question: " + list.get(randomIndex).getQuestion() + "?");
            String correctAnswer = list.get(randomIndex).getAnswer();
            //System.out.println(correctAnswer);
           String correctAnswertemp = correctAnswer.trim().replaceAll(" ",
                                    "").replaceAll("'", "").replaceAll("<i>",
                   "").replaceAll("</i>","");
            String answer = scanner.nextLine();
            if (answer.isEmpty()){
                System.out.println("Skipped the question!");
                continue;
            }
            String answerTemp = answer.trim().replaceAll(" ",
                    "").replaceAll("'", "");


            if (answerTemp.equals("quit")){
                break;
            }

            if (!correctAnswertemp.isEmpty() && correctAnswertemp.equalsIgnoreCase(answerTemp)) {
                score ++;
                System.out.println("You got that right!");
                System.out.println("Your score is: " + score);
            } else {
                System.out.println("Nope! Wrong answer!");

                System.out.println("The correct answer is: "+ correctAnswer+"\n");
            }
        }
            System.out.println("Your final score is: " + score+ " out of 10"+"\n"+ "\n"+
                            "You might not be good at this trivia game but you KENZIEMADE it!"+"\n");

            scanner.close();

    }

}

