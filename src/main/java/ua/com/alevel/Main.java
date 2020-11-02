package ua.com.alevel;

import ua.com.alevel.factory.CoroneDatasourceFactory;

/**
 * @author Iehor Funtusov, created 02/11/2020 - 7:41 PM
 */

public class Main {

    public static void main(String[] args) {
        System.out.println("Main.main");
        CoroneDatasourceFactory factory = new CoroneDatasourceFactory();
        factory.create();
    }
}
