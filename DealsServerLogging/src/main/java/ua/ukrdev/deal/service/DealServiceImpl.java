package ua.ukrdev.deal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ukrdev.deal.dao.DealDAO;
import ua.ukrdev.deal.form.Deal;

import java.util.List;

/**
 * Created by Eugene on 20.09.2014.
 */
@Service
public class DealServiceImpl implements DealService {
    @Autowired
    private DealDAO dealDAO;

    @Transactional
    public Integer addDeal(Deal deal) {
        return dealDAO.addDeal(deal);
    }

    @Transactional
    public List<Deal> listDeal() {

        return dealDAO.listDeal();
    }

    @Transactional
    public void removeDeal(Integer id) {
        dealDAO.removeDeal(id);
    }

    @Transactional
    public Integer getNext() {
        return  dealDAO.getNext();

    }

}