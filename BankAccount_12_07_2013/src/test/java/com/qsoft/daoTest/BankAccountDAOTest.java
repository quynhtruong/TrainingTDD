package com.qsoft.daoTest;

import com.qsoft.persistence.dao.BankAccountDAO;
import com.qsoft.persistence.model.BankAccountEntity;
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

import static junit.framework.Assert.assertEquals;

/**
 * User: quynhtq
 * Date: 7/12/13
 * Time: 1:36 PM
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
    public void testGetAccountByAccountnumber()
    {
        BankAccountEntity bankAccountEntity = bankAccountDAO.getAccountByAccountNumber("0123456781");

        assertEquals("0123456789", bankAccountEntity.getAccountNumber());
        assertEquals(new Double(100), bankAccountEntity.getBalance());
        assertEquals(new Long(12345678), bankAccountEntity.getOpenTimestamp());

    }


}
