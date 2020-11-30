import java.lang.Math;
import java.util.*;

/* YOU NEED TO ADD YOUR CODE TO THIS CLASS, REMOVING ALL DUMMY CODE
 *
 * DO NOT CHANGE THE NAME OR SIGNATURE OF ANY OF THE EXISTING METHODS
 * (Signature includes parameter types, return types and being static)
 *
 * You can add private methods to this class if it makes your code cleaner,
 * but this class MUST work with the UNMODIFIED Tester2.java class.
 *
 * This is the ONLY class that you can submit for your assignment.
 *
 * MH October 2020
 */
public class Queens2
{
    private static int boardSize = 12;
    
    // ************************************************************************
    // ************ A. RANK A SET OF VALUES ***********************************
    // ************************************************************************
    
    // returns the rank of the values provided
    // worst =0 ; best = values.length-1
    // ranking is shared if tied:
    // for input values [30, 70, 50, 50, 40, 80] the rankings are [0, 4, 2.5, 2.5, 1, 5]
    // ... because (2 + 3)/2 = 2.5
    public static double[] rank(Integer values[])
    {
        double rank[] = new double[values.length];
        
        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE:
        
        rank = new double[]{5, 4, 3, 2, 1, 0};
        for (int i = 0; i < values.length; i++){
            int x = 1;
            int y = 1;
            for (int j = 0; j < values.length; j ++){
                if (j != i && values[j] < values[i]){
                    x += 1;
                }
                if (j != i && values[j] == values[i]){
                    y += 1;
                }
            }
            rank[i] = x + (double)(y - 1)/(double)2 - (double)1;
            
        }

        // END OF YOUR CODE
        
        return rank;
    }
    
    // ************************************************************************
    // ************ B. CALCULATE LINEAR RANKING PROBABILITY *******************
    // ************************************************************************
    
    // calculates the linear ranking probability of a genotype (see the linear ranking equation)
    public static double linearRankingProb(double rank, double s, int populationSize)
    {
        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE:
        double probability = 0.2;
        probability = ((2 - s)/populationSize) + (2 * rank*(s - 1)/(populationSize * (populationSize - 1)));

        
        // END OF YOUR CODE
        
        return probability;
    }
    
    // ************************************************************************
    // ************ C. PERFORM LINEAR RANKING PARENT SELECTION ****************
    // ************************************************************************
    
    /* performs linear ranking parent selection (see class slides & the guide in the course shell)
     * s is the 'selective pressure' parameter from the P[i] equation
     */
    public static Integer[][] linearRankingSelect(Integer[][] population, double s)
    {
        // The first three parts of this method have been written for you...
        // But the fourth part - selecting the two parents - is up to you!
        
        Integer [][] parents = new Integer [2][boardSize]; // will hold the selected parents
        int popSize = population.length;
        
        // 1. determine the fitness of each member of the population
        Integer [] fitness = getFitnesses(population);
        
        // 2. hence determine the ranking of each member by its fitness
        double [] rank = rank(fitness);
        
        // 3. determine the cumulative probability of selecting each member, using the linear ranking formula
        double [] cumulative = new double [popSize];
        cumulative[0] = linearRankingProb(rank[0], s, popSize);
        for (int index = 1; index < popSize; index ++)
        {
            cumulative[index] = cumulative[index-1] + linearRankingProb(rank[index], s, popSize);
        }
        
        // 4. finally, select two parents, based on the cumulative probabilities
        // see the pseudocode in RESOURCES > Evolutionary Computation General >
        // Linear Ranking: How to use cumulative probability to select parents
        
        // YOUR CODE GOES HERE

        double rand1 = Math.random();
        int first = 0;
        while(cumulative[first] < rand1)
        {
            first++;
        }
        int second = first;

        while(first == second)
        {
            double rand2 = Math.random();
            second = 0;
            while(cumulative[second] < rand2)
            {
                second++;
            }
        }
        parents[0] = population[first];
        parents[1] = population[second];
        
        // END OF YOUR CODE
        
        return parents;
    }
    
    // ************************************************************************
    // ************ D. (λ, μ) SURVIVOR SELECTION ******************************
    // ************************************************************************
    
    /* creates a new population through (λ, μ) survivor selection
     * given a required population of size n, and a set of children of size 2n,
     * this method will return the n fittest children as the new population
     */
        
        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE:

        public static Integer[][] survivorSelection(Integer[][] children, int n)
    {

        Integer [][] newPop = new Integer[n][n];
       
        int index = 0;
                        
        for(int k = 0; k < newPop.length; k++) 
        {

            double MaxNum = 0;
            for (int j = 0; j < children.length; j++)
            {

                if(Queens.measureFitness(children[j]) >= MaxNum) 
                {
                    MaxNum = Queens.measureFitness(children[j]);
                    index = j;
                    newPop[k] = children[j];

                }
            }
            Integer[][] temp = new Integer[children.length - 1][]; 
            int m = 0;
            for (int i = 0; i < children.length; i ++)
            {
                if (i != index)
                {
                    temp[m++] = children[i]; 
                }
            }
            children = temp;

        }

        // END OF YOUR CODE
        
        return newPop;
    }
    
    // ************************************************************************
    // ************ E. FITNESS DIVERSITY **************************************
    // ************************************************************************
    
    // counts the number of unique fitness values seen in the population
    public static int fitnessDiversity(Integer[][] population)
    {
        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE:
        int same = 0;
        double temp1 = 0;
        double temp2 = 0;
        int uniqueFitnessValues = 0;
        Integer UNList [][] = new Integer [population.length][boardSize];

        for (int j = 0; j < population.length; j++){
            for (int i = 0; i < UNList.length; i++){
                if ( Arrays.equals(population[j], UNList[i] )){

                    same = 1;
                    break;
                }
                else{
                    same = 0;
                }
            }
            
            if (same == 0){
                UNList[uniqueFitnessValues] = population[j];
                uniqueFitnessValues ++;
                
            }
        }
        // END OF YOUR CODE
        
        return uniqueFitnessValues;
    }
    
    // ************************************************************************
    // ************ F.  INVERSION MUTATION ************************************
    // ************************************************************************
    
    // inverts the order of a series of genes in the genotype
    public static Integer[] inversionMutate(Integer[] genotype, double p)
    {
        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE:
        Random r = new Random();
        int p1 = r.nextInt(boardSize);
        int p2 = r.nextInt(boardSize);
        int i = 0;
        int diff = p1 - p2;
        int absDiff = Math.abs(p1 - p2);
        if (Math.random() <= p){
            for (int a = 1; a <= (absDiff/2) + 1; a++){
                i = genotype[p1];
                genotype[p1] = genotype[p2];
                genotype[p2] = i;
                if (diff < 0){
                    p1 += 1;
                    p2 -= 1;
                }
                else{
                    p1 -= 1;
                    p2 += 1;
                }
            }
           
        }
        // END OF YOUR CODE
        
        return genotype;
    }
    
    // ************ DO NOT EDIT OR DELETE THE METHOD BELOW! *******************
    
    // ************************************************************************
    // ************ GET THE FITNESS VALUES OF A POPULATION ********************
    // ************************************************************************
    
    // returns an array of fitnesses for a population
    private static Integer[] getFitnesses(Integer [][] population)
    {
        int popSize = population.length;
        Integer [] fitness = new Integer [popSize];
        
        for (int index = 0; index < popSize; index ++)
        {
            fitness[index] = Queens.measureFitness(population[index]);
        }
        
        return fitness;
    }
}
