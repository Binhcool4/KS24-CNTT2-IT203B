package KiemTraDauGio;

class DigitalProduct extends Product {
    private double size; // MB

    public DigitalProduct(String id, String name, double price, double size) {
        super(id, name, price);
        this.size = size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public void displayInfo() {
        System.out.println("[Digital] ID: " + id +
                ", Name: " + name +
                ", Price: " + price +
                ", Size: " + size + " MB");
    }
}
