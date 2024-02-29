import java.io.*;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ToyService implements ToyServiceInterface, RandomToy{
    int CountToy;

    public ToyService() {
        CountToy = 0;
    }

    @Override
    public void addToy(List<Toy> toyList, String name, float weight,int count) throws NumberFormatException{
        if (weight < 0 && weight > 1)
            throw new NumberFormatException("Вероятность выпадния игрушки не может быть больше или равна 1");
        toyList.add(new Toy(++CountToy, name, weight, count));
    }

    @Override
    public void initToy(List<Toy> toyList) throws FileNotFoundException {
        File f = new File("Data/data.txt");

        if (f.exists()){
            try(Scanner scanner = new Scanner(f);){

                while (scanner.hasNextLine()){
                    List<String> toy = List.of(scanner.nextLine().split(" "));
                    String name = toy.get(0);
                    float weight = Float.parseFloat(toy.get(1));
                    int count = Integer.parseInt(toy.get(2));
                    addToy(toyList, name, weight, count);
                }
            }
        }else {
            System.out.println("Файл не найден");
        }
    }

    @Override
    public void setWeight(List<Toy> toyList, int id, float weight) {
        for (Toy toy:toyList){
            if (toy.getId() == id){toy.setWeight(weight);}
        }
    }

    @Override
    public Toy choiseToy(List<Toy> toyList) {
        return getRandomToy(toyList);
    }

    @Override
    public void givetoy(List<Toy> toyList){
        try(FileWriter fw = new FileWriter("Winning.txt", true);){
            fw.append(toyList.get(0).getName() + "\n");
            toyList.remove(0);
        }catch (IOException e){
            System.out.println("Файл не записан");
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Нет игрушек на выдачу");
        }

    }

    @Override
    public Toy getRandomToy(List<Toy> toyList) {
        List<Integer> ids = new ArrayList<>();
        Random random = new Random();
        for (Toy toy: toyList){
            for (int i = 0; i < toy.getWeight()*100; i++){
                ids.add(toy.getId());
            }
        }

        int id = ids.get(random.nextInt(0, ids.size()-1));
        for (Toy toy: toyList){
            if (toy.getId() == id ){

                if(toy.getCount() == 1)
                        toyList.remove(toy);
                return toy;
            }
        }
        return null;
    }
}
