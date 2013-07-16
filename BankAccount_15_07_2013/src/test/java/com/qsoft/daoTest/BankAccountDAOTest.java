package com.qsoft.daoTest;

import com.qsoft.persistence.dao.BankAccountDAO;
import com.qsoft.persistence.model.BankAccountEntity;
import junit.framework.Assert;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;

/**
 * User: quynhtq
 * Date: 7/15/13
 * Time: 2:03 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class BankAccountDAOTest
{
    @Autowired
    private BankAccountDAO bankAccountDAO;
    @Autowired
    private DataSource dataSourceTest;

    private IDatabaseTester databaseTester;

    @Before
    public void setup() throws Exception
    {
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(System.class.getResource("/dataset.xml"));
        databaseTester = new DataSourceDatabaseTester(dataSourceTest);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @After
    public void tearDown() throws Exception
    {
        databaseTester.onTearDown();
    }

    @Test
    public void testGetAccountByAccountNumber()
    {
        BankAccountEntity bankAccountEntity = bankAccountDAO.getAccountByAccountNumber("0123456781");

        assertEquals("0123456781", bankAccountEntity.getAccountNumber());
        Assert.assertEquals(new Double(1), bankAccountEntity.getBalance());
        Assert.assertEquals(new Long(12345678), bankAccountEntity.getOpenTimestamp());
    }

    @Test
    public void testSaveAccountIntoDatabase()
    {
        BankAccountEntity bankAccountEntity = new BankAccountEntity("987654321", 10D, 12346789L);
        bankAccountDAO.save(bankAccountEntity);

        BankAccountEntity bankAccountEntity1 = bankAccountDAO.getAccountByAccountNumber("987654321");

        assertEquals("987654321", bankAccountEntity1.getAccountNumber());
        assertEquals(new Double(10), bankAccountEntity1.getBalance());
        assertEquals(new Long(12346789), bankAccountEntity1.getOpenTimestamp());
    }

    @Test
    public void getAnAccountWhichDoesNotExistInTheDataBase()
    {
        BankAccountEntity bankAccountEntity = bankAccountDAO.getAccountByAccountNumber("2842373143");

        assertEquals(null, bankAccountEntity);
    }

    @Test
    public void testUpdateAnAccountWhichIsExistingInTheDatabase()
    {
        BankAccountEntity bankAccountEntity = bankAccountDAO.getAccountByAccountNumber("0123456781");
        Double aBalance = bankAccountEntity.getBalance() + 10D;
        bankAccountEntity.setBalance(aBalance);
        bankAccountDAO.save(bankAccountEntity);

        BankAccountEntity bankAccountEntity1 = bankAccountDAO.getAccountByAccountNumber("0123456781");
        assertEquals("0123456781", bankAccountEntity1.getAccountNumber());
        assertEquals(aBalance, bankAccountEntity1.getBalance());
        assertEquals(new Long(12345678), bankAccountEntity1.getOpenTimestamp());
    }

}
