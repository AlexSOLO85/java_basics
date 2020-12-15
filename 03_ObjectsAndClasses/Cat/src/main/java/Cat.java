
public class Cat {
    private double originWeight;
    private double weight;
    private double allEat;

    private double minWeight;
    private double maxWeight;

    private static int count;

    public static final int QUANTITY_EYES = 2;
    public static final double MINIMAL_WEIGHT = 1000;
    public static final double MAXIMAL_WEIGHT = 9000;

    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Cat() {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        count++;
    }

    public Cat(double weight) {
        this();
        this.weight = weight;
    }

    public void meow() {
        if (weight > minWeight) {
            weight = weight - 1;
            System.out.println("Meow");
        } else
            printStringDead();
    }

    public void feed(Double amount) {
        if (weight < maxWeight) {
            weight = weight + amount;
            allEat = allEat + amount;
        } else
            printStringDead();
    }

    public void drink(Double amount) {
        if (weight < maxWeight) {
            weight = weight + amount;
        } else
            printStringDead();
    }

    public void pee(Double amount) {
        if (weight > minWeight) {
            weight = weight - amount;
            System.out.println("Сat went to pee");
        } else
            printStringDead();
    }

    private void printStringDead() {
        System.out.println("Cat is dead");
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

    public Cat cloneCat() {
        Cat cat = new Cat();
        cat.originWeight = this.originWeight;
        cat.weight = this.weight;
        cat.minWeight = this.minWeight;
        cat.maxWeight = this.maxWeight;
        return cat;
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