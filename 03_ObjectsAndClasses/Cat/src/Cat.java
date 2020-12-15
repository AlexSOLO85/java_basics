
public class Cat {
    private double originWeight;
    private double weight;
    private double allEat;

    private double minWeight;
    private double maxWeight;

    private static int count;

    public Cat() {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        count++;
    }

    public void meow() {
        weight = weight - 1;
        System.out.println("Meow");
    }

    public void feed(Double amount) {
        weight = weight + amount;
        allEat = allEat + amount;
    }

    public void drink(Double amount) {
        weight = weight + amount;
    }

    public void pee(Double amount) {
        weight = weight - amount;
        System.out.println("Сat went to pee");
    }

    public static int getCount() {
        return count;
    }

    public Double getWeight() {
        return weight;
    }

    public double getAllEating() {
        return allEat;
    }

    public String getStatus() {
        if(weight < minWeight) {
            count--;
            return "Dead";
        }
        else if(weight > maxWeight) {
            count--;
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }
}