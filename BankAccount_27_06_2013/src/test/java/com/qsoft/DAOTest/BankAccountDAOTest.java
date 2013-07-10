package com.qsoft.DAOTest;

import com.qsoft.business.BankAccountService;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * User: quynhtq
 * Date: 7/9/13
 * Time: 1:54 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class BankAccountDAOTest
{
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private BankAccountDAO bankAccountDAO;
    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private DataSource dataSourceTest;

    private IDatabaseTester databaseTester;

    @Before
    public void setup() throws Exception
    {
        IDataSet dataSet = readDataSet();  // read data from xml file
        cleanlyInsert(dataSet);  // empty the db and insert data
    }

    public IDataSet readDataSet() throws Exception
    {
        return new FlatXmlDataSetBuilder().build(System.class.getResource("/dataset.xml"));
    }

    private void cleanlyInsert(IDataSet dataSet) throws Exception
    {
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
        BankAccountEntity bankAccountEntity = bankAccountDAO.getAccountByAccountNumber("0123456789");
        assertEquals("0123456789", bankAccountEntity.getAccountNumber());
        assertEquals(new Double(100), bankAccountEntity.getBalance());
        assertEquals(new Long(12345678), bankAccountEntity.getOpenTimestamp());
    }
    @Test
    public void testGetAccountById(){
        BankAccountEntity bankAccountEntity = bankAccountDAO.getAccountById(2L);
        assertEquals("0123456782", bankAccountEntity.getAccountNumber());
        assertEquals(new Double(10), bankAccountEntity.getBalance());
        assertEquals(new Long(12345678), bankAccountEntity.getOpenTimestamp());
    }
    @Test
    public void testAnAccountDoesNotExist()
    {
        BankAccountEntity bankAccountEntity = bankAccountDAO.getAccountByAccountNumber("138741");
        assertEquals(null, bankAccountEntity);
        BankAccountEntity bankAccountEntity1 = bankAccountDAO.getAccountByAccountNumber("10");
        assertEquals(null, bankAccountEntity1);
    }

    @Test
    public void testTheWholeListInTheDatabase()
    {
        List<BankAccountEntity> bankAccountEntityList = bankAccountDAO.getLAllAccount();
        assertEquals("0123456782",bankAccountEntityList.get(0).getAccountNumber());
        assertEquals(new Double(10), bankAccountEntityList.get(0).getBalance());
        assertEquals(new Long(12345678), bankAccountEntityList.get(0).getOpenTimestamp());

        assertEquals("0123456783",bankAccountEntityList.get(1).getAccountNumber());
        assertEquals(new Double(100), bankAccountEntityList.get(1).getBalance());
        assertEquals(new Long(12345678), bankAccountEntityList.get(1).getOpenTimestamp());

    }

}
