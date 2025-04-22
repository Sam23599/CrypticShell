package views;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.DataDAO;
import model.Data;

public class UserView {

    private String email;

    UserView(String email) {
        this.email = email;
    }

    public void home(){
        do{
            System.out.println("Welcome to Cryptic Shell! : " + this.email);
            System.out.println("Please select an option:");
            System.out.println("1. Show all encrypted hidden files");
            System.out.println("2. Encrypt a file");
            System.out.println("3. Decrypt a file");
            System.out.println("0. Exit");

            Scanner sc = new Scanner(System.in);
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    try {
                        List<Data> files = DataDAO.getAllFiles(this.email);
                        if(files.isEmpty()) {
                            System.out.println("No hidden files found.\n");
                            break;
                        }

                        System.out.println("Your hidden files are: ");
                        System.out.println("ID - File Name");
                        for (Data file : files) {
                            System.out.println(file.getId() + " - " + file.getFileName());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Enter the file path to encrypt: ");
                    String filePath = sc.nextLine();
                    File f = new File(filePath);
                    
                    Data file = new Data(0, f.getName(), filePath, this.email);

                    try {
                        DataDAO.hideFile(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    List<Data> filesList;
                    try {
                        filesList = DataDAO.getAllFiles(this.email);
                        System.out.println("Your hidden files are: ");
                        System.out.println("ID - File Name");
                        for (Data file1 : filesList) {
                            System.out.println(file1.getId() + " - " + file1.getFileName());
                        }

                        System.out.println("Enter the ID of the file to decrypt: ");
                        int id = Integer.parseInt(sc.nextLine());
                        boolean isValidId = false;
                        for (Data file1 : filesList) {
                            if (file1.getId() == id) {
                                isValidId = true;
                                break;
                            }
                        }
                        if (!isValidId) {
                            System.out.println("Invalid ID. Please try again.");
                            continue;
                        }
                        DataDAO.unHideFile(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 0:
                    System.out.println("Exiting the application.");
                    System.exit(0);
                    break;
            }
        }
        while(true);
    } 
}
