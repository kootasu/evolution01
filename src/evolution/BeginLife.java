package evolution;

public class BeginLife {

    public static void main(String[] args) {
        /**
         * Simulation start with single ancestor
         * Numbers in evolution() determine single ancestor stats
         */
        Evolution evolution = new Evolution();
        evolution.evolution(2,12,2,8,0.1);
    }
}
