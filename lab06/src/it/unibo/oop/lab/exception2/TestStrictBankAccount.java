package it.unibo.oop.lab.exception2;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * JUnit test to test
 * {@link it.unibo.oop.lab.exception2.StrictBankAccount}.
 */
public final class TestStrictBankAccount {

    private static final int STARTING_AMOUNT = 10_000;
    private static final int LIMIT = 50_000;

    /**
     * Used to test Exceptions on
     * {@link it.unibo.oop.lab.exception2.StrictBankAccount}.
     */
    @Test
    public void testBankOperations() {
        /*
         * 1) Creare due StrictBankAccountImpl assegnati a due AccountHolder a
         * scelta, con ammontare iniziale pari a 10000 e nMaxATMTransactions=10
         */
        final AccountHolder mRossi = new AccountHolder("Mario", "Rossi", 1);
        final AccountHolder lBianchi = new AccountHolder("Luigi", "Bianchi", 2);
        final StrictBankAccount rossiAccount = new StrictBankAccount(mRossi.getUserID(), STARTING_AMOUNT, 10);
        final StrictBankAccount bianchiAccount = new StrictBankAccount(lBianchi.getUserID(), STARTING_AMOUNT, 10);
        /*
         * 2) Effetture un numero di operazioni a piacere per verificare che le
         * eccezioni create vengano lanciate soltanto quando opportuno, cioè in presenza
         * di un id utente errato, oppure al superamento del numero di operazioni ATM
         * gratuite.
         */
        try {
            rossiAccount.deposit(4, 100);
            fail();
        } catch (WrongAccountHolderException e) {
            assertNotNull(e);
        }
        for (int i = 0; i < 10; i++) {
            try {
                bianchiAccount.depositFromATM(lBianchi.getUserID(), 1);
            } catch (TransactionsOverQuotaException | WrongAccountHolderException e) {
                fail("Still not reached number maxinum of transactions!");
            }
        }
        try {
            bianchiAccount.depositFromATM(lBianchi.getUserID(), 1);
            fail("Maxinum number of transactions reached!");
        } catch (TransactionsOverQuotaException | WrongAccountHolderException e) {
            assertNotNull(e);
        }
        try {
            rossiAccount.withdraw(mRossi.getUserID(), LIMIT);
        } catch (WrongAccountHolderException e) {
            fail();
        } catch (NotEnoughFoundsException e) {
            assertNotNull(e);
        }
    }
}
