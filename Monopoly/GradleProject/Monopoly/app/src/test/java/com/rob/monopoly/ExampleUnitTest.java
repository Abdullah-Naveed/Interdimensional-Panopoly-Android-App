package com.rob.monopoly;

import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    Bank bank;

    @Before
    public void setUp()
    {
        bank=new Bank(1000);
    }

    @Test
    public void deposit() throws Exception {
        bank.deposit(100);
        assertEquals(bank.getBalance(),1100);
    }
    @Test
    public void withdraw() throws Exception {
        bank.withdraw(100);
        assertEquals(bank.getBalance(),900);
    }
    @Test
    public void getBalance() throws Exception {
        assertEquals(bank.getBalance(),1000);
    }

}