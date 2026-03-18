package KiemTraDauGio;

class PhysicalProduct extends Product {
    private double weight;

    public PhysicalProduct(String id, String name, double price, double weight) {
        super(id, name, price);
        this.weight = weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public void displayInfo() {
        System.out.println("[Physical] ID: " + id +
                ", Name: " + name +
                ", Price: " + price +
                ", Weight: " + weight);
    }
}
