import java.util.Scanner;
public class Database {
    static void Menu(){
        System.out.println("Меню\n" +
                "(1) Вывести БД в консоль \n" +
                "(2) Вывести БД в файл \n" +
                "(3) Добавить новый объект \n" +
                "(4) Удалить объект \n" +
                "(5) Поиск объекта по ключу \n" +
                "(6) Редактировать объект \n" +
                "(0) Выйти из программы");
    }
    public static void main(String[] args){
            MyDatabase userList = new MyDatabase();
            Menu();
            Scanner sc = new Scanner(System.in);
            int sw = sc.nextInt();
            while (sw != 0) {
                if (sw == 1) {
                    userList.Print();
                    Menu();
                    sw = sc.nextInt();
                }
                if (sw == 2) {
                    userList.WriteToFile();
                    Menu();
                    sw = sc.nextInt();
                }
                if (sw == 3) {
                    userList.Add();
                    Menu();
                    sw = sc.nextInt();
                }
                if (sw == 4) {
                    userList.Remove();
                    Menu();
                    sw = sc.nextInt();
                }
                if (sw == 5) {
                    userList.Search();
                    Menu();
                    sw = sc.nextInt();
                }
                if (sw == 6) {
                    userList.Edit();
                    Menu();
                    sw = sc.nextInt();
                }
            }
        }
}