package ua.ukrdev.deal.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.ukrdev.deal.form.Deal;

import java.util.List;

/**
 * Created by Eugene on 20.09.2014.
 */
@Repository
public class DealDAOImpl implements DealDAO {


    @Autowired
    private SessionFactory sessionFactory;


    public Integer addDeal(Deal deal) {
        return (Integer) sessionFactory.getCurrentSession().save(deal);

    }

    public List<Deal> listDeal() {
        return sessionFactory.getCurrentSession().createQuery("from deals")
                .list();
    }

    public void removeDeal(Integer id) {
        Deal deal = (Deal) sessionFactory.getCurrentSession().load(
                Deal.class, id);
        if (null != deal) {
            sessionFactory.getCurrentSession().delete(deal);
        }

    }

    public Integer getNext() {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(Deal.class).setProjection(Projections.max("id"));
        if (crit.uniqueResult()!=null)
        return  (Integer) crit.uniqueResult()+1;
        else return 0;
    }
}
