package ch.hslu.ad.exercise.n3.prime;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimeTask implements Callable <BigInteger> {
    
    private static final Logger LOG = LoggerFactory.getLogger(PrimeTask.class);
    static BlockingQueue<BigInteger> primes = new ArrayBlockingQueue<>(100);
    

    public PrimeTask(){

    }

        public static BlockingQueue<BigInteger> getQueue() {
        return primes;
    }
    
    

    @Override
    public BigInteger call() {
        BigInteger bi = new BigInteger(1024, new Random());
        if (bi.isProbablePrime(Integer.MAX_VALUE)) {
            boolean inserted = primes.offer(bi);  // false wenn voll
            if (!inserted) {
                throw new IllegalStateException("Queue is full.");
            }
        }
        return bi;
    }

}
