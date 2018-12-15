package com.example.demo.test;

import lombok.Data;

public class ListExample {
    public static void main(String[] args) {

    }
}
class RepertDTO{}
@Data
class InoutPlanReportDTO extends RepertDTO{
    private String inoutPlanId;
}
@Data
class LoanDetailReportDTO extends RepertDTO{
    private String loanDetailId;
}
