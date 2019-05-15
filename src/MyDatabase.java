import java.io.*;
import java.util.*;

public class MyDatabase extends TreeMap {
    private TreeMap<Integer,String> userList;
    MyDatabase(){
        userList = new TreeMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите имя файла:");
        String name = sc.nextLine();
        int tempCount = 0;
        try {
            FileInputStream inF = new FileInputStream("D://Java/Database/"+name);
            BufferedReader fr = new BufferedReader(new InputStreamReader(inF,"Cp1251"));
            String line = null;
            String[] arr = null;
            while ((line = fr.readLine()) != null) {
                arr = line.split(":");
                userList.put(Integer.valueOf(arr[0]),arr[1]);
            }
            fr.close();
        }
        catch(NumberFormatException o)
        {
            System.err.println("You chose invalid file. Error:" + o);
        }
        catch (FileNotFoundException e1) {
            System.err.println("Error:" + e1);
        }
        catch (IOException e2) {
            System.err.println("Error:" + e2);
        }
    }
    public void Remove(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите номер игрока,которого нужно удалить из БД: ");
            int num = sc.nextInt();
            if (!checkString(String.valueOf(num))) {
                throw new NotValidDataException("Not valid data");
            }
            if (userList.containsKey(num) == false) System.err.println("Игрока с таким номером не существует");
            userList.remove(num);
        }
        catch (NotValidDataException e){
            System.err.println(e);
        }
        catch (NumberFormatException k){
            System.err.println("Not valid data");
        }
        catch (ArrayIndexOutOfBoundsException a){
            System.err.println("Not valid data");
        }
        catch (InputMismatchException a){
            System.err.println("Not valid data");
        }
    }
    public void WriteToFile(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input name of second file:");
        String name2 = sc.next();
        try
        {
            OutputStream f = new FileOutputStream("D://Java/Database/"+name2);
            OutputStreamWriter writer = new OutputStreamWriter(f);
            BufferedWriter out = new BufferedWriter(writer);

            for(Map.Entry<Integer,String> e : userList.entrySet())
            {
                writer.write(Integer.toString(e.getKey()));
                writer.write(" : ");
                writer.write(e.getValue());
                writer.write("; "+"\r\n");
            }
            out.close();
        }
        catch(IOException ex)
        {
            System.err.println("Error:" + ex);
        }
    }
    public void Add(){
        List<String> str = new ArrayList<>();
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите номер игрока: ");
            String data = sc.nextLine();
            if (userList.containsKey(Integer.valueOf(data))==true){
                System.err.println("Игрок с таким номером уже существует.");
                return;
            }
            str.add(data);
            System.out.println("Введите Имя игрока: ");
            data = sc.nextLine();
            str.add(data);
            if (checkString(str.get(1))!=false){
                throw new NotValidDataException("Not valid data");
            }
            System.out.println("Введите Фамилию игрока: ");
            data = sc.nextLine();
            str.add(data);
            if (checkString(str.get(2))!=false){
                throw new NotValidDataException("Not valid data");
            }
            System.out.println("Введите Возраст игрока: ");
            data = sc.nextLine();
            str.add(data);
            if (!checkString(str.get(3))){
                throw new NotValidDataException("Not valid data");
            }
            System.out.println("Введите Амплуа игрока: ");
            data = sc.nextLine();
            str.add(data);
            if (checkString(str.get(4))!=false){
                throw new NotValidDataException("Not valid data");
            }
            System.out.println("Введите кол-во минут на поле игрока: ");
            data = sc.nextLine();
            str.add(data);
            if (!checkString(str.get(5))){
                throw new NotValidDataException("Not valid data");
            }

            if (!checkString(str.get(3))||!checkString(str.get(5))){
                throw new NotValidDataException("Not valid data");
            }
            userList.put(Integer.valueOf(str.get(0)),str.get(1)+" "+str.get(2)+" "+str.get(3)+" "+str.get(4)+" "+str.get(5));
        }
        catch (NotValidDataException e){
            System.out.println(e);
        }
        catch (NumberFormatException k){
            System.err.println("Not valid data");
        }
        catch (ArrayIndexOutOfBoundsException a){
            System.err.println("Not valid data");
        }
    }
    public boolean Search() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите номер игрока,которого нужно найти: ");
            int num = sc.nextInt();
            if (!checkString(String.valueOf(num))) {
                throw new NotValidDataException("Not valid data");
            }
            if (userList.containsKey(num) == false) {
                System.err.println("Игрока с таким номером не существует");
                return false;
            }

        if (userList.containsKey(num) == true) {
            for (Map.Entry<Integer, String> e : userList.entrySet()) {
                if (e.getKey() == num) System.out.println("№ "+e.getKey() + " : " + e.getValue());
            }
            return true;
        }

        }
        catch (NotValidDataException e){
            System.err.println(e);
        }
        catch (NumberFormatException k){
            System.err.println("Not valid data");
        }
        catch (ArrayIndexOutOfBoundsException a){
            System.err.println("Not valid data");
        }
        catch (InputMismatchException a){
            System.err.println("Not valid data");
        }
        return true;
    }
    public boolean checkString(String string) {
        if (string == null) return false;
        return string.matches("^-?\\d+$");
    }
    public void Print(){
        for (Map.Entry<Integer, String> e : userList.entrySet()) {
            System.out.println("№ "+e.getKey() + " : " + e.getValue());
        }
    }
    public void Edit(){
        try {
            List<String> str = new ArrayList<>();
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите номер игрока для редактирования: ");
            int num = sc.nextInt();
            if (userList.containsKey(num) == false) {
                System.err.println("Игрока с таким номером не существует.");
                return;
            }
            System.out.println("(0) Редактировать игрока по: Номеру\n" +
                    "(1) Редактировать игрока по: Имени\n" +
                    "(2) Редактировать игрока по: Фамилии\n" +
                    "(3) Редактировать игрока по: Возрасту\n" +
                    "(4) Редактировать игрока по: Амплуа\n" +
                    "(5) Редактировать игрока по: кол-ву минут на поле");
            int sw = sc.nextInt();
            if (sw == 0) { //Редактирование по номеру
                for (Map.Entry<Integer, String> e : userList.entrySet()) {
                    if (e.getKey() == num) str.add(e.getValue());
                }
                sc = new Scanner(System.in);
                System.out.println("Введите новый номер игрока: ");
                String newnum = sc.nextLine();
                if (!checkString(newnum)){
                    throw new NotValidDataException("Not valid data");
                }
                if (userList.containsKey(Integer.parseInt(newnum)) == true) {
                    System.err.println("Игрок с таким номером уже существует.");
                    return;
                }
                userList.remove(num);
                userList.put(Integer.parseInt(newnum), str.get(0));
            }
             {//Редактирование по всему остальному
                for (Map.Entry<Integer, String> e : userList.entrySet()) {
                    if (e.getKey() == num) str.add(e.getValue());
                }
                String[] str1 = (str.get(0)).split(" ");
                if (sw == 1) System.out.println("Введите новое имя игрока: ");
                if (sw == 2) System.out.println("Введите новую фамилию игрока: ");
                if (sw == 3) System.out.println("Введите новый возраст игрока: ");
                if (sw == 4) System.out.println("Введите новое амплуа игрока: ");
                if (sw == 5) System.out.println("Введите новое кол-во минут на поле: ");
                sc = new Scanner(System.in);
                String name = sc.nextLine();
                if (sw == 3 || sw == 5) {
                    if (!checkString(name)) {
                        throw new NotValidDataException("Not valid data");
                    }
                }
                if (sw == 1 || sw == 2|| sw == 4) {
                    if (checkString(name)!=false) {
                        throw new NotValidDataException("Not valid data");
                    }
                }
                str1[sw - 1] = name;
                str.clear();
                str.add(str1[0] + " " + str1[1] + " " + str1[2] + " " + str1[3] + " " + str1[4]);
                userList.remove(num);
                userList.put(num, str.get(0));
            }
        }
       catch (NotValidDataException e){
                System.err.println(e);
            }
        catch (NumberFormatException k){
                System.err.println("Not valid data");
            }
        catch (ArrayIndexOutOfBoundsException a){
                System.err.println("Not valid data");
            }
    }
}
