package com.simpletaskmanager;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> taskList = new ArrayList<String>();

        taskList.add("Pescar morenas");
        taskList.add("Cocinarlas");
        taskList.add("Limpiar la cocina");
        taskList.add("Echar gasolina");
        taskList.add("Comprar pan");
        taskList.add("Hacer la colada");

        int option;
        while( ( option = showMenu(taskList) ) != 0){
            switch (option){
                case 1:
                    // Ver tareas
                    showTasks(taskList);
                    break;
                case 2:
                    addTask(taskList);
                    // Añadir tarea
                    break;
                case 3:
                    deleteTask(taskList);
                    // Eliminar tarea
                case 4:
                    editTask(taskList);
                case 5:
                    moveTask(taskList);
                    break;
                default:
            }
        }
    }

    private static void moveTask(ArrayList<String> myTasks) {
        Scanner input = new Scanner(System.in);
        int fromIndexTask, toIndexTask;
        String editedTask;

        showTasks(myTasks);

        // Leer el índice de la tarea a mover
        do {
            System.out.println("Introduzca el índice de la tarea a mover:");
            fromIndexTask = input.nextInt();
        }while( !correctIndex(fromIndexTask, myTasks) );

        // Leer la posición a la que la quiero mover
        do {
            System.out.println("Introduzca el índice de la posición:");
            toIndexTask = input.nextInt();
        }while( !correctIndex(toIndexTask, myTasks) );

        myTasks.add(toIndexTask, myTasks.get(fromIndexTask) );
        myTasks.remove( fromIndexTask + 1);
    }

    private static void editTask(ArrayList<String> myTasks) {
        Scanner input = new Scanner(System.in);
        int indexTask;
        String editedTask;

        showTasks(myTasks);

        // Leer el índice de la tarea a modificar
        do {

            System.out.println("Introduzca el índice de la tarea a editar:");
            indexTask = input.nextInt();
        }while( !correctIndex(indexTask, myTasks) );

        // Leer la nueva tarea
        input.nextLine();

        do{
            System.out.println("Introducir nueva tarea");
            editedTask = input.nextLine().trim().replaceAll("\\s+"," ");
        }while(
                editedTask.length() == 0 ||
                myTasks.contains( editedTask )
                );

        myTasks.set( indexTask, editedTask);

    }


    public static void deleteTask(ArrayList<String> myTasks){
        Scanner input = new Scanner(System.in);
        int index;

        showTasks(myTasks);

        do{
            System.out.printf("Introducir el índice: ");
            index = input.nextInt();
        }while( !correctIndex(index, myTasks) );


        myTasks.remove(index);

    }

    public static boolean correctIndex(int index, ArrayList<String> myTasks){
        if( index >=0 && index < myTasks.size() ){
            return true;
        }else{
            return false;
        }
    }

    public static void addTask(ArrayList<String> myTasks){
        Scanner input = new Scanner(System.in);
        String task;

        do{
            System.out.println("Introducir nueva tarea:");
            task = input.nextLine().trim().replaceAll("\\s+"," ");
        }while( task.length() == 0);


        myTasks.add( task );

    }

    public static void showTasks(ArrayList<String> myTasks){
        int index = 0;
        for (String task: myTasks) {
            System.out.println( (index++) + " - " + task);
        }

    }

    public static int showMenu(ArrayList<String> myTasks){
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> options = new ArrayList<Integer>();
        int option;

        System.out.println("**********************");
        System.out.println("* 1 - Ver tareas     *");
        options.add(1);
        System.out.println("* 2 - Añadir tarea   *");

        options.add(2);
        if (myTasks.size() > 0 ){
            System.out.println("* 3 - Eliminar tarea *");
            options.add(3);
            System.out.println("* 4 - Editar tarea   *");
            options.add(4);

        }
        if ( myTasks.size() > 1 ){
            System.out.println("* 5 - Mover tarea    *");
            options.add(5);
        }
        System.out.println("* 0 - Salir          *");
        options.add(0);
        System.out.println("**********************");
        do{
            System.out.println("Opción: ");
            option = input.nextInt();
        }while( option < 0 || option >= options.size());


        return option;
    }
}
