package org.project;

/*
 * Create a load balancer, get address of instance, no network calls
 *
 * 1)
 * It should be possible to register an instance, identified by an address
 * Each address should be unique, it should not be possible to register the same address more than once
 * Load Balancer should accept up to 10 addresses
 *
 *
 * I can create instance class with name and address
 * Instances have to be unique, because addresses not shared
 *
 * addresses could be updated, but use constant
 *
 * In case we get to 10 instance, we notify the client. We don't try to remove instances.
 *
 * Should code be thread safe? For now single thread. Multithread later.
 * TDD approach.
 *
 *
 * 2)
 * Develop an algorithm that, when invoking the Load Balancer 's get() method multiple times,
 * should return one backend-instance choosing between the registered ones randomly.
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
