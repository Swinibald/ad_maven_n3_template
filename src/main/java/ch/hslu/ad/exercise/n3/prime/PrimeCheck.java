/*
 * Copyright 2025 Hochschule Luzern Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.ad.exercise.n3.prime;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


/**
 * 100 grosse Primzahlen finden.
 */
public final class PrimeCheck {

    private static final Logger LOG = LoggerFactory.getLogger(PrimeCheck.class);


    /**
     * Privater Konstruktor.
     */
    private PrimeCheck() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(20);
        ArrayList<Future<BigInteger>> futures = new ArrayList<>();
    
        while (PrimeTask.getQueue().size() < 100) {
            Future<BigInteger> future = executor.submit(new PrimeTask());
            futures.add(future);
        }
    
        executor.shutdownNow();  // Alle Threads abbrechen, sobald genug Primes
    
        int n = 1;
        for (BigInteger prime : PrimeTask.getQueue()) {
            LOG.info("{} : {}...", n, prime.toString().substring(0, 20));
            n++;
        }
    }
}

