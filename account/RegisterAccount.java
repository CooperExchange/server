/*
ECE-366: CooperExchange
Code written by: Chris Lee
Description: Allows registration by creating Account instances and overlooking process
 */

package account;

import java.util.Scanner;

public class RegisterAccount {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        Account newAccount = registerAccount();
    }

    //Account registration method; Prompting will be replaced with front-end components
    public static Account registerAccount(){
        System.out.println("User ID: ");
        int userID = scanner.nextInt();
        System.out.println("First Name: ");
        String firstName = scanner.next();
        System.out.println("Last Name: ");
        String lastName = scanner.next();
        System.out.println("SSN: ");
        int ssn = scanner.nextInt();
        System.out.println("Username: ");
        String username = scanner.next();
        System.out.println("Password: ");
        String password = scanner.next();
        System.out.println("Email: ");
        String email = scanner.next();

        return new Account(userID, firstName, lastName, ssn, username, password, email);
    }
}