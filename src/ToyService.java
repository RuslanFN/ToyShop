import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
        if (weight > 0 && weight < 1)
            throw new NumberFormatException("Вероятность выпадния игрушки не может быть больше или равна 1");
        toyList.add(new Toy(++CountToy, name, weight, count));
    }

    @Override
    public void initToy(List<Toy> toyList) throws FileNotFoundException {
        File f = new File("Data/data.txt");
        if (f.exists()){
            try(Scanner scanner = new Scanner(f);){

                while (scanner.hasNext()){
                    List<String> toy = List.of(scanner.nextLine().split(" "));
                    String name = toy.get(0);
                    float weight = Float.parseFloat(toy.get(1));
                    int count = Integer.parseInt(toy.get(2));
                    addToy(toyList, name, weight, count);
                }
            }
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
    public void givetoy(List<Toy> toyList) {
        toyList.remove(toyList.size()-1);
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
        int id = random.nextInt(0, ids.size()-1);
        for (Toy toy: toyList){
            if (toy.getId() == id)
                return toy;
        }
        return null;
    }
}
