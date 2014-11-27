package ua.ukrdev.deal.form;

import javax.persistence.*;


@Entity
@Table(name="DEALS")
public class Deal {

    @Id
    @Column(name="trandid")
    @GeneratedValue
    private Integer id;

    @Column(name="MasterDealerID")
    private Integer MasterDealerID ;

    @Column(name="DealerID")
    private Integer DealerID;

    @Column(name="AgentID")
    private Integer AgentID;

    @Column(name="AgentName")
    private String AgentName;

    @Column(name="MSISDN")
    private double MSISDN;

    @Column(name="Amount")
    private Integer Amount;

    @Column(name="Transtate")
    private String Transtate;

    @Column(name="System_id")
    private String System_id;

    @Column(name="day")
    private java.util.Date day;

    @Column(name="dtime")
    private java.util.Date dtime;

    @Column(name="err_code")
    private Integer err_code;

    @Column(name="err_description")
    private String err_description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMasterDealerID() {
        return MasterDealerID;
    }

    public void setMasterDealerID(Integer MasterDealerID) {
    	this.MasterDealerID = MasterDealerID;
    }

    public Integer getDealerID() {
        return DealerID;
    }

    public void setDealerID(Integer dealerID) {
        DealerID = dealerID;
    }

    public Integer getAgentID() {
        return AgentID;
    }

    public void setAgentID(Integer agentID) {
        AgentID = agentID;
    }

    public String getAgentName() {
        return AgentName;
    }

    public void setAgentName(String agentName) {
        AgentName = agentName;
    }

    public double getMSISDN() {
        return MSISDN;
    }

    public void setMSISDN(double MSISDN) {
        this.MSISDN = MSISDN;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }

    public String getSystem_id() {
        return System_id;
    }

    public void setSystem_id(String system_id) {
        System_id = system_id;
    }

    public String getTranstate() {
        return Transtate;
    }

    public void setTranstate(String transtate) {
        Transtate = transtate;
    }

    public java.util.Date getDay() {
        return day;
    }

    public void setDay(java.util.Date day) {
        this.day = day;
    }

    public java.util.Date getDtime() {
        return dtime;
    }

    public void setDtime(java.util.Date dtime) {
        this.dtime = dtime;
    }

    public Integer getErr_code() {
        return err_code;
    }

    public void setErr_code(Integer err_code) {
        this.err_code = err_code;
    }

    public String getErr_description() {
        return err_description;
    }

    public void setErr_description(String err_description) {
        this.err_description = err_description;
    }


}
