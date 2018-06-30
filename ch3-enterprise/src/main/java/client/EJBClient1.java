package client;

import ejb.HelloService;

import javax.ejb.EJB;

public class EJBClient1 {
    @EJB
    public HelloService helloService;
}
