import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ToyMashinController implements toyMashin{
    private List<Toy> toyList;
    private List<Toy> queue;
    final ToyServiceInterface service = new ToyService();

    public ToyMashinController() {
        this.toyList = new ArrayList<>();
        this.queue = new ArrayList<>();
    }

    @Override
    public void initToy() {
        try {
            service.initToy(toyList);
            System.out.println("Список игрушек прочитан");
        }catch (FileNotFoundException e){
            System.out.println("Файл не найден");
        }

    }

    @Override
    public void addToy(String name, float weight, int count) {
        service.addToy(toyList, name, weight, count);
    }

    @Override
    public void setWeight(int id, float weight) {
        service.setWeight(toyList, id, weight);
    }

    @Override
    public void choiseToy() {
        Toy toy = service.choiseToy(toyList);
        if (toy != null) {
            toy.giveToy();
            queue.add(toy);
        }


    }

    @Override
    public void giveToy() {
        service.givetoy(queue);
    }
}
