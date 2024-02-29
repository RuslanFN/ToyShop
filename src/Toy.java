public class Toy {
    private int Id;
    private String Name;
    private Float Weight;
    private  int Count;

    public int getId() {
        return Id;
    }

    public int getCount() {
        return Count;
    }

    public Float getWeight() {
        return Weight;
    }

    public Toy(int id, String name, Float weight, int count) {
        Id = id;
        Name = name;
        Weight = weight;
        Count = count;
    }
    void giveToy(){
        Count--;
    }

    public void setWeight(Float weight) {
        Weight = weight;
    }

    public String getName() {
        return Name;
    }
}
