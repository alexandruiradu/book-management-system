package org.sda.bms;

import org.sda.bms.controller.AuthorController;
import org.sda.bms.controller.BookController;
import org.sda.bms.controller.UserOption;
import org.sda.bms.repository.AuthorRepositoryImpl;
import org.sda.bms.repository.BookRepositoryImpl;
import org.sda.bms.service.AuthorServiceImpl;
import org.sda.bms.service.BookServiceImpl;
import org.sda.bms.utils.SessionManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        startHibernate();

        // dependencies
        Scanner scanner = new Scanner(System.in);
        AuthorController authorController = new AuthorController(
                new AuthorServiceImpl(new AuthorRepositoryImpl()),
                scanner
        );
        BookController bookController = new BookController(
                new BookServiceImpl(
                        new BookRepositoryImpl(),
                        new AuthorRepositoryImpl()
                ),
                scanner
        );

        UserOption userOption = UserOption.UNKNOWN;
        while (userOption != UserOption.EXIT) {
            UserOption.printAllOptions();
            System.out.println();
            System.out.println("Please Select An Option:");
            try {
                int selectedOption = Integer.parseInt(scanner.nextLine().trim());
                userOption = UserOption.findUserOption(selectedOption).orElse(UserOption.UNKNOWN);
            } catch (NumberFormatException e) {
                userOption = UserOption.UNKNOWN;
            }

            switch (userOption) {
                case CREATE_AUTHOR:
                    authorController.create();
                    break;
                case UPDATE_AUTHOR:
                    authorController.updateById();
                    break;
                case DELETE_AUTHOR:
                    authorController.deleteById();
                    break;
                case VIEW_ALL_AUTHORS:
                    authorController.displayAll();
                    break;
                case CREATE_BOOK:
                    bookController.create();
                    break;
                case VIEW_ALL_BOOKS:
                    bookController.displayAll();
                    break;
                case VIEW_BOOK_BY_ID:
                    bookController.displayById();
                    break;
                case UNKNOWN:
                    System.out.println("Please insert a valid option!!!");
                    break;
                case EXIT:
                    stopHibernate();
                    System.out.println("Good bye!");
                    break;
                default:
                    System.out.println("Not implemented. Please contact your administrator");
                    break;
            }
        }
    }

    private static void startHibernate() {
        SessionManager.getSessionFactory();
        for (int index = 0; index < 50; index++) {
            System.out.println();
        }
    }

    private static void stopHibernate() {
        SessionManager.shutDown();
        for (int index = 0; index < 50; index++) {
            System.out.println();
        }
    }
}