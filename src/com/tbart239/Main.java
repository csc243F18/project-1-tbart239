package com.tbart239;

/*
    Author:         Tyler Bartnick
    Major:          Computer Science - I.T.
    Creation Date:  9/12/2018
    Due Date:       9/18/2018
    Course:         CSC 243 - Java Programming
    Professor:      Dr. Demarco
    Assignment:     Project #1
    Filename:       Main.java
    Purpose:        Calculate the compound interest for a given annual amount, over a set number of years,
                    with a fixed interest rate of 5%.
 */

import com.sun.istack.internal.NotNull;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    // static variables available throughout program
    private static Scanner scanner = new Scanner(System.in);
    private static final double INTEREST_RATE = 0.05;
    private static final String errorMsg = "Supplied value is invalid. Please enter a number greater than 0.\n";

    public static void main(String[] args) {

        // calculate the total retirement savings with given inputs
        double totalRetirementSavings = calculateRetirementSavings(getYearsFromUser(), getAnnualSavingsFromUser());

        // output the information in a nicely formatted manner
        System.out.println("\nTotal retirement savings: " + NumberFormat.getCurrencyInstance(Locale.US).format(totalRetirementSavings));

        // ensure that input scanner is correctly closed
        scanner.close();
        System.exit(0);
    }

    private static int getYearsFromUser() {
        /*
            Name:           getYearsFromUser
            Description:    Prompt the user for the number of years for use in the calculation - with error checking for invalid inputs.
            Parameters:     N/a
            Return:         int - the number of years
         */

        int years;

        // prompt user number of years, force valid input
        while (true) {
            try {
                System.out.print("Please enter the number of years until retirement:\n> ");
                years = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(errorMsg);
                scanner.next(); // clear buffer - infinite loop otherwise
                continue;
            }

            if (years > 0) {
                break;
            } else {
                System.out.println(errorMsg);
            }
        }

        return years;
    }

    private static double getAnnualSavingsFromUser() {
        /*
            Name:           getAnnualSavingsFromUser
            Description:    Prompt the user for the total annual savings for use in the calculation - with error checking for invalid inputs.
            Parameters:     N/a
            Return:         double  - annual savings
         */

        double savings;

        // prompt user annual savings, force valid input
        while (true) {
            try {
                System.out.print("Please enter your total annual savings:\n> ");
                savings = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println(errorMsg);
                scanner.next(); // clear buffer - infinite loop otherwise
                continue;
            }

            if (savings > 0.0d) {
                break;
            } else {
                System.out.println(errorMsg);
            }
        }

        return savings;

    }

    private static double calculateRetirementSavings(@NotNull int years, @NotNull double savings) {
        /*
            Name:           calculateRetirementSavings
            Description:    Calculate the total retirement savings, with a fixed compound interest rate, for the given number of years and annual contribution.
            Parameters:     int     - years     - the total number of years - input
                            double  - savings   - the total annual savings contribution - input
            Return:         double  - total retirement savings, with compound interest
         */

        double retirementSavings = 0.0d;

        for (int i = 0; i < years; i++) {
            retirementSavings += savings;
            retirementSavings += (retirementSavings * INTEREST_RATE);
        }

        return retirementSavings;
    }
}
