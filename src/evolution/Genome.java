package evolution;

import java.util.Random;

public class Genome {

    /**
     * A couple of (hopefully?) self-explanatory traits
     * maximumLifespan is counted as number of generations (ticks)
     * mutationFactor determines the degree to which inheritable traits mutate when creatures reproduce
     * genomeValue is used to determine compatibility with potential mates
     */
    private double energyForReproduction;
    private double energyForCloning;
    private double energyGainedPerGeneration;
    private double maximumLifespan;
    private double mutationFactor;
    private double genomeValue;

    // TODO: more traits if interesting – e.g. sexiness?

    /**
     * Single ancestor constructor
     * @param energyForReproduction
     * @param energyForCloning
     * @param energyGainedPerGeneration
     * @param maximumLifespan
     * @param mutationFactor
     */
    public Genome(double energyForReproduction, double energyForCloning, double energyGainedPerGeneration, double maximumLifespan, double mutationFactor) {
        this.energyForReproduction = energyForReproduction;
        this.energyForCloning = energyForCloning;
        this.energyGainedPerGeneration = energyGainedPerGeneration;
        this.maximumLifespan = maximumLifespan;
        this.mutationFactor = mutationFactor;
        genomeValue =  energyForReproduction *  energyForReproduction * energyGainedPerGeneration * maximumLifespan * mutationFactor; // Result of other values
    }

    /**
     * Offspring constructor
     * This is where mutations happens
     * @param parentGenome
     */
    public Genome(Genome parentGenome) {
        Random random = new Random();
        double randomMutation = (1 - parentGenome.getMutationFactor()) + ((1 + parentGenome.getMutationFactor()) - (1 - parentGenome.getMutationFactor())) * random.nextDouble();
        energyForReproduction = parentGenome.energyForReproduction * randomMutation;
        energyForCloning = parentGenome.energyForCloning * randomMutation;
        energyGainedPerGeneration = parentGenome.energyGainedPerGeneration * randomMutation;
        maximumLifespan = parentGenome.maximumLifespan * randomMutation;
        mutationFactor = parentGenome.mutationFactor * randomMutation;
        genomeValue = energyForReproduction * energyForReproduction * energyGainedPerGeneration * maximumLifespan * mutationFactor; // Result of other values
    }

    public double getEnergyForReproduction() {
        return energyForReproduction;
    }

    public double getEnergyForCloning() {
        return energyForCloning;
    }

    public double getEnergyGainedPerGeneration() { return energyGainedPerGeneration; }

    public double getMaximumLifespan() {
        return maximumLifespan;
    }

    public double getMutationFactor() {
        return mutationFactor;
    }

    public double getGenomeValue() {
        return genomeValue;
    }

}
