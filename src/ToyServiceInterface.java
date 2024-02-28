import java.io.FileNotFoundException;
import java.util.List;

public interface ToyServiceInterface {
    void addToy(List<Toy> toyList, String name, float weight, int count) throws NumberFormatException;
    void initToy(List<Toy> toyList) throws FileNotFoundException;
    void setWeight(List<Toy> toyList, int id, float weight);
    Toy choiseToy(List<Toy> toyList);
    void givetoy(List<Toy> toyList);
}
