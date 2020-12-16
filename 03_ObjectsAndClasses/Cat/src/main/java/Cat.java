
public class Cat {
    private double originWeight;
    private double weight;
    private double allEat = 0;

    private double minWeight;
    private double maxWeight;

    private static int count = 0;
    private boolean isAlive;

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
        isAlive = true;
        count++;
    }

    public Cat(double weight) {
        this();
        this.weight = weight;
        this.originWeight = weight;
    }

    public boolean isWeightNormal() {
        return (weight > MINIMAL_WEIGHT && weight < MAXIMAL_WEIGHT);
    }

    public void meow() {
        if (isAlive) {
            weight = weight - 1;
            System.out.println("Meow");
            if (!isWeightNormal()) {
                isAlive = false;
                count--;
            }
            if (!isAlive) {
                printStringDead();
            }
        }
    }

    public void feed(Double amount) {
        if (isAlive) {
            weight = weight + amount;
            allEat = allEat + amount;
            isWeightNormal();
            if (!isWeightNormal()) {
                isAlive = false;
                count--;
            }
            if (!isAlive) {
                printStringDead();
            }
        }
    }

    public void drink(Double amount) {
        if (isAlive) {
            weight = weight + amount;
            isWeightNormal();
            if (!isWeightNormal()) {
                isAlive = false;
                count--;
            }
            if (!isAlive) {
                printStringDead();
            }
        }
    }

    public void pee(Double amount) {
        if (isAlive) {
            weight = weight - amount;
            isWeightNormal();
            System.out.println("Сat went to pee");
            if (!isWeightNormal()) {
                isAlive = false;
                count--;
            }
            if (!isAlive) {
                printStringDead();
            }
        }
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

    public static Cat copy(Cat original) {
        Cat cat = new Cat();
        cat.originWeight = original.originWeight;
        cat.weight = original.weight;
        cat.minWeight = original.minWeight;
        cat.maxWeight = original.maxWeight;
        return cat;
    }

    public String getStatus() {
        if(weight < minWeight) {
            return "Dead";
        }
        else if(weight > maxWeight) {
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