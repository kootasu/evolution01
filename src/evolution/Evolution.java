package evolution;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Evolution {

    private ArrayList<Creature> creatures = new ArrayList<>();
    private ArrayList<Creature> tempCreatures = new ArrayList<>();

    /**
     * Simulation starts with a single ancestor
     * In every generation:
     * Creatures that have reached their maximum lifespan die
     * All creatures will try to mate (requires both energy and compatible mate)
     * All creatures will try to clone (requres energy)
     * All creatures get some energy
     * Print info on creatures
     * All creatures age
     * @param energyForReproduction
     * @param energyForCloning
     * @param energyGainedPerGeneration
     * @param maximumLifespan
     * @param mutationFactor
     */
    public void evolution(double energyForReproduction, double energyForCloning, double energyGainedPerGeneration, double maximumLifespan, double mutationFactor) {
        // Single ancestor
        creatures.add(new Creature(new Genome(energyForReproduction, energyForCloning, energyGainedPerGeneration, maximumLifespan, mutationFactor)));

        Iterator<Creature> iterator = creatures.iterator();
        int ticks = 0;
        Scanner sc = new Scanner(System.in);

        // Time keeps moving
        while (true) {

            // For every creature
            for (Creature creature : creatures) {

                // Old creatures die – only keep living ones
                if (creature.getAge() < creature.getGenome().getMaximumLifespan()) {
                    tempCreatures.add(creature);
                }
                else if (creature.getAge() >= creature.getGenome().getMaximumLifespan()) {
                    System.out.println("Creature " + creature.getCreatureId() + " died");
                }

                // Creature will try to mate
                mate(creature);

                // If creature can't mate, it will try to clone
                clone(creature);
            }

            // List of creatures emptied
            creatures.removeAll(creatures);
            // All creatures in temporary list added to creatures list
            for (Creature creature : tempCreatures) { creatures.add(creature); }
            // Temporary creature list emptied
            tempCreatures.removeAll(tempCreatures);

            // All creatures get some energy
            for (Creature creature : creatures) {
                creature.setEnergyLevel(creature.getEnergyLevel() + creature.getGenome().getEnergyGainedPerGeneration());
            }

            // Time to print
            System.out.println("Generation " + ticks + "\nCreature count: " + creatures.size() + "\n\n" +
                    "*********************************************\n");
            for (Creature creature : creatures) {
                // Count possible mates
                int mateCount = 0;
                for (Creature mate : creatures) {
                    // Can't mate with itself
                    if (mate == creature) {
                        continue;
                    } else {
                        if (creature.canMate(mate))
                            mateCount++;
                    }
                }
                System.out.println(creature + "\n" +
                        "Potential mates: " + mateCount + "\n");
            }
            System.out.println("Generation " + ticks + "\nCreature count: " + creatures.size() + "\n\n" +
                    "*********************************************\n\n(Press enter to continue)");
            // Every creature ages
            for (Creature creature : creatures) {
                creature.setAge(creature.getAge() + 1);
            }
            ticks++; // Time moves
            String input = sc.nextLine();
        }
    }

    /**
     * Creature tries to mate
     * Requires a certain energy level
     * Also requires genetically compatible mate
     * Mates are judged by their inherited trait genomeValue
     * If creature can mate, it will produce as many offspring as possible with its current energy level
     * Offspring are created with mutated version of their parent's genome
     * @param creature
     */
    public void mate(Creature creature) {
        // If creature has enough energy to mate
        if (creature.getEnergyLevel() > creature.getGenome().getEnergyForReproduction()) {
            // Look for a mate
            for (Creature mate : creatures) {
                // Can't mate with itself
                if (mate == creature) {
                    continue;
                } else {
                    // If the 2 creatures are genetically compatible
                    if (creature.canMate(mate)) {
                        // Creature spends energy on mating – TODO: Maybe mate should also spend energy?
                        creature.setEnergyLevel(creature.getEnergyLevel() - creature.getGenome().getEnergyForReproduction());
                        // Mutated creatures are born
                        Creature offspring = new Creature(new Genome(creature.getGenome()));
                        tempCreatures.add(offspring);
                    }
                }
            }
        }
    }

    /**
     * Creatures with enough energy can clone
     * Clones are genetically identical
     * Clones are able to mate with each other
     * @param creature
     */
    public void clone(Creature creature) {
        // If creature has enough energy to clone
        if (creature.getEnergyLevel() > creature.getGenome().getEnergyForCloning()) {
            // Creature spends energy on cloning
            creature.setEnergyLevel(creature.getEnergyLevel() - creature.getGenome().getEnergyForCloning());
            // Creates clone with identical genome
            Creature clone = new Creature(creature.getGenome());
            tempCreatures.add(clone);
        }
    }
}