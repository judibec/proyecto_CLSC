package com.financialLab.financedevapp.services;

import com.financialLab.financedevapp.dto.charts.ChartsGaugePercentageDTO;
import com.financialLab.financedevapp.models.*;
import com.financialLab.financedevapp.types.ConceptType;
import com.financialLab.financedevapp.types.DebtType;
import com.financialLab.financedevapp.types.FrequencyType;
import com.financialLab.financedevapp.types.RevenueType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChartsService {

    @Autowired
    private FinanceService financeService;

    @Transactional
    public ChartsGaugePercentageDTO getDebtOverMonthlyIncome(User loggedUser){
        Finance finance = loggedUser.getFinance();
        List<Debt> debts = finance.getDebts();
        List<Revenue> revenues = finance.getRevenues();
        Double debtsAmount = debts.stream().filter(debt -> FrequencyType.MONTHLY.equals(debt.getFrequency()))
                .mapToDouble(Debt::getAmount)
                .sum();
        Double revenuesAmount = revenues.stream().filter(revenue -> !RevenueType.SAVINGS.equals(revenue.getType()))
                .mapToDouble(Revenue::getAmount)
                .sum();
        Double percentage = (debtsAmount/revenuesAmount) * 100;
        ChartsGaugePercentageDTO chart = new ChartsGaugePercentageDTO();
        chart.setPercentage(percentage);
        return chart;
    }

    @Transactional
    public ChartsGaugePercentageDTO getAssetsVSDebts(User loggedUser){
        Finance finance = loggedUser.getFinance();
        List<Debt> debts = finance.getDebts();
        List<Asset> assets = finance.getAssets();
        List<Investment> investments = finance.getInvestments();
        Double debtsAmount = debts.stream().filter(debt -> FrequencyType.TOTAL.equals(debt.getFrequency()))
                .mapToDouble(Debt::getAmount)
                .sum();
        Double assetsAmount = assets.stream()
                .mapToDouble(Asset::getAmount)
                .sum();
        Double investmentsAmount = investments.stream().filter(investment -> ConceptType.INVESTMENT.equals(investment.getConcept()))
                .mapToDouble(Investment::getAmount)
                .sum();
        Double percentage = (debtsAmount/(assetsAmount+investmentsAmount)) * 100;
        ChartsGaugePercentageDTO chart = new ChartsGaugePercentageDTO();
        chart.setPercentage(percentage);
//        ChartsPercentageDTO.of(chart);
        return chart;
    }

    @Transactional
    public ChartsGaugePercentageDTO getRevenuesVSExpenses(User loggedUser){
        Finance finance = loggedUser.getFinance();
        List<Expenses> expenses = finance.getExpenses();
        List<Revenue> revenues = finance.getRevenues();
        List<Debt> debts = finance.getDebts();
        Double expensesAmount = expenses.stream()
                .mapToDouble(Expenses::getAmount)
                .sum();
        Double monthlyDebts = debts.stream().filter(debt -> FrequencyType.MONTHLY.equals(debt.getFrequency()))
                .mapToDouble(Debt::getAmount)
                .sum();
        Double revenuesAmount = revenues.stream().filter(revenue -> !RevenueType.SAVINGS.equals(revenue.getType()))
                .mapToDouble(Revenue::getAmount)
                .sum();
        Double percentage = ((expensesAmount+monthlyDebts)/revenuesAmount) * 100;
        ChartsGaugePercentageDTO chart = new ChartsGaugePercentageDTO();
        chart.setPercentage(percentage);
//        ChartsPercentageDTO.of(chart);
        return chart;
    }

    @Transactional
    public ChartsGaugePercentageDTO getRevenuesVSSavings(User loggedUser){
        Finance finance = loggedUser.getFinance();
        List<Revenue> revenues = finance.getRevenues();
        Double savings = revenues.stream().filter(revenue -> RevenueType.SAVINGS.equals(revenue.getType()))
                .mapToDouble(Revenue::getAmount)
                .sum();
        Double revenuesAmount = revenues.stream().filter(revenue -> !RevenueType.SAVINGS.equals(revenue.getType()))
                .mapToDouble(Revenue::getAmount)
                .sum();
        Double percentage = (savings/revenuesAmount) * 100;
        ChartsGaugePercentageDTO chart = new ChartsGaugePercentageDTO();
        chart.setPercentage(percentage);
//        ChartsPercentageDTO.of(chart);
        return chart;
    }

    @Transactional
    public ChartsGaugePercentageDTO getFinantialFreedom(User loggedUser){
        Finance finance = loggedUser.getFinance();
        List<Expenses> expenses = finance.getExpenses();
        List<Investment> investments = finance.getInvestments();
        List<Debt> debts = finance.getDebts();
        Double monthlyDebts = debts.stream().filter(debt -> FrequencyType.MONTHLY.equals(debt.getFrequency()))
                .mapToDouble(Debt::getAmount)
                .sum();
        Double expensesAmount = expenses.stream()
                .mapToDouble(Expenses::getAmount)
                .sum();
        Double investmentsAmount = investments.stream().filter(investment -> ConceptType.PASSIVE_INCOME.equals(investment.getConcept()))
                .mapToDouble(Investment::getAmount)
                .sum();
        Double percentage = (investmentsAmount/(expensesAmount+monthlyDebts)) * 100;
        ChartsGaugePercentageDTO chart = new ChartsGaugePercentageDTO();
        chart.setPercentage(percentage);
//        ChartsPercentageDTO.of(chart);
        return chart;
    }
}
