public class Main {
    public static void main(String[] args) {
        toyMashin controller = new ToyMashinController();
        controller.initToy();
        controller.addToy("Чебурашка", 0.4F, 3);
        controller.choiseToy();
        controller.choiseToy();
        controller.choiseToy();
        controller.giveToy();
        controller.giveToy();
        controller.giveToy();
    }
}