package ch.hslu.ad.exercise.n3.prime;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimeTask implements Callable <BigInteger> {
    
    private static final Logger LOG = LoggerFactory.getLogger(PrimeTask.class);


    public PrimeTask(){

    }
    
    

    @Override
    public BigInteger call() throws Exception {
        try {
            BigInteger bi = new BigInteger(1024, new Random());
            if (bi.isProbablePrime(Integer.MAX_VALUE)) {
                return bi;
            } else {
                return BigInteger.ZERO; // Return a default value if not prime
            }
        } catch (Exception ex) {
            return BigInteger.ZERO;
        }
    }

}
