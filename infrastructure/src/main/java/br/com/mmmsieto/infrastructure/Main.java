package br.com.mmmsieto.infrastructure;


import br.com.mmmsieto.application.UseCase;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(new UseCase().execute());
    }

}