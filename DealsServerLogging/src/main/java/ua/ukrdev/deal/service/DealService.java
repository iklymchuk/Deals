package ua.ukrdev.deal.service;

import ua.ukrdev.deal.form.Deal;

import java.util.List;

/**
 * Created by Eugene on 20.09.2014.
 */
public interface DealService {
    public Integer addDeal(Deal deal);
    public List<Deal> listDeal();
    public void removeDeal(Integer id);
    public Integer getNext();
}
