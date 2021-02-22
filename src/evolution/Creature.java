package evolution;

public class Creature {

    private int creatureId;
    public static int creatureCounter = 0;

    /**
     * Inheritable traits
     */
    private Genome genome;

    /**
     * Creature specific traits
     */
    private double energyLevel;
    private int age;
    // TODO: More traits if interesting

    public Creature(Genome genome) {
        creatureId = creatureCounter;
        creatureCounter++;
        energyLevel = 0;
        age = 0;
        this.genome = genome;
    }

    public boolean canMate (Creature possibleMate){
        // If genome values are close enough
        double tolerancePercentage = 10; // TODO – inheritable trait?
        double difference = Math.abs(this.getGenome().getGenomeValue() - possibleMate.getGenome().getGenomeValue());
        double tolerance = tolerancePercentage/100 * this.getGenome().getGenomeValue();
        return difference < tolerance;
    }

    public int getCreatureId() {
        return creatureId;
    }

    public Genome getGenome() {
        return genome;
    }

    public double getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(double energyLevel) {
        this.energyLevel = energyLevel;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Shows values of creature specific traits as well as inherited (genome) traits
     * @return
     */
    @Override
    public String toString() {
        return "Creature " + creatureId + "\n" +
                "Age " + age + "\n" +
                "Maximum lifespan " + getGenome().getMaximumLifespan() + "\n" +
                "Current energy level: " + energyLevel + "\n" +
                "Energy needed to reproduce: " + getGenome().getEnergyForReproduction() + "\n" +
                "Energy needed to clone: " + getGenome().getEnergyForCloning() + "\n" +
                "Genome value: " + getGenome().getGenomeValue() + "\n" +
                "Energy gained per generation: " + getGenome().getEnergyGainedPerGeneration() + "\n" +
                "Mutation factor: " + getGenome().getMutationFactor();
    }

    /**
     * Compare if 2 creatures are exactly the same
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
            // If the object is compared with itself then return true
            if (object == this) { return true; }

            // Check if o is an instance of Creature or not
            // "null instanceof [type]" also returns false
            if (!(object instanceof Creature)) { return false; }

            // typecast object to Creature so that we can compare data members
            Creature creature = (Creature) object;

            // Compare the data members and return accordingly
            return (this.getCreatureId() == ((Creature) object).getCreatureId());
        }
    }