/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Account;
import java.util.ArrayList;
import javax.persistence.criteria.CriteriaQuery;
import model.Address;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import view.Launchpad;

/**
 *
 * @author Primax
 */
public class AccountHandler {
    //private Account acc;
    private ArrayList<Account> acc;
    private Session sess;

    public AccountHandler() {
        this.acc = new ArrayList<>();
        
    }
    public boolean createAccount(long accountNumber, String name, long mobileNo, double balance, char accountType, ArrayList<Address> addr){
        Account acco = new Account(accountNumber, name, mobileNo, balance, accountType, addr);
        //this.acc.add(acco);
        Transaction tx = null;
        try{
            sess = Launchpad.sf.openSession();
            tx =sess.beginTransaction();
            sess.save(acco);
            tx.commit();
            //sess.close();
            return true;
        }catch (Exception e) {
            if (tx!=null)
                tx.rollback();
            return false;
        }finally{
            sess.close();
        }
    }
    
    //deleteAccount business logic only.
    public boolean deleteAccount(Account ac){
        Transaction tx = null;
        try {
            sess = Launchpad.sf.openSession();
            tx = sess.beginTransaction();
            sess.delete(ac);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        } finally {
            sess.close();
        }
    }
    
    public double deposit(Account ac, double balance){
        double tempAmount = ac.getAmount();
        tempAmount += balance;
        ac.setAmount(tempAmount);
        Transaction tx = null;
        try {
            sess = Launchpad.sf.openSession();
            tx = sess.beginTransaction();
            sess.update(ac);
            tx.commit();
            return ac.getAmount();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return -1;
        } finally {
            sess.close();
        }
    }

    public double withdraw(Account ac, double balance){
                double tempAmount = ac.getAmount();
                if(tempAmount < balance){
                    return -2;
                }
                tempAmount -= balance;
                ac.setAmount(tempAmount);
                Transaction tx = null;
                try{
                    sess = Launchpad.sf.openSession();
                    tx =sess.beginTransaction();
                    sess.update(ac);
                    tx.commit();
                    return ac.getAmount();
                }catch(Exception e){
                    if (tx!=null)
                        tx.rollback();
                    return -1;
                }finally{
                    sess.close();
                }
    }
    
    public ArrayList<Account> searchName(String name){
        Transaction tx = null;
        try{
            sess = Launchpad.sf.openSession();
            tx =sess.beginTransaction();
            Criteria criteria = sess.createCriteria(Account.class);
            ArrayList<Account> data = (ArrayList<Account>) criteria.add(Restrictions.eq("name", name))
                             .list();
            return data;
        }catch(Exception e){
            if(tx != null)
                tx.rollback();
            return null;
        }finally{
            sess.close();
        }
    }
//    
    public Account search(long accountNumber){
        Transaction tx = null;
        try{
            sess = Launchpad.sf.openSession();
            tx =sess.beginTransaction();
            Account tmpAcc = (Account)sess.get(Account.class, accountNumber);
            // if fetch method is EAGER comment the line below.
            Hibernate.initialize(tmpAcc.getAddr());
            return tmpAcc;
        }catch(Exception e){
            if (tx!=null)
                tx.rollback();
            return null;
        }finally{
            sess.close();
        }
    }
//    
    public boolean FundTransfer(Account srcAccount, Account trgtAccount, double amount){
        double fundA, fundB;
        fundA = srcAccount.getAmount();
        fundB = trgtAccount.getAmount();
        if(fundA < amount){
            return false;
        }
        fundB += amount;
        fundA -= amount;
        srcAccount.setAmount(fundA);
        trgtAccount.setAmount(fundB);
        Transaction tx = null;
        try{
                    sess = Launchpad.sf.openSession();
                    tx =sess.beginTransaction();
                    sess.update(srcAccount);
                    sess.update(trgtAccount);
                    tx.commit();
                    return true;
                }catch(Exception e){
                    if (tx!=null)
                        tx.rollback();
                    return false;
                }finally{
                    sess.close();
                }
    }
    
    public ArrayList<Account> listAll(){
        Transaction tx = null;
        ArrayList<Account> data;
        sess = Launchpad.sf.openSession();
        tx =sess.beginTransaction();
        CriteriaQuery<Account> criteriaQuery = sess.getCriteriaBuilder().createQuery(Account.class);
        criteriaQuery.from(Account.class);

        data = (ArrayList<Account>)sess.createQuery(criteriaQuery).getResultList();
        sess.close();
        return data;
    }
    
// This method is here to give the difference to students on what we were ding previously and what we do now.
// depreceated but easy method.
//    public ArrayList<Account> listAll(){
//        Transaction tx = null;
//        ArrayList<Account> data = new ArrayList<>();
//        sess = Launchpad.sf.openSession();
//        tx =sess.beginTransaction();
//        data = (ArrayList<Account>)sess.createCriteria(Account.class).list();
//        sess.close();
//        return data;
//    }
}
